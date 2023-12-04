package com.pessoaDeon.api.controller.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.usuario.RespostaAnaliseUsuario;
import com.pessoaDeon.domain.repository.usuario.RespostaUsuarioAnaliseRepository;

@RestController
@RequestMapping("/api/v1/resposta")
public class RespostaAnalise {

	@Autowired
	private RespostaUsuarioAnaliseRepository respostaUsuarioAnaliseRepository;
	
	@GetMapping
	public List<RespostaAnaliseUsuario> listar(){
		return respostaUsuarioAnaliseRepository.findAll();
	}
}
