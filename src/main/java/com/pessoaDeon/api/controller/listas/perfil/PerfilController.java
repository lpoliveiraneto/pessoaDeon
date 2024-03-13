package com.pessoaDeon.api.controller.listas.perfil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.security.Perfil;
import com.pessoaDeon.domain.repository.listas.perfil.PerfilRepository;

@RestController
@RequestMapping("/api/v1/lista/perfil")
public class PerfilController {

	@Autowired
	private PerfilRepository perfilRepository;
	
	@GetMapping
	public List<Perfil> list(){
		return perfilRepository.findAll();
	}
}
