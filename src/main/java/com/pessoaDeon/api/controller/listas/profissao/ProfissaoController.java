package com.pessoaDeon.api.controller.listas.profissao;

import com.pessoaDeon.domain.model.Profissao;
import com.pessoaDeon.domain.repository.listas.profissão.ProfissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/profissoes")
public class ProfissaoController{
    @Autowired
    private ProfissaoRepository profissaoRepository;

    @GetMapping
    public List<Profissao> listarProfissoes(){
        return profissaoRepository.findAll();
    }
}
