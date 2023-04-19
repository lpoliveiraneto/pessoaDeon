package com.pessoaDeon.api.controller;

import com.pessoaDeon.domain.model.Endereco;
import com.pessoaDeon.domain.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> listarEnderecos(){
        return enderecoService.listarEnderecos();
    }
}
