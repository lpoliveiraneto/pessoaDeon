package com.pessoaDeon.api.controller.cadastro;

import com.pessoaDeon.domain.model.pessoa.Pessoa;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.dto.CadastroRequestDto;
import com.pessoaDeon.domain.service.CadastroService;
import com.pessoaDeon.domain.service.VerificacaoContaService;

@RestController
@RequestMapping("api/v1/cadastro")
public class CadastroController {
	
	@Autowired
	private CadastroService cadastroService;
	
	@Autowired
	private VerificacaoContaService contaService;
	
	@PostMapping("/salvar")
	public ResponseEntity<Pessoa> salvarCadastroPessoa(@RequestBody @Valid CadastroRequestDto cadastroDto){
		System.out.println("entrei no método de salvar");
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroService.salvar(cadastroDto));
	}
	
//	@GetMapping("/testeEnvioEmail")
//	public ResponseEntity<?> testeEnvioEmail(@RequestParam(name = "email") String email){
//		cadastroService.testeEnvioEmail(email); 
//		return ResponseEntity.ok("envio realizado com sucesso!");
//	}
	
	@GetMapping("/verifyAccount")
	public ResponseEntity<?> ativaConta(@RequestParam(name = "codigo") String codigo){
		return contaService.ativarConta(codigo);
	}
	
	@GetMapping("/resendVerifyToken")
	public ResponseEntity<?> reenviarCodigoVerificacao(@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "numeroDocumento", required = true) String numeroDocumento){
		return contaService.reenviarCodigoVerificacao(email, numeroDocumento);
	}
	
}