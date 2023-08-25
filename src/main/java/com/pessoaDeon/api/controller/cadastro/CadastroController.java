package com.pessoaDeon.api.controller.cadastro;

import com.pessoaDeon.domain.model.pessoa.Pessoa;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.dto.CadastroRequestDto;
import com.pessoaDeon.domain.service.CadastroService;

@RestController
@RequestMapping("api/v1/cadastro")
public class CadastroController {
	
	@Autowired
	private CadastroService cadastroService;
	
	@PostMapping("/salvar")
	public ResponseEntity<Pessoa> salvarCadastroPessoa(@RequestBody @Valid CadastroRequestDto cadastroDto){
		System.out.println("entrei no método de salvar");
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroService.salvar(cadastroDto));
	}
	
}