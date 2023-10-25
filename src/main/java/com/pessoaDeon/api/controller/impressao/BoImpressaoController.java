package com.pessoaDeon.api.controller.impressao;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.config.security.TokenService;
import com.pessoaDeon.domain.service.impressao.BoImpressaoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BoImpressaoController {
	
	@Value("${spring.profiles.active}")
	private String modoSistema;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private BoImpressaoService boImpressaoService;
	
	@GetMapping(value = "api/v1/gerarBoImpressao/{tokenBoSigma}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<?> gerarBo(@PathVariable String tokenBoSigma, HttpServletRequest request){
		var idBoSigma = tokenService.getSubject(tokenBoSigma);
		ByteArrayInputStream bais = boImpressaoService.getPdfImpressao(idBoSigma, request);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
	}

}
