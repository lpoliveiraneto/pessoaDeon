package com.pessoaDeon.api.controller.listas.identidadeGenero;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.enumeration.IdentidadeGenero;

@RestController
@RequestMapping("api/v1/identidadeGenero")
public class IdentidadeGeneroController {

	@GetMapping
    public Map<String, String> listarDeficiencias(){
        List<IdentidadeGenero> listaEnum = Arrays.asList(IdentidadeGenero.values());
        Map<String, String> listaIdentidadeGenero = new HashMap<>();
        listaEnum.forEach(lista -> {
        	listaIdentidadeGenero.put(lista.toString(), lista.getDescricao());
        });
        return listaIdentidadeGenero;
    }
}
