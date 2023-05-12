package com.pessoaDeon.api.controller.listas.deficiencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.enumeration.Deficiencia;
import com.pessoaDeon.domain.model.util.EnumToObject;

@RestController
@RequestMapping("api/v1/deficiencias")
public class DeficienciaController {

    @GetMapping
    public List<EnumToObject> listarDeficiencias(){
        List<Deficiencia> listaEnum = Arrays.asList(Deficiencia.values());
        Map<String, String> listaDeficiencias = new HashMap<>();
        listaEnum.forEach(d -> {
            listaDeficiencias.put(d.toString(), d.getDescricao());
        });
        List<EnumToObject> lista = new ArrayList<>();
        for(String key : listaDeficiencias.keySet()) {
        	EnumToObject to = new EnumToObject();
        	to.setKey(key);
        	to.setValue(listaDeficiencias.get(key));
        	lista.add(to);
        }
        return lista;
    }
}
