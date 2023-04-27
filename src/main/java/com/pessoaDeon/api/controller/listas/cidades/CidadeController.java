package com.pessoaDeon.api.controller.listas.cidades;

import com.pessoaDeon.domain.model.Cidade;
import com.pessoaDeon.domain.repository.listas.cidade.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> listarCidades(){
        return cidadeRepository.findAll();
    }
}
