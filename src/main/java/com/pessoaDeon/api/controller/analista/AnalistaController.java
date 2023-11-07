package com.pessoaDeon.api.controller.analista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort;
import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.model.dto.UsuariosPendentesResponseDto;
import com.pessoaDeon.domain.model.dto.analista.AnalistaReturnDto;
import com.pessoaDeon.domain.model.dto.analista.AnalistaSigmaDeonDto;
import com.pessoaDeon.domain.service.UsuarioService;
import com.pessoaDeon.domain.service.analista.AnalistaService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/analista")
public class AnalistaController {
    @Autowired
    AnalistaService analistaService;
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/salvarAnalista")
    public Analista salvarAnalista(@RequestBody AnalistaSigmaDeonDto analistaRequest, HttpServletRequest http) {
        return analistaService.salvarAnalista(analistaRequest.getAnalistaRequest(), http, analistaRequest.getAnalistaResponseDto());
    }
    
    @GetMapping("/verificaFunc")
    public AnalistaReturnDto buscaFuncSigma(@RequestParam(value = "cpf") String cpf, HttpServletRequest http) {
    	return analistaService.verificaAnalista(cpf, http);
    }
    
    @GetMapping("/listaUsuariosAnalise")
    public Page<UsuariosPendentesResponseDto> usuariosAnalise(
    		@PageableDefault(size = 10, page = 0, sort = "idUsuario",
            direction = Sort.Direction.ASC)Pageable pageable){
    	return usuarioService.getUsuariosAnalise(pageable);
    }
}
