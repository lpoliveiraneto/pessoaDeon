package com.pessoaDeon.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.Email;
import com.pessoaDeon.domain.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository; 
	
	@Transactional
	public Email salvarEmail(Email email) {
		return emailRepository.save(email);
	}
}
