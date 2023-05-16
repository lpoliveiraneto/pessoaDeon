package com.pessoaDeon.api.controller.listas.corRaca;

import com.pessoaDeon.domain.model.enumeration.CorPele;
import com.pessoaDeon.domain.model.util.EnumToObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("api/v1/racas")
public class CorRacaController {

    @GetMapping
    public List<EnumToObject> listarPele(){
        List<CorPele> listaEnum = Arrays.asList(CorPele.values());
        Map<String, String > listaCorPele = new HashMap<>();
        listaEnum.forEach(p ->{
            listaCorPele.put(p.toString(), p.getDescricao());
        });
        List<EnumToObject> lista = new ArrayList<>();
        for(String chave : listaCorPele.keySet()) {
        	EnumToObject novoEnum = new EnumToObject();
        	novoEnum.setKey(chave);
        	novoEnum.setValue(listaCorPele.get(chave));
        	lista.add(novoEnum);
        }
        return lista;
    }
}
