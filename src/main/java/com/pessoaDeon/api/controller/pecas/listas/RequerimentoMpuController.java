package com.pessoaDeon.api.controller.pecas.listas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pessoaDeon.domain.model.pecas.TituloRequerimentoMpu;
import com.pessoaDeon.domain.service.pecas.PerguntaRespostaService;

@RestController
@RequestMapping("/api/v1/listas/requerimento")
public class RequerimentoMpuController {

	@Autowired
    private PerguntaRespostaService perguntaRespostaService;
	
	@GetMapping
    public List<TituloRequerimentoMpu> listarTituloAndRespostas(){ 
    	return perguntaRespostaService.listaTituloRequerimento();
    }
}
