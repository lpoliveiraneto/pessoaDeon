package com.pessoaDeon.api.controller.listas.identidadeGenero;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.listas.IdentidadeGenero;
import com.pessoaDeon.domain.repository.listas.identidadeGenero.IdentidadeGeneroRepository;

@RestController
@RequestMapping("api/v1/lista/identidadeGenero")
public class IdentidadeGeneroController {

	@Autowired
	private IdentidadeGeneroRepository identidadeGeneroRepository;

	@GetMapping
	public List<IdentidadeGenero> listarIdentidadeGenero(){
		return identidadeGeneroRepository.findAll();
	}
}
