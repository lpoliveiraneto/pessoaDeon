package com.pessoaDeon.api.controller.listas.orientacaoSexual;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.enumeration.OrientacaoSexual;

@RestController
@RequestMapping("api/v1/orientacaoSexual")
public class OrientacaoSexualController {

	@GetMapping
    public Map<String, String> listarDeficiencias(){
        List<OrientacaoSexual> listaEnum = Arrays.asList(OrientacaoSexual.values());
        Map<String, String> listaOrientacaoSexual = new HashMap<>();
        listaEnum.forEach(d -> {
        	listaOrientacaoSexual.put(d.toString(), d.getDescricao());
        });
        return listaOrientacaoSexual;
    }
}
