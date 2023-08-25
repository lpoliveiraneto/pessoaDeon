package com.pessoaDeon.domain.service;

import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.security.Usuario;
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
import com.pessoaDeon.domain.model.RemetenteEmail;
import com.pessoaDeon.domain.model.Telefone;
import com.pessoaDeon.domain.model.dto.CadastroRequestDto;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
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
			enviaCodigoEmail(user.getEmail(), verify.getCodigo());
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
	
	private void enviaCodigoEmail(String destinatario, String codigo) {
		Optional<RemetenteEmail> remetente = envioEmailService.getRemetente(1);
		if (remetente.isPresent()) {
			String corpo2 = "<div style=\"background-color:#ffffff\"><div class=\"adM\">\r\n"
					+ "    </div><center>\r\n"
					+ "	<table style=\"width:560px;margin:0;padding:0;font-family:Helvetica,Arial,sans-serif;border-collapse:collapse!important;height:100%!important;background-color:#ffffff\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"m_-3052587946865727809bodyTable\">\r\n"
					+ "		<tbody><tr>\r\n"
					+ "		<td align=\"center\" valign=\"top\" id=\"m_-3052587946865727809bodyCell\" style=\"margin:0;padding:0;font-family:Helvetica,Arial,sans-serif;height:100%!important\">\r\n"
					+ "            <div style=\"background-color:#ffffff;color:#202123;padding:27px 20px 0 15px\">\r\n"
					+ "			</div>\r\n"
					+ "            <div style=\"background-color:#ffffff;color:#353740;padding:40px 20px;text-align:left;line-height:1.5\">\r\n"
					+ "              <h1 style=\"color:#202123;font-size:32px;line-height:40px;margin:0 0 20px\">Verifique seu endereço de email</h1>\r\n"
					+ "\r\n"
					+ "              <p style=\"font-size:16px;line-height:24px\">\r\n"
					+ "                Para continuar com a validação de sua conta na Delegacia Online, é necessário que você confirme que este é seu endereço de email. \r\n"
					+ "              </p>\r\n"
					+ "              \r\n"
					+ "              <p style=\"margin:24px 0 0;text-align:left\">\r\n"
//					+ "                <a href=\"http://10.38.210.18:8080/api/v1/cadastro/verifyAccount?codigo="+codigo+"\" style=\"display:inline-block;text-decoration:none;background:#10a37f;border-radius:3px;color:white;font-family:Helvetica,sans-serif;font-size:16px;line-height:24px;font-weight:400;padding:12px 20px 11px;margin:0px\" target=\"_blank\">\r\n"
					+ "                <a href=\"http://10.38.210.18:5173/validacaoEmail/"+codigo+"\" style=\"display:inline-block;text-decoration:none;background:#10a37f;border-radius:3px;color:white;font-family:Helvetica,sans-serif;font-size:16px;line-height:24px;font-weight:400;padding:12px 20px 11px;margin:0px\" target=\"_blank\">\r\n"
					+ "                    Confirmar endereço de email\r\n"
					+ "                </a>\r\n"
					+ "                </p>\r\n"
					+ "            </div>\r\n"
					+ "			<div style=\"text-align:left;background:#ffffff;color:#6e6e80;padding:0 20px 20px;font-size:13px;line-height:1.4\">\r\n"
					+ "				<p style=\"margin:0\">\r\n"
					+ "                Este link expira em 2 horas. Se você não fez esta solicitação, favor desconsidere este email. Para ajuda, contate nosso suporte através do número (98) 991756242 Whatsapp.\r\n"
					+ "				</p>\r\n"
					+ "            </div>\r\n"
					+ "          </td>\r\n"
					+ "        </tr>\r\n"
					+ "      </tbody></table>\r\n"
					+ "    </center>\r\n"
					+ "  </div>";
			String assunto = "Cadastro Deon - Confirme seu email";
			EnvioEmailService.enviarEmail(remetente.get(), destinatario, assunto, corpo2);
		}
	}
	
	public String testeEnvioEmail(String email) {
		Usuario user = new Usuario();
		user.setEmail(email);
		var codigo = verificacaoContaService.gerarCodigoVerificacaoConta();
		enviaCodigoEmail(user.getEmail(), codigo);
		return null;
	}
	
}
