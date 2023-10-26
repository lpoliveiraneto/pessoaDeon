package com.pessoaDeon.api.controller.analista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.model.dto.BosAnalisadosResponseDto;
import com.pessoaDeon.domain.model.dto.BosPendentesResponseDto;
import com.pessoaDeon.domain.model.dto.analista.AnalistaRequest;
import com.pessoaDeon.domain.service.analista.AnalistaService;
import com.pessoaDeon.domain.service.analista.BoAnaliseService;
import com.pessoaDeon.domain.service.bo.BoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/ocorrencia")
public class AnalistaController {

    @Autowired
    private BoService boService;

    @Autowired
    private BoAnaliseService boAnaliseService;
    
    @Autowired
    private AnalistaService analistaService;

    @GetMapping
    public Page<BosPendentesResponseDto> listarOcorrenciasParaAnalise(
    		@PageableDefault(size = 10, page = 0, sort = "idBo", 
    		direction = Sort.Direction.ASC)Pageable pageable){
        return boService.getBosPendentes(pageable);
    }

    @GetMapping("/todos")
    public Page<BosAnalisadosResponseDto> listarBosAnaliseTodos(
            @PageableDefault(size = 10, page = 0, sort = "idBo",
                    direction = Sort.Direction.ASC)Pageable pageable){

        return boAnaliseService.getBoAnalise(pageable);
    }

    @GetMapping("/analisadas")
    public Page<BosAnalisadosResponseDto> listarBosAnalisados(
            @PageableDefault(size = 10, page = 0, sort = "idBo",
                    direction = Sort.Direction.ASC)Pageable pageable){

        return boAnaliseService.getBoAnalisados(pageable);
    }

    @GetMapping("/emAnalise")
    public Page<BosAnalisadosResponseDto> listarBosEmAnalise(
            @PageableDefault(size = 10, page = 0, sort = "idBo",
                    direction = Sort.Direction.ASC)Pageable pageable){
        return boAnaliseService.getBoEmAnalise(pageable);
    }


    @GetMapping("/funcionario/analisadas")
    public ResponseEntity<?>  listarOcorrenciasAnalisadasFuncionario(){
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/salvarAnalista")
    public Analista salvarAnalista(@RequestBody AnalistaRequest analistaRequest, HttpServletRequest http) {
    	return analistaService.salvarAnalista(analistaRequest, http);
    }
}
