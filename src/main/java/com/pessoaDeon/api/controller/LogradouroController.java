package com.pessoaDeon.api.controller;

import com.pessoaDeon.domain.model.Logradouro;
import com.pessoaDeon.domain.service.LogradouroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/logradouro")
public class LogradouroController {

    @Autowired
    LogradouroService logradouroService;

    @GetMapping("/{cep}")
    public ResponseEntity getBuscaPorCep(@PathVariable String cep){
        Logradouro logradouro = logradouroService.getLogradouroByCep(cep);

        return ResponseEntity.ok().body(logradouro);

    }

}
