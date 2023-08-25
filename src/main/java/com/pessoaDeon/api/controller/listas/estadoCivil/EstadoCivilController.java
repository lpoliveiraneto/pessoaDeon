package com.pessoaDeon.api.controller.listas.estadoCivil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.listas.EstadoCivil;
import com.pessoaDeon.domain.repository.listas.estadoCivil.EstadoCivilRepository;

@RestController
@RequestMapping("api/v1/lista/estadoCivil")
public class EstadoCivilController {
	
	@Autowired
	private EstadoCivilRepository estadoCivilRepository;

	@GetMapping
	public List<EstadoCivil> listarEstadoCivil(){
		return estadoCivilRepository.findAll();
	}
	
}
