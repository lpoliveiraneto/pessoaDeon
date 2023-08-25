package com.pessoaDeon.api.controller.listas.deficiencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.listas.Deficiencia;
import com.pessoaDeon.domain.repository.listas.deficiencia.DeficienciaRepository;

@RestController
@RequestMapping("api/v1/lista/deficiencias")
public class DeficienciaController {
		
	@Autowired
	private DeficienciaRepository deficienciaRepository;

	@GetMapping
	public List<Deficiencia> listarDeficiencias(){
		return deficienciaRepository.findAll();
	}
}
