package com.pessoaDeon.api.controller.listas.corRaca;

import com.pessoaDeon.domain.model.enumeration.CorPele;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("api/v1/racas")
public class CorRacaController {

    @GetMapping
    public Map<String, String> listarPele(){
        List<CorPele> listaEnum = Arrays.asList(CorPele.values());
        Map<String, String > listaCorPele = new HashMap<>();
        listaEnum.forEach(p ->{
            listaCorPele.put(p.toString(), p.getDescricao());
        });
        return listaCorPele;
    }
}
