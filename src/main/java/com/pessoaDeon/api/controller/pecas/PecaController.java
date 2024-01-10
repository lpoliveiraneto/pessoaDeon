package com.pessoaDeon.api.controller.pecas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.dto.pecas.PerguntaRespostaDto;
import com.pessoaDeon.domain.model.pecas.TipoPeca;
import com.pessoaDeon.domain.service.pecas.PerguntaRespostaService;

@RestController
@RequestMapping("/api/v1/pecas")
public class PecaController {
    
    @Autowired
    private PerguntaRespostaService perguntaRespostaService;

    @GetMapping
    public PerguntaRespostaDto listarPerguntas(@RequestParam(value = "id") TipoPeca id, @RequestParam(value = "bloco") Integer bloco){
        return perguntaRespostaService.listaResposta(id, bloco);
    }
}
