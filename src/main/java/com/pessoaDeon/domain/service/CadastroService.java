package com.pessoaDeon.domain.service;

import com.pessoaDeon.domain.model.enumeration.PerfilUsuario;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.security.Perfil;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.repository.UsuarioRepository;
import com.pessoaDeon.domain.repository.listas.perfil.PerfilRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.Email;
import com.pessoaDeon.domain.model.Endereco;
import com.pessoaDeon.domain.model.Logradouro;
import com.pessoaDeon.domain.model.Pessoa;
import com.pessoaDeon.domain.model.Telefone;
import com.pessoaDeon.domain.model.dto.CadastroRequestDto;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
		String senha = gerarSenhaAleatoria(5);
		System.out.println(senha);
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		usuario.setStatus(Status.PE);
		usuario.setDataCadastro(LocalDateTime.now());
		usuario.adicionarPerfil(perfilRepository.findById(PERFIL_USER).get());
		var user = usuarioService.salvarUsuario(usuario);
		return usuario;
	}

	private String gerarSenhaAleatoria(int tamanho) {
		final String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		SecureRandom random = new SecureRandom();
		return IntStream.range(0, tamanho)
				.map(i -> random.nextInt(chars.length()))
				.mapToObj(randomIndex -> String.valueOf(chars.charAt(randomIndex)))
				.collect(Collectors.joining());
	}

}
