package com.pessoaDeon.api.controller.listas.orientacaoSexual;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.listas.OrientacaoSexual;
import com.pessoaDeon.domain.repository.listas.orientacaoSexual.OrientacaoSexualRepository;

@RestController
@RequestMapping("api/v1/lista/orientacaoSexual")
public class OrientacaoSexualController {

	@Autowired
	private OrientacaoSexualRepository oRepository;

	@GetMapping
	public List<OrientacaoSexual> listarOrientacaoSexual() {
		return oRepository.findAll();
	}
}
