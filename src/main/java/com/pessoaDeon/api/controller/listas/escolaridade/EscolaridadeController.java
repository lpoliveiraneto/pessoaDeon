package com.pessoaDeon.api.controller.listas.escolaridade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.Escolaridade;
import com.pessoaDeon.domain.repository.listas.escolaridade.EscolaridadeRepository;


@RestController
@RequestMapping("api/v1/lista/escolaridade")
public class EscolaridadeController {

	@Autowired
	private EscolaridadeRepository escolaridadeRepository;

	@GetMapping
	public List<Escolaridade> listarEscolaridade(){
		return escolaridadeRepository.findAll();
	}
}
