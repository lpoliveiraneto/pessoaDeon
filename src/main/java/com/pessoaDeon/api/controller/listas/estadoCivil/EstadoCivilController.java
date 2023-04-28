package com.pessoaDeon.api.controller.listas.estadoCivil;

import com.pessoaDeon.domain.model.enumeration.EstadoCivil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/estadoCivil")
@CrossOrigin(origins= "*")
public class EstadoCivilController {

    @GetMapping
    public Map<String, String> listarEstadoCivil(){
        List<EstadoCivil> listEnum = Arrays.asList(EstadoCivil.values());
        Map<String, String> listaEstadoCivil = new HashMap<>();
        listEnum.forEach(e ->{
            listaEstadoCivil.put(e.toString(), e.getDescricao());
        });
        return listaEstadoCivil;
    }
}
