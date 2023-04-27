package com.pessoaDeon.api.controller.listas.deficiencia;

import com.pessoaDeon.domain.model.enumeration.Deficiencia;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/deficiencias")
public class DeficienciaController {

    @GetMapping
    public Map<String, String> listarDeficiencias(){
        List<Deficiencia> listaEnum = Arrays.asList(Deficiencia.values());
        Map<String, String> listaDeficiencias = new HashMap<>();
        listaEnum.forEach(d -> {
            listaDeficiencias.put(d.toString(), d.getDescricao());
        });
        return listaDeficiencias;
    }
}
