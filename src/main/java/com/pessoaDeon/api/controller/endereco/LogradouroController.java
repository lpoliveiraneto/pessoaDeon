package com.pessoaDeon.api.controller.endereco;

import com.pessoaDeon.domain.model.endereco.Logradouro;
import com.pessoaDeon.domain.service.LogradouroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lista/logradouro")
public class LogradouroController {

    @Autowired
    LogradouroService logradouroService;

    @GetMapping("/{cep}")
    public ResponseEntity<?> getBuscaPorCep(@PathVariable String cep){
        Logradouro logradouro = logradouroService.getByCep(cep);
        return ResponseEntity.ok().body(logradouro);
    }

}
