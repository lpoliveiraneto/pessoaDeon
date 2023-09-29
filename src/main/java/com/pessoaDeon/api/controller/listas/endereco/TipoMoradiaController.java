package com.pessoaDeon.api.controller.listas.endereco;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.endereco.TipoMoradia;
import com.pessoaDeon.domain.repository.listas.TipoMoradiaRepository;

@RestController
@RequestMapping("/api/v1/tipoMoradia")
public class TipoMoradiaController {
	
	@Autowired
	private TipoMoradiaRepository listRepository;
	
	/**
	 * @author Hilberto	
	 * @return retorna lista de enderecos(residencial, comercial e residencial/ comercial) para ser usado no front
	 * 
	 * */
	@GetMapping("/lista")
	public List<TipoMoradia> list(){
		return listRepository.findAll();
	}
	
}