package com.pessoaDeon.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.Telefone;
import com.pessoaDeon.domain.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;
	
	@Transactional
	public Telefone saveContato(Telefone telefone) {
		return contatoRepository.save(telefone);
		//Optional<Telefone> buscaTelefone = contatoRepository.findById(telefone.getIdTelefone());
		
//		if(buscaTelefone.isPresent()) {
	//		throw new RuntimeException("Telefone já cadastrado!");
	//	}
	}
}
