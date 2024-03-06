package com.pessoaDeon.api.controller.analista;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.model.dto.analista.AnalistaResponseDto;
import com.pessoaDeon.domain.model.dto.analista.AnalistaReturnDto;
import com.pessoaDeon.domain.model.dto.analista.AnalistaSigmaDeonDto;
import com.pessoaDeon.domain.service.analista.AnalistaService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/analista")
public class AnalistaController {
    @Autowired
    AnalistaService analistaService;
    
    @PostMapping("/salvarAnalista")
    public Analista salvarAnalista(@RequestBody AnalistaSigmaDeonDto analistaRequest, HttpServletRequest http) {
        return analistaService.salvarAnalista(analistaRequest.getAnalistaRequest(), http, analistaRequest.getAnalistaResponseDto());
    }
    
    @GetMapping("/verificaFunc")
    public AnalistaReturnDto buscaFuncSigma(@RequestParam(value = "cpf") String cpf, HttpServletRequest http) {
    	return analistaService.verificaAnalista(cpf, http);
    }
    
    @GetMapping
    public Page<AnalistaResponseDto> analistasCadastrados(@PageableDefault(size = 10, page = 0, sort = "idAnalista",
    direction = Sort.Direction.ASC)Pageable pageable){
    	return analistaService.getAllAnalistas(pageable);
    }
    
    @PutMapping("/{idAnalista}")
    public ResponseEntity<String> adicionarPerfil(@PathVariable(value = "idAnalista") Integer idAnalista, @RequestParam List<String> perfis ) {
    	try {
    		analistaService.adicionarPerfis(idAnalista, perfis);
    		return ResponseEntity.ok("Perfil(s) adicionados ao analista");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
    }
    
    @GetMapping("/{idAnalista}")
    public AnalistaResponseDto findByAnalista(@PathVariable(name = "idAnalista") Integer idAnalista){
    	Optional<Analista> analista = analistaService.findById(idAnalista);
    	return analistaService.converteAnalista(analista.get());
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> desativar(@PathVariable(name = "id") Integer id){
    	analistaService.desativarAnalista(id);
    	return ResponseEntity.ok("Funcionario desativado!!!");
    }
}
