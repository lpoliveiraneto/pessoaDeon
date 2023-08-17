package com.pessoaDeon.api.controller.listas.corRaca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.Raca;
import com.pessoaDeon.domain.repository.listas.raca.RacaRepository;

@RestController
@RequestMapping("api/v1/lista/racas")
public class CorRacaController {

	@Autowired
	private RacaRepository racaRepository;

	@GetMapping
	public List<Raca> listarRacaCor(){
		return racaRepository.findAll() ;
	}
}
