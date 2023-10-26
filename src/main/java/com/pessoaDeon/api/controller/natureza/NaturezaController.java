package com.pessoaDeon.api.controller.natureza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.dto.NaturezaDeonRequestDto;
import com.pessoaDeon.domain.service.natureza.NaturezaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/natureza")
public class NaturezaController {

	@Autowired
	private NaturezaService naturezaService;
	
	@GetMapping("/listNaturezaSigma")
	public ResponseEntity<?> lista(@RequestParam(value = "nome" , required = false) String nome, @PageableDefault(size = 10, page = 0, sort = "nome", direction = Direction.ASC) Pageable pageable){
		var naturezas = naturezaService.listNatureza(pageable, nome);
		if(!naturezas.isEmpty()) {
			return new ResponseEntity<>(naturezas, HttpStatus.OK);			
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/naturezaSigma/{idNatureza}")
	public ResponseEntity<?> listarNaturezaSigmaAdminPorId(@PathVariable(value = "idNatureza") Integer idNatureza){
		return ResponseEntity.ok(naturezaService.buscarNaturezaSigma(idNatureza));
	}
	
	@PostMapping("/salvarNaturezaDeon")
	public ResponseEntity<?> salvarNaturezaDeon(@RequestBody @Valid NaturezaDeonRequestDto natDto, Errors erros){
		if(erros.hasErrors())
			return ResponseEntity.status(HttpStatus.CONFLICT).body(erros.getFieldErrors());
		if(!naturezaService.existeNatureza(natDto)) {
			var naturezaDeon = naturezaService.salvar(natDto);
			if (naturezaDeon != null) {
				return ResponseEntity.ok().body("Natureza salva com sucesso!");			
			}
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Natureza já existe");
	}
	
	@GetMapping("/filtroNome")
	public ResponseEntity<?> filtrarNome(@RequestParam("nome") String nome, Pageable pageable){
		return ResponseEntity.ok(naturezaService.filtroNome(nome, pageable));
	}

	@GetMapping("/filtroGlossario")
	public ResponseEntity<?> filtrarGlossario(@RequestParam("glossario") String glossario, Pageable pageable){
		return ResponseEntity.ok(naturezaService.filtroGlossario(glossario, pageable));
	}
	
	@GetMapping("/listarNatDeon")
	public ResponseEntity<?> listar(){
		return naturezaService.listarNaturezaDeonAtiva();
	}

	@GetMapping("/listaNaturezas")
	public ResponseEntity<?> listaNaturezas(){
		return naturezaService.listaNaturezasFront();
	}
}
