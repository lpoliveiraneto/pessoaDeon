package com.pessoaDeon.api.controller.pecas.listas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.dto.pecas.PerguntaRespostaDto;
import com.pessoaDeon.domain.model.pecas.TipoPeca;
import com.pessoaDeon.domain.service.pecas.PerguntaRespostaService;

@RestController
@RequestMapping("/api/v1/listas/formulario")
public class FormularioRiscoController {

	@Autowired
    private PerguntaRespostaService perguntaRespostaService;
	
	@GetMapping
    public PerguntaRespostaDto listarPerguntas(@RequestParam(value = "id") TipoPeca id, @RequestParam(value = "bloco") Integer bloco){
        return perguntaRespostaService.listaResposta(id, bloco);
    }   
}
