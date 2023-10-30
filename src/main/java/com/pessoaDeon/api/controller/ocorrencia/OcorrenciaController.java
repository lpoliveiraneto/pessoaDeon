package com.pessoaDeon.api.controller.ocorrencia;

import com.pessoaDeon.domain.model.dto.bo.BoAnaliseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pessoaDeon.domain.model.dto.bo.BosAnalisadosResponseDto;
import com.pessoaDeon.domain.model.dto.bo.BosPendentesResponseDto;
import com.pessoaDeon.domain.model.enumeration.TipoPesquisa;
import com.pessoaDeon.domain.service.analista.AnalistaService;
import com.pessoaDeon.domain.service.analista.BoAnaliseService;
import com.pessoaDeon.domain.service.bo.BoService;


@RestController
@RequestMapping("/api/v1/ocorrencia")
public class OcorrenciaController {

    @Autowired
    private BoService boService;

    @Autowired
    private BoAnaliseService boAnaliseService;
    
    @Autowired
    private AnalistaService analistaService;

    @GetMapping
    public Page<BosPendentesResponseDto> listarOcorrenciasParaAnalise(
    		@PageableDefault(size = 10, page = 0, sort = "idBo", 
    		direction = Sort.Direction.ASC)Pageable pageable,
    		@RequestParam(name = "tipoPesquisa", required = false) TipoPesquisa tipoPesquisa,
    		@RequestParam(name = "parametro", required = false) String parametro){
        return boService.getBosPendentes(pageable, tipoPesquisa, parametro);
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

    @PostMapping("/emAnalise")
    public ResponseEntity salvarBoAnalise(@RequestBody BoAnaliseRequest boAnaliseRequest){
        System.out.println("irei salvar na tabela BoAnalise");
        boAnaliseService.salvarBoEmAnalise(boAnaliseRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/emAnalise")
    public ResponseEntity salvarRespostaBoAnalise(@RequestBody BoAnaliseRequest boAnaliseRequest){
        boAnaliseService.salvarRespostaBoEmAnalise(boAnaliseRequest);
       // System.out.println("testando rota.");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/funcionario/analisadas")
    public ResponseEntity<?>  listarOcorrenciasAnalisadasFuncionario(){
        return ResponseEntity.ok().build();
    }
    

}
