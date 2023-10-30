package com.pessoaDeon.api.controller.analista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.model.dto.analista.AnalistaReturnDto;
import com.pessoaDeon.domain.model.dto.analista.AnalistaSigmaDeonDto;
import com.pessoaDeon.domain.service.analista.AnalistaService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/analista")
public class AnalistaController {
    @Autowired
    AnalistaService analistaService;

    @PostMapping("/salvarAnalista")
    public Analista salvarAnalista(@RequestBody AnalistaSigmaDeonDto analistaRequest, HttpServletRequest http) {
        return analistaService.salvarAnalista(analistaRequest.getAnalistaRequest(), http, analistaRequest.getAnalistaResponseDto());
    }
    
    @GetMapping("/verificaFunc")
    public AnalistaReturnDto buscaFuncSigma(@RequestParam(value = "cpf") String cpf, HttpServletRequest http) {
    	return analistaService.verificaAnalista(cpf, http);
    }
}
