package com.pessoaDeon.api.controller.listas.analista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.analista.Cargo;
import com.pessoaDeon.domain.repository.analista.CargoRepository;

@RestController
@RequestMapping("/api/v1/lista/cargo")
public class CargoController {

	@Autowired
	private CargoRepository repository;
	
	@GetMapping
	public List<Cargo> listarCargos(){
		return repository.findAll();
	}
}
