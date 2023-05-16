package com.pessoaDeon.api.controller.listas.sexo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.enumeration.Sexo;
import com.pessoaDeon.domain.model.util.EnumToObject;

@RestController
@RequestMapping("api/v1/sexo")
public class SexoController {

	@GetMapping
    public List<EnumToObject> listarDeficiencias(){
        List<Sexo> listaEnum = Arrays.asList(Sexo.values());
        Map<String, String> listaSexo = new HashMap<>();
        listaEnum.forEach(lista -> {
        	listaSexo.put(lista.toString(), lista.getDescricao());
        });
        
        List<EnumToObject> lista = new ArrayList<>();
        for(String chave : listaSexo.keySet()) {
        	EnumToObject novoEnum = new EnumToObject();
        	novoEnum.setKey(chave);
        	novoEnum.setValue(listaSexo.get(chave));
        	lista.add(novoEnum);
        }
        return lista;
    }
}
