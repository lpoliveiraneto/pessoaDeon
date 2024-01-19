package com.pessoaDeon.api.controller.pecas.listas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.pecas.TipoRelacionamento;
import com.pessoaDeon.domain.repository.pecas.TipoRelacionamentoRepository;

@RestController
@RequestMapping("/api/v1/listas/relacionamento")
public class RelacionamentoController {

	
	@Autowired
    private TipoRelacionamentoRepository tipoRelacionamentoRepository;
	
	 
    @GetMapping
    public List<TipoRelacionamento> tiposRelacionamentos(){
    	return tipoRelacionamentoRepository.findAll();
    }
}
