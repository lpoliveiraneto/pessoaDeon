package com.pessoaDeon.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.EnvioEmail;
import com.pessoaDeon.domain.model.RemetenteEmail;
import com.pessoaDeon.domain.model.enumeration.StatusEnvio;
import com.pessoaDeon.domain.model.enumeration.TipoEnvio;
import com.pessoaDeon.domain.repository.EnvioEmailRepository;
import com.pessoaDeon.domain.repository.RemetenteEmailRepository;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EnvioEmailService {
	
	@Autowired
	private RemetenteEmailRepository remetenteRepository;
	
	@Autowired
	private EnvioEmailRepository envioEmailRepository;
	
	private static Session getSession(RemetenteEmail remetente) {
		final String username = remetente.getEmail();
		final String password = remetente.getSenha();
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", remetente.getHost());
		props.put("mail.smtp.port", remetente.getPorta());
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		return session;
	}
	
	@SuppressWarnings("finally")
	public EnvioEmail enviarEmail(RemetenteEmail remetente, String destinatario, String assunto, String emailBody) {
		try {
			Message message = new MimeMessage(getSession(remetente));
			message.setFrom(new InternetAddress(remetente.getEmail()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			message.setSubject(assunto);
			message.setContent(emailBody, "text/html; charset=utf-8");
			Transport.send(message);
			System.out.println("Email enviado com sucesso!");
		} catch (MessagingException e) {
			System.err.println(e.getMessage());
		} finally {
			return salvarLogEnvioEmail(destinatario);
		}
	}
	
	public Optional<RemetenteEmail> getRemetente(Integer id) {
		return remetenteRepository.findByIdRemetenteAndStatusIsTrue(id);
	}
	
	@Transactional
	public EnvioEmail salvarLogEnvioEmail(String destinatario) {
		EnvioEmail envioEmail = new EnvioEmail();
		envioEmail.setDataRegistro(LocalDateTime.now());
		envioEmail.setDestinatario(destinatario);
		envioEmail.setStatus(StatusEnvio.OK);
		return envioEmailRepository.save(envioEmail);
		
	}
	
	public void enviarCodigoEmail(String destinatario, String codigo, TipoEnvio tipo) {
		Optional<RemetenteEmail> remetente = getRemetente(1);
		if (remetente.isPresent()) {
			String body = null;
			if(tipo == TipoEnvio.CD) {
				body = emailBodyConfirmarConta(codigo);
			}
			if(tipo == TipoEnvio.RS) {
				body = emailBodyResetSenha(codigo);
			}
			enviarEmail(remetente.get(), destinatario, tipo.getDescricao(), body);
		}
	}
	
	public String emailBodyConfirmarConta(String codigo) {
		String emailBody = "<div style=\"background-color:#ffffff\"><div class=\"adM\">\r\n"
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
	//				+ "                <a href=\"http://10.38.210.18:8080/api/v1/cadastro/verifyAccount?codigo="+codigo+"\" style=\"display:inline-block;text-decoration:none;background:#10a37f;border-radius:3px;color:white;font-family:Helvetica,sans-serif;font-size:16px;line-height:24px;font-weight:400;padding:12px 20px 11px;margin:0px\" target=\"_blank\">\r\n"
			+ "                <a href=\"https://deon.policiacivil.ma.gov.br/validacaoEmail/"+codigo+"\" style=\"display:inline-block;text-decoration:none;background:#10a37f;border-radius:3px;color:white;font-family:Helvetica,sans-serif;font-size:16px;line-height:24px;font-weight:400;padding:12px 20px 11px;margin:0px\" target=\"_blank\">\r\n"
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
		return emailBody;
	}
	
	public String emailBodyResetSenha(String codigo) {
		String emailBody = "<div style=\"background-color:#ffffff\"><div class=\"adM\">\r\n"
				+ "    </div><center>\r\n"
				+ "	<table style=\"width:560px;margin:0;padding:0;font-family:Helvetica,Arial,sans-serif;border-collapse:collapse!important;height:100%!important;background-color:#ffffff\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"m_-3052587946865727809bodyTable\">\r\n"
				+ "		<tbody><tr>\r\n"
				+ "		<td align=\"center\" valign=\"top\" id=\"m_-3052587946865727809bodyCell\" style=\"margin:0;padding:0;font-family:Helvetica,Arial,sans-serif;height:100%!important\">\r\n"
				+ "            <div style=\"background-color:#ffffff;color:#202123;padding:27px 20px 0 15px\">\r\n"
				+ "			</div>\r\n"
				+ "            <div style=\"background-color:#ffffff;color:#353740;padding:40px 20px;text-align:left;line-height:1.5\">\r\n"
				+ "              <h1 style=\"color:#202123;font-size:32px;line-height:40px;margin:0 0 20px\">Solicitação de redefinição de senha</h1>\r\n"
				+ "\r\n"
				+ "              <p style=\"font-size:16px;line-height:24px\">\r\n"
				+ "                Uma solicitação para redefinição de senha foi realizada, para redefinir sua senha clique no botão abaixo. \r\n"
				+ "              </p>\r\n"
				+ "              \r\n"
				+ "              <p style=\"margin:24px 0 0;text-align:left\">\r\n"
				//				+ "                <a href=\"http://10.38.210.18:8080/api/v1/cadastro/verifyAccount?codigo="+codigo+"\" style=\"display:inline-block;text-decoration:none;background:#10a37f;border-radius:3px;color:white;font-family:Helvetica,sans-serif;font-size:16px;line-height:24px;font-weight:400;padding:12px 20px 11px;margin:0px\" target=\"_blank\">\r\n"
				+ "                <a href=\"https://deon.policiacivil.ma.gov.br/resetSenha/"+codigo+"\" style=\"display:inline-block;text-decoration:none;background:#10a37f;border-radius:3px;color:white;font-family:Helvetica,sans-serif;font-size:16px;line-height:24px;font-weight:400;padding:12px 20px 11px;margin:0px\" target=\"_blank\">\r\n"
				+ "                    Redefinir senha\r\n"
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
		return emailBody;
	}

}
