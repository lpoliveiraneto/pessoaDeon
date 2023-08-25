package com.pessoaDeon.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.pessoa.Email;
import com.pessoaDeon.domain.repository.pessoa.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository; 
	
	@Transactional
	public Email salvarEmail(Email email) {
//		Optional<Email> buscaEmail = emailRepository.findById(email.getId_email());
//		if(buscaEmail.isPresent()) {
//			throw new RuntimeException("Email já está cadastrado para uma pessoa");
//		}
		return emailRepository.save(email);
	}
}
