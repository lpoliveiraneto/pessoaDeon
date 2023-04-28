package com.pessoaDeon.api.controller.listas.escolaridade;

import com.pessoaDeon.domain.model.enumeration.Escolaridade;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/escolaridades")
@CrossOrigin(origins= "*")
public class EscolaridadeController {

    @GetMapping
    public Map<String, String> listarEscolaridade(){
        List<Escolaridade> listaEnum = Arrays.asList(Escolaridade.values());
        Map<String, String> listaEscolaridade = new HashMap<>();
        listaEnum.forEach(e -> {
            listaEscolaridade.put(e.toString(), e.getDescricao());
        });
        return listaEscolaridade;
    }
}
