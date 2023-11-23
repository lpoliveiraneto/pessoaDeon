package com.pessoaDeon.api.controller.ocorrencia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/violenciaDomestica")
public class OcorrenciaViolenciaDomestica {

    // @Autowired
    // private BoAnaliseService boAnaliseService;

    @GetMapping
    public ResponseEntity<?> listarOcorrenciaViolenciaDomestica(){
        System.out.println("testando a rota de violencia domestica");
        return ResponseEntity.ok().build();
    }
}
