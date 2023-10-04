package com.pessoaDeon.domain.service.analista;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.repository.analista.AnalistaRepository;

@Service
public class AnalistaService {

	@Autowired
	private AnalistaRepository analistaRepository;
	
	public Optional<Analista> findById(Integer id) {
		return analistaRepository.findById(id);
	}
}
