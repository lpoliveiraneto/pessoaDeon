package com.pessoaDeon.api.controller.pecas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.service.pecas.PecaService;

@RestController
@RequestMapping("/api/v1/pecas")
public class PecaController {
    
	@Autowired
	private PecaService pecaService;

//	public void salvarPeca(Peca peca) {
		//pecaService.salvarPeca(peca);
//	}
}
