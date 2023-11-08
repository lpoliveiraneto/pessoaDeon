package com.pessoaDeon.api.controller.listas.analista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.analista.Funcao;
import com.pessoaDeon.domain.repository.analista.FuncaoRepository;

@RestController
@RequestMapping("/api/v1/lista/funcao")
public class FuncaoController {

	@Autowired
	private FuncaoRepository funcaoRepository;
	
	@GetMapping
	public List<Funcao> listarFuncao(){
		return funcaoRepository.findAll();
	}
}
