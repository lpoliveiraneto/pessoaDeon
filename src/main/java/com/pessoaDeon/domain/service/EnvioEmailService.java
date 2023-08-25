package com.pessoaDeon.domain.service;

import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.RemetenteEmail;
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
	
	public static void enviarEmail(RemetenteEmail remetente, String destinatario, String assunto, String corpoEmail) {
		try {
			Message message = new MimeMessage(getSession(remetente));
			message.setFrom(new InternetAddress(remetente.getEmail()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			message.setSubject(assunto);
			message.setContent(corpoEmail, "text/html; charset=utf-8");
			Transport.send(message);
			System.out.println("Email enviado com sucesso!");
		} catch (MessagingException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Optional<RemetenteEmail> getRemetente(Integer id) {
		return remetenteRepository.findByIdRemetenteAndStatusIsTrue(id);
	}

}
