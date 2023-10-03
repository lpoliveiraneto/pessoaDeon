package com.pessoaDeon.domain.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.dto.CadastroRequestDto;
import com.pessoaDeon.domain.model.dto.CadastroResponseDto;
import com.pessoaDeon.domain.model.endereco.Endereco;
import com.pessoaDeon.domain.model.endereco.Logradouro;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.enumeration.TipoEnvio;
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
//		var user = salvarUsuario(cadastroRequestDto);
//		pessoa.setUsuario(user);
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
		
		salvarUsuario(cadastroRequestDto, pessoaSave);
//		user.setPessoa(pessoaSave);
		return pessoaSave;
	}
	
	@Transactional
	private Endereco salvarEndereco(CadastroRequestDto dto, Pessoa pessoa, Logradouro logradouro) {
		Endereco endereco = new Endereco();
		endereco.setAtual(true);
		endereco.setNumero(dto.getNumero());
		endereco.setReferencia(dto.getReferencia());
		endereco.setComplemento(dto.getComplemento());
		endereco.setPessoa(pessoa);
		endereco.setLogradouro(logradouro);
		endereco.setTipoMoradia(dto.getTipoMoradia());
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
	private Usuario salvarUsuario(CadastroRequestDto cadastroDto, Pessoa pessoa){
		final long PERFIL_USER = 1;
		Usuario usuario = new Usuario();
		usuario.setEmail(cadastroDto.getEmail());
		usuario.setSenha(new BCryptPasswordEncoder().encode(cadastroDto.getSenha()));
		usuario.setStatus(Status.PE);
		usuario.setDataCadastro(LocalDateTime.now());
		usuario.setPessoa(pessoa);
		usuario.adicionarPerfil(perfilRepository.findById(PERFIL_USER).get());
		var user = usuarioService.salvarUsuario(usuario);
		if (user != null) {
			var verify = verificacaoContaService.save(user);
			envioEmailService.enviarCodigoEmail(user.getEmail(), verify.getCodigo(), TipoEnvio.CD);
		}
		return usuario;
	}

	@ReadOnlyProperty
	public CadastroResponseDto listarCadastroPessoa(Integer idPessoa) {
		CadastroResponseDto response = new CadastroResponseDto();
		
		Endereco enderecoPessoa = enderecoService.getById(idPessoa).get();
		Pessoa pessoa = pessoaService.buscarPessoa(idPessoa).get();
		Telefone contato = contatoService.getById(idPessoa);
		Email email = emailService.getByIdEmail(idPessoa);
		//dados referentes a pessoa
		response.setNome(pessoa.getNome());
		response.setDataNascimento(pessoa.getDataNascimento());
		response.setNomeMae(pessoa.getNomeMae());
		response.setNomePai(pessoa.getNomePai());
		response.setAlcunha(pessoa.getAlcunha());
		response.setNomeSocial(pessoa.getNomeSocial());
		response.setSexo(pessoa.getSexo().getDescricao());
		response.setEstadoCivil(pessoa.getEstadoCivil().getNome());
		response.setOrientacaoSexual(pessoa.getOrientacaoSexual().getNome());
		response.setIdentidadeGenero(pessoa.getIdentidadeGenero().getNome());
		response.setDeficiencia(pessoa.getDeficiencia().getNome());
		response.setCorPele(pessoa.getCorPele().getNome());
		response.setEscolaridade(pessoa.getEscolaridade().getNome());
		response.setTipoDocumento(pessoa.getTipoDocumento().getDescricao());
		response.setNumeroDocumento(pessoa.getNumeroDocumento());
		response.setEstadoNaturalidade(pessoa.getEstadoNaturalidade().getDescricao());
		response.setCidadeNaturalidade(pessoa.getCidadeNaturalidade().getDescricao());
		response.setPais(pessoa.getPais().getDescricao());
		//dados referentes a endereco
		response.setCep(enderecoPessoa.getLogradouro().getCep());
		response.setEstado(enderecoPessoa.getLogradouro().getEstado().getDescricao());
		response.setCidade(enderecoPessoa.getLogradouro().getCidade().getDescricao());
		response.setBairro(enderecoPessoa.getLogradouro().getBairro().getDescricao());
		response.setLogradouro(enderecoPessoa.getLogradouro().getLogradouro());
		response.setNumero(enderecoPessoa.getNumero());
		response.setTipoMoradia(enderecoPessoa.getTipoMoradia().getDescricao());
		response.setComplemento(enderecoPessoa.getComplemento());
		response.setReferencia(enderecoPessoa.getReferencia());
		//dados referentes
		response.setTelefone(contato.getTelefone());
		response.setTipoWhatsapp(contato.getTipowhatsapp());
		response.setTipoTelegram(contato.getTipotelegram());
		response.setEmail(email.getEmail());
		
		return response;
	}
}
