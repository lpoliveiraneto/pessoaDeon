package com.pessoaDeon.domain.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.dto.CadastroRequestDto;
import com.pessoaDeon.domain.model.endereco.Endereco;
import com.pessoaDeon.domain.model.endereco.Logradouro;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.pessoa.Email;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.pessoa.Telefone;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.repository.listas.perfil.PerfilRepository;


@Service
public class CadastroService {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
    private LogradouroService logradouroService;
    
    @Autowired
    private EnderecoService enderecoService;
    
    @Autowired
    private ContatoService contatoService;
    
    @Autowired
    private EmailService emailService;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EnvioEmailService envioEmailService;

	@Autowired
	private VerificacaoContaService verificacaoContaService;

	@Autowired
	private PerfilRepository perfilRepository;

	@Transactional
	public Pessoa salvar(CadastroRequestDto cadastroRequestDto){
		Pessoa pessoa = modelMapper.map(cadastroRequestDto, Pessoa.class);
		var user = salvarUsuario(cadastroRequestDto);
		pessoa.setUsuario(user);
		var pessoaSave = pessoaService.salvarPessoaDeon(pessoa);
		
		if(pessoaSave != null) {
			Logradouro logradouro = modelMapper.map(cadastroRequestDto, Logradouro.class);

				var logradouroSave = logradouroService.getByCep(logradouro.getCep());
				
				if (logradouroSave.getCep() != null) {
					this.salvarEndereco(cadastroRequestDto, pessoaSave, logradouroSave);
				} else {
					var logradouroWithoutCepNotFound = logradouroService.save(logradouro);
					this.salvarEndereco(cadastroRequestDto, pessoaSave, logradouroWithoutCepNotFound);
				}

			this.salvarTelefone(cadastroRequestDto, pessoaSave);
			this.salvarEmail(cadastroRequestDto, pessoaSave);
		}
		return pessoaSave;
	}
	
	@Transactional
	private Endereco salvarEndereco(CadastroRequestDto dto, Pessoa pessoa, Logradouro logradouro) {
		Endereco endereco = new Endereco();
		endereco.setAtual(true);
		endereco.setNumero(dto.getNumero());
		endereco.setReferencia(dto.getReferencia());
		endereco.setPessoa(pessoa);
		endereco.setLogradouro(logradouro);
		endereco.setTipoLocal(dto.getTipoLocal());
		return enderecoService.salvarEndereco(endereco);
	}
	
	@Transactional
	private Telefone salvarTelefone(CadastroRequestDto requestDto, Pessoa pessoa) {
		Telefone telefone = new Telefone();
		telefone.setTelefone(requestDto.getTelefone());
		telefone.setAtual(true);
		telefone.setPessoa(pessoa);
		if (requestDto.getTipoWhatsapp() != null) {
			telefone.setTipowhatsapp(requestDto.getTipoWhatsapp());
		} else {
			telefone.setTipowhatsapp(false);
		}
		if (requestDto.getTipoTelegram() != null) {
			telefone.setTipotelegram(requestDto.getTipoTelegram());
		} else {
			telefone.setTipotelegram(false);
		}
		var telefoneSave = contatoService.saveContato(telefone);
		return telefoneSave;
	}
	
	@Transactional
	private Email salvarEmail(CadastroRequestDto dto, Pessoa pessoa) {
		Email email = new Email();
		email.setEmail(dto.getEmail());
		email.setAtual(true);
		email.setPessoa(pessoa);
		var emailSave = emailService.salvarEmail(email);
		return emailSave;
	}

	@Transactional
	private Usuario salvarUsuario(CadastroRequestDto cadastroDto){
		final long PERFIL_USER = 1;
		Usuario usuario = new Usuario();
		usuario.setEmail(cadastroDto.getEmail());
		usuario.setSenha(new BCryptPasswordEncoder().encode(cadastroDto.getSenha()));
		usuario.setStatus(Status.PE);
		usuario.setDataCadastro(LocalDateTime.now());
		usuario.adicionarPerfil(perfilRepository.findById(PERFIL_USER).get());
		var user = usuarioService.salvarUsuario(usuario);
		if (user != null) {
			var verify = verificacaoContaService.save(user);
			envioEmailService.enviarCodigoEmail(user.getEmail(), verify.getCodigo());
		}
		return usuario;
	}

//	private String gerarSenhaAleatoria(int tamanho) {
//		final String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//
//		SecureRandom random = new SecureRandom();
//		return IntStream.range(0, tamanho)
//				.map(i -> random.nextInt(chars.length()))
//				.mapToObj(randomIndex -> String.valueOf(chars.charAt(randomIndex)))
//				.collect(Collectors.joining());
//	}

	public String testeEnvioEmail(String email) {
		Usuario user = new Usuario();
		user.setEmail(email);
		var codigo = verificacaoContaService.gerarCodigoVerificacaoConta();
		envioEmailService.enviarCodigoEmail(user.getEmail(), codigo);
		return null;
	}

}
