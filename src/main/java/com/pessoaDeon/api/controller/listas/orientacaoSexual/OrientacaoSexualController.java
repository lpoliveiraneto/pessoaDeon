package com.pessoaDeon.api.controller.listas.orientacaoSexual;

import com.pessoaDeon.domain.model.enumeration.OrientacaoSexual;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/orientacaoSexual")
@CrossOrigin(origins= "*")
public class OrientacaoSexualController {

    @GetMapping
    public Map<String, String> listarOrientacaoSexual(){
        List<OrientacaoSexual> listaEnum = Arrays.asList(OrientacaoSexual.values());
        Map<String, String> listaOrientacao = new HashMap<>();
        listaEnum.forEach(o->{
            listaOrientacao.put(o.toString(), o.getDescricao());
        });
        return listaOrientacao;
    }
}
