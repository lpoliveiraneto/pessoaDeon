package com.pessoaDeon.domain.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pessoaDeon.domain.exception.ArquivoNaoEncontradoException;
import com.pessoaDeon.domain.model.AnexoPessoa;
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
import com.pessoaDeon.domain.model.util.ConfiguracaoUpload;
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
	private ConfiguracaoUploadService uploadService;
	
	@Autowired
	private AnexoPessoaService anexoPessoaService;

	@Autowired
	private PerfilRepository perfilRepository;

	@Transactional
	public Pessoa salvar(CadastroRequestDto cadastroRequestDto, MultipartFile[] files){
		Pessoa pessoa = modelMapper.map(cadastroRequestDto, Pessoa.class);
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
		salvarAnexosDocumentoPessoa(pessoa, files);
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
		
		Endereco enderecoPessoa = enderecoService.getEnderecoByIdPessoa(idPessoa).get();
		Pessoa pessoa = pessoaService.buscarPessoa(idPessoa).get();
		Telefone contato = contatoService.getById(idPessoa);
		Email email = emailService.getByIdEmail(idPessoa);
		List<String> anexos = carregarArquivo(idPessoa);
		//dados referentes a pessoa
		response.setNome(pessoa.getNome());
		response.setDataNascimento(pessoa.getDataNascimento());
		response.setNomeMae(pessoa.getNomeMae());
		response.setNomePai(pessoa.getNomePai());
		response.setAlcunha(pessoa.getAlcunha());
		response.setNomeSocial(pessoa.getNomeSocial());
		response.setSexo(pessoa.getSexo() != null ? pessoa.getSexo().getDescricao() : "Não Informado");
		response.setEstadoCivil(pessoa.getEstadoCivil() !=null ? pessoa.getEstadoCivil().getNome() : "Não Informado");
		response.setOrientacaoSexual(pessoa.getOrientacaoSexual() !=null ? pessoa.getOrientacaoSexual().getNome() : "Não Informado");
		response.setIdentidadeGenero(pessoa.getIdentidadeGenero() !=null ? pessoa.getIdentidadeGenero().getNome() : "Não Informado");
		response.setDeficiencia(pessoa.getDeficiencia()!=null ? pessoa.getDeficiencia().getNome() : "Não Informado");
		response.setCorPele(pessoa.getCorPele() !=null ? pessoa.getCorPele().getNome() : "Não Informado");
		response.setEscolaridade(pessoa.getEscolaridade() !=null ? pessoa.getEscolaridade().getNome() : "Não Informado");
		response.setTipoDocumento(pessoa.getTipoDocumento() !=null ? pessoa.getTipoDocumento().getDescricao() : "Não Informado");
		response.setNumeroDocumento(pessoa.getNumeroDocumento());
		response.setEstadoNaturalidade(pessoa.getEstadoNaturalidade() !=null ? pessoa.getEstadoNaturalidade().getDescricao() : "Não Informado");
		response.setCidadeNaturalidade(pessoa.getCidadeNaturalidade() !=null ? pessoa.getCidadeNaturalidade().getDescricao() : "Não Informado");
		response.setPais(pessoa.getPais() !=null ? pessoa.getPais().getDescricao() : "Não Informado");
		//dados referentes a endereco
		response.setCep(enderecoPessoa.getLogradouro() != null ? enderecoPessoa.getLogradouro().getCep() : "Não Informado");
		response.setEstado(enderecoPessoa.getLogradouro() != null ? enderecoPessoa.getLogradouro().getEstado().getDescricao() : "Não Informado");
		response.setCidade(enderecoPessoa.getLogradouro() != null ? enderecoPessoa.getLogradouro().getCidade().getDescricao() : "Não Informado");
		response.setBairro(enderecoPessoa.getLogradouro() != null ? enderecoPessoa.getLogradouro().getBairro().getDescricao() : "Não Informado");
		response.setLogradouro(enderecoPessoa.getLogradouro() != null ? enderecoPessoa.getLogradouro().getLogradouro() : "Não Informado");
		response.setNumero(enderecoPessoa.getNumero());
		response.setTipoMoradia(enderecoPessoa.getTipoMoradia() != null ? enderecoPessoa.getTipoMoradia().getDescricao() : "Não Informado");
		response.setComplemento(enderecoPessoa.getComplemento());
		response.setReferencia(enderecoPessoa.getReferencia());
		//dados referentes
		response.setTelefone(contato.getTelefone());
		response.setTipoWhatsapp(contato.getTipowhatsapp());
		response.setTipoTelegram(contato.getTipotelegram());
		response.setEmail(email.getEmail());
//		lista de anexos
		response.setListaAnexos(anexos);
		
		return response;
	}
	
	private String gerarNomeArquivo() {
		UUID uuid = UUID.randomUUID();
		String name = uuid.toString();		
		return name; 
	}

	public String salvarAnexosDocumentoPessoa(Pessoa pessoa, MultipartFile[] files) {
		ConfiguracaoUpload config = uploadService.getConfiguracaoUploadAtiva();
		Path path = Paths.get(config.getPath());
		try {
			if (files != null) {
				for(MultipartFile file : files) {
					String extensao = FilenameUtils.getExtension(file.getOriginalFilename());
                    String nomeArquivo = gerarNomeArquivo() + "." + extensao;
                    Path filePath = path.resolve(nomeArquivo);
					Files.copy(file.getInputStream(), filePath);
					salvarInfoAnexo(config, pessoa, nomeArquivo, extensao);
				}
				 return "Arquivo salvo com sucesso!";
			}
		} catch (Exception e) {
			return "Erro ao salvar arquivo: " +e.getMessage();		}
		return null;
	}
	
	private void salvarInfoAnexo(ConfiguracaoUpload config, Pessoa pessoa, 
			String arquivo, String extensao) {
		AnexoPessoa anexo = new AnexoPessoa();
		anexo.setCaminho(config.getPath());
		anexo.setConfigUpload(config);
		anexo.setDataUpload(LocalDateTime.now());
		anexo.setNomeArquivo(arquivo);
		anexo.setPessoa(pessoa);
		anexo.setTipoArquivo(extensao);	
		anexoPessoaService.salvarAnexoPessoa(anexo);
	}
	
	public List<String> carregarArquivo(Integer idPessoa) {
		List<AnexoPessoa> listaAnexo = anexoPessoaService.findByPessoaIdPessoa(idPessoa);
		List<String> listaAnexoToString = new ArrayList<>();
		listaAnexo.forEach(a -> {
			Path diretorioDeArmazenamento = Paths.get(a.getCaminho());
			try {
				Path caminhoArquivo = diretorioDeArmazenamento.resolve(a.getNomeArquivo());
				Resource resource = new UrlResource(caminhoArquivo.toUri());
				if (resource.exists() && resource.isReadable()) {
					var bin = resource.getContentAsByteArray();
					String imagemString = Base64.getEncoder().encodeToString(bin);
					listaAnexoToString.add(imagemString);
				} else {
					throw new ArquivoNaoEncontradoException("Arquivo não encontrado: " + idPessoa, null);
				}
			} catch (IOException e) {
				throw new ArquivoNaoEncontradoException("Arquivo não encontrado: " + idPessoa, e);
			}
		});
		return listaAnexoToString;
    }
}

