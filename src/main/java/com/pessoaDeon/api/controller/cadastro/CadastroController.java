package com.pessoaDeon.api.controller.cadastro;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pessoaDeon.domain.model.dto.CadastroRequestDto;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.service.CadastroService;
import com.pessoaDeon.domain.service.VerificacaoContaService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/cadastro")
public class CadastroController {
	
	@Autowired
	private CadastroService cadastroService;
	
	@Autowired
	private VerificacaoContaService contaService;
	
	@PostMapping("/salvar")
	public ResponseEntity<?> salvarCadastroPessoa(
			@RequestPart("cadastro") CadastroRequestDto cadastro, 
			@RequestPart("files") MultipartFile[] files){
		System.out.println("entrei no método de salvar");
		Pessoa pessoa = cadastroService.salvar(cadastro, files);
		if (pessoa != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("cadastro efetuado com sucesso!");
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("não foi possivel efetuar o cadastro no momento!");
	}
	
	@GetMapping("/verifyAccount")
	public ResponseEntity<?> ativaConta(@RequestParam(name = "codigo") String codigo){
		return contaService.ativarConta(codigo);
	}
	
	@GetMapping("/resendVerifyToken")
	public ResponseEntity<?> reenviarCodigoVerificacao(@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "numeroDocumento", required = true) String numeroDocumento){
		return contaService.reenviarCodigoVerificacao(email, numeroDocumento);
	}
	
	/**
     * @author Hilberto
     * @param idPessoa
     * @return retorna os dados de Cadastro para o analista
     */
	@GetMapping("/pessoaPorId/{idPessoa}")
	public ResponseEntity<?> listarPessoaPorId(@PathVariable (value = "idPessoa") Integer idPessoa){
		return ResponseEntity.ok().body(cadastroService.listarCadastroPessoa(idPessoa));
	}
	
	@GetMapping("/{idPessoa}")
    public ResponseEntity<?> baixarArquivo(@PathVariable Integer idPessoa,
    		HttpServletResponse response) throws IOException {
        List<String> arquivos = cadastroService.carregarArquivo(idPessoa);
        if (arquivos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum anexo encontrado!");
		}
        return ResponseEntity.ok(arquivos);
    }
	
	@PostMapping("/salvarFotoPerfil/{idPessoa}")
	public ResponseEntity<?> salvarFotoPerfil(@PathVariable Integer idPessoa, 
			@RequestParam(name = "foto") MultipartFile foto){
		var x = cadastroService.salvarFotoPerfil(idPessoa, foto);
		return ResponseEntity.ok(x);
	}
	
	@GetMapping("/getFotoPerfil/{idPessoa}")
	public ResponseEntity<?> getFotoPerfil(Integer idPessoa){
		String imgBase64 = cadastroService.carregarFotoPerfil(idPessoa);
		if (imgBase64 == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum arquivo encontrado!");
		}
		return ResponseEntity.ok(imgBase64);
	}
	
}