package com.pessoaDeon.api.controller.listas.escolaridade;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.enumeration.Escolaridade;

@RestController
@RequestMapping("api/v1/escolaridade")
public class EscolaridadeController {

	@GetMapping
    public Map<String, String> listarEscolaridade(){
        List<Escolaridade> list = Arrays.asList(Escolaridade.values());
        Map<String, String> listaEscolaridade = new HashMap<>();
        list.forEach(lista -> {
        	listaEscolaridade.put(lista.toString(), lista.getDescricao());
        });
        return listaEscolaridade;
    }
}
