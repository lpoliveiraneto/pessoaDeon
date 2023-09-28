package com.pessoaDeon.api.controller.analista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.service.bo.BoService;
import com.pessoaDeon.domain.service.envolvido.EnvolvidoService;
import com.pessoaDeon.domain.service.integracao.IntegracaoService;

@RestController
@RequestMapping("/api/v1/analiseBo")
public class AnaliseBoController {

	@Value("${url.api-integracao}")
	private String URL;
	
	@Autowired
	private BoService boService;
	
	@Autowired
	private IntegracaoService integracaoService;
	
	@Autowired
	private EnvolvidoService envolvidoService;
	
	@GetMapping
	public ResponseEntity<?> enviarBoSigma(@RequestParam("idBo") Integer idBo){
		var bo = boService.buscarBoPorId(idBo);
		var envolvidos = envolvidoService.getEnvolvidoBo(idBo);
		return ResponseEntity.status(HttpStatus.OK).body(envolvidos);
	}
	
}
