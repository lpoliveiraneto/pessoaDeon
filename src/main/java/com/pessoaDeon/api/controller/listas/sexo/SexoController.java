package com.pessoaDeon.api.controller.listas.sexo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.enumeration.Sexo;

@RestController
@RequestMapping("api/v1/sexo")
public class SexoController {

	@GetMapping
    public Map<String, String> listarDeficiencias(){
        List<Sexo> listaEnum = Arrays.asList(Sexo.values());
        Map<String, String> listaSexo = new HashMap<>();
        listaEnum.forEach(lista -> {
        	listaSexo.put(lista.toString(), lista.getDescricao());
        });
        return listaSexo;
    }
}
