package com.pessoaDeon.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.pessoa.Telefone;
import com.pessoaDeon.domain.repository.pessoa.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;
	
	@Transactional
	public Telefone saveContato(Telefone telefone) {
		return contatoRepository.save(telefone);
	}
	
	public Telefone getById(Integer idTelefone) {
		Telefone contato = contatoRepository.findById(idTelefone).get();
		return contato;
	}
}
