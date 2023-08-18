package com.pessoaDeon.api.controller.listas.sexo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.Sexo;
import com.pessoaDeon.domain.repository.listas.sexo.SexoRepository;

@RestController
@RequestMapping("api/v1/lista/sexo")
public class SexoController {
	
	@Autowired
	private SexoRepository sexoRepository;


	@GetMapping
	public List<Sexo> listSexo(){
		return sexoRepository.findAll();
	}
}
