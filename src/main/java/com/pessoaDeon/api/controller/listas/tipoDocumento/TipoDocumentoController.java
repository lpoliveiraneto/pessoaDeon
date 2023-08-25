package com.pessoaDeon.api.controller.listas.tipoDocumento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.listas.TipoDocumento;
import com.pessoaDeon.domain.repository.listas.tipoDocumento.TipoDocumentoRepository;

@RestController
@RequestMapping("api/v1/lista/tipoDocumento")
public class TipoDocumentoController {

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@GetMapping
	public List<TipoDocumento> listarTipoDoc(){
		return tipoDocumentoRepository.findAll();
	}
}
