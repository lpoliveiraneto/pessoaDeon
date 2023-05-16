package com.pessoaDeon.api.controller.listas.estadoCivil;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.enumeration.EstadoCivil;
import com.pessoaDeon.domain.model.util.EnumToObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1/estadoCivil")
public class EstadoCivilController {

	@GetMapping
	public List<EnumToObject> listarEstadoCivil(){
		List<EstadoCivil> list = Arrays.asList(EstadoCivil.values());
		Map<String, String> listaEstadoCivil = new HashMap<>();
		list.forEach(lista -> {
			listaEstadoCivil.put(lista.toString(), lista.getDescricao());
		});
		
		List<EnumToObject> lista = new ArrayList<>();
        for(String chave : listaEstadoCivil.keySet()) {
        	EnumToObject novoEnum = new EnumToObject();
        	novoEnum.setKey(chave);
        	novoEnum.setValue(listaEstadoCivil.get(chave));
        	lista.add(novoEnum);
        }
        return lista;
	}
}
