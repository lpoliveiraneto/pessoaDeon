package com.pessoaDeon.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.dto.integracao.ContatoRequestDto;
import com.pessoaDeon.domain.model.pessoa.Email;
import com.pessoaDeon.domain.model.pessoa.Telefone;
import com.pessoaDeon.domain.repository.pessoa.ContatoRepository;
import com.pessoaDeon.domain.repository.pessoa.EmailRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private EmailRepository emailRepository; 
	
	@Transactional
	public Telefone saveContato(Telefone telefone) {
		return contatoRepository.save(telefone);
	}
	
	public Telefone getById(Integer idTelefone) {
		Telefone contato = contatoRepository.findById(idTelefone).get();
		return contato;
	}
	
	public ContatoRequestDto contatoToDto(Integer idPessoa) {
		Telefone telefone = contatoRepository.findByPessoaId(idPessoa);
		Email email = emailRepository.findByPessoaId(idPessoa);
		ContatoRequestDto contatoDto = new ContatoRequestDto();
		contatoDto.setEmail(email != null ? email.getEmail() : null);
		contatoDto.setNumero(telefone != null ? telefone.getTelefone() : null);
		contatoDto.setWhatsapp(telefone != null ? telefone.getTipowhatsapp() : null);
		return contatoDto;
	}
}
