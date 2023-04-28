package com.pessoaDeon.api.controller.listas.identidadeGenero;

import com.pessoaDeon.domain.model.enumeration.IdentidadeGenero;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/identidadeGenero")
@CrossOrigin(origins= "*")
public class IdentidadeGeneroController {

    @GetMapping
    public Map<String, String> listarIdentidadeGenero(){
        List<IdentidadeGenero> listaEnum = Arrays.asList(IdentidadeGenero.values());
        Map<String, String> listaIdentidadeGenero = new HashMap<>();
        listaEnum.forEach(i ->{
            listaIdentidadeGenero.put(i.toString(), i.getDescricao());
        });
        return listaIdentidadeGenero;
    }
}
