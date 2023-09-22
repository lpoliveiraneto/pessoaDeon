package com.pessoaDeon.api.controller.analista;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/ocorrencia")
public class AnalistaController {

    @GetMapping
    public ResponseEntity listarOcorrenciasParaAnalise(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("analisadas")
    public ResponseEntity listarOcorrenciasAnalisadas(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("funcionario/analisadas")
    public ResponseEntity  listarOcorrenciasAnalisadasFuncionario(){
        return ResponseEntity.ok().build();
    }
}
