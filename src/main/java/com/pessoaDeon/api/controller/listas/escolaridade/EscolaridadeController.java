package com.pessoaDeon.api.controller.listas.escolaridade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.enumeration.Escolaridade;
import com.pessoaDeon.domain.model.util.EnumToObject;

@RestController
@RequestMapping("api/v1/escolaridade")
public class EscolaridadeController {

	@GetMapping
    public List<EnumToObject> listarEscolaridade(){
        List<Escolaridade> list = Arrays.asList(Escolaridade.values());
        Map<String, String> listaEscolaridade = new HashMap<>();
        list.forEach(lista -> {
        	listaEscolaridade.put(lista.toString(), lista.getDescricao());
        });
        List<EnumToObject> lista = new ArrayList<>();
        for(String chave : listaEscolaridade.keySet()) {
        	EnumToObject novoEnum = new EnumToObject();
        	novoEnum.setKey(chave);
        	novoEnum.setValue(listaEscolaridade.get(chave));
        	lista.add(novoEnum);
        }
        return lista;
    }
}
