package com.pessoaDeon.api.controller.impressao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.service.bo.BoService;

@RestController
public class BoImpressao {
	
	@Value("${spring.profiles.active}")
	private String modoSistema;
	
	@Autowired
	private BoService boService;
	
//	@Autowired
//	private BoImpressaoService boImpressaoService;
	
//	@GetMapping(value = "/gerarBoImpressao/{idBo}", produces = MediaType.APPLICATION_PDF_VALUE)
//	public ResponseEntity<?> gerarBo(@PathVariable Integer idBo){
//		Optional<BoDeon> bo = boService.findById(idBo);
//		if (bo.isPresent()) {
//			ByteArrayInputStream bais = boImpressaoService.gerarBoImpressao(bo.get(), modoSistema);
//		}
//		return null;
//	}

}
