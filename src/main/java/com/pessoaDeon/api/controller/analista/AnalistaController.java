package com.pessoaDeon.api.controller.analista;

import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.model.dto.analista.AnalistaRequest;
import com.pessoaDeon.domain.service.analista.AnalistaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analista")
public class AnalistaController {
    @Autowired
    AnalistaService analistaService;

    @PostMapping("/salvarAnalista")
    public Analista salvarAnalista(@RequestBody AnalistaRequest analistaRequest, HttpServletRequest http) {
        return analistaService.salvarAnalista(analistaRequest, http);
    }
}
