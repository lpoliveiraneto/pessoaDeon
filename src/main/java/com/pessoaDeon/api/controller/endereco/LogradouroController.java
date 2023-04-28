package com.pessoaDeon.api.controller.endereco;

import com.pessoaDeon.domain.model.Logradouro;
import com.pessoaDeon.domain.service.LogradouroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/logradouro")
@CrossOrigin(origins= "*")
public class LogradouroController {

    @Autowired
    LogradouroService logradouroService;

    @GetMapping("/{cep}")
    public ResponseEntity getBuscaPorCep(@PathVariable String cep){
        Logradouro logradouro = logradouroService.getByCep(cep);

        return ResponseEntity.ok().body(logradouro);

    }

}
