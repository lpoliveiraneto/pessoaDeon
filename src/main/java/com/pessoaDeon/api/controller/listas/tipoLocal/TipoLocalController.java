package com.pessoaDeon.api.controller.listas.tipoLocal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.TipoLocal;
import com.pessoaDeon.domain.repository.listas.tipoLocal.TipoLocalRepository;

@RestController
@RequestMapping("api/v1/lista/tipoLocal")
public class TipoLocalController {
	
	@Autowired
	private TipoLocalRepository tipoLocalRepository;
	
	//@GetMapping("/lista")
	@GetMapping
	public List<TipoLocal> listarTipoLocal(){
		return tipoLocalRepository.findAll();
	}
}
