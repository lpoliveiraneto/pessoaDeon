package com.pessoaDeon.api.controller.listas.cidades;

import com.pessoaDeon.domain.model.listas.Cidade;
import com.pessoaDeon.domain.service.CidadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/lista/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/{idEstado}")
    public List<Cidade> cidadesPorEstado(@PathVariable("idEstado") Integer idEstado){
    	return cidadeService.findByEstadoIdEstado(idEstado);
    }
}
