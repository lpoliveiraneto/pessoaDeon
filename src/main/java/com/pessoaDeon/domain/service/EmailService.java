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
		return emailRepository.save(email);
	}
	
	public Email getByIdEmail(Integer idEmail) {
		var email =	emailRepository.findById(idEmail).get();
		return email;
	}
}
