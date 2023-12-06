package com.pessoaDeon.api.controller.ocorrencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.dto.bo.BoAnaliseRequest;
import com.pessoaDeon.domain.model.dto.bo.BosAnalisadosResponseDto;
import com.pessoaDeon.domain.model.dto.bo.BosPendentesResponseDto;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.enumeration.TipoPesquisa;
import com.pessoaDeon.domain.service.analista.BoAnaliseService;
import com.pessoaDeon.domain.service.bo.BoService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/ocorrencia")
public class OcorrenciaController {

    @Autowired
    private BoService boService;

    @Autowired
    private BoAnaliseService boAnaliseService;
    
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

    @PostMapping("/analisarBo")
	public ResponseEntity<?> analiseBo(@RequestBody BoAnaliseRequest analiseRequest, HttpServletRequest request, @RequestParam(value = "status") Status status){
		boAnaliseService.salvarBoEmAnalise(analiseRequest,request, status);
		return ResponseEntity.ok().build();
    }

    @PutMapping("/emAnalise")
    public ResponseEntity<?> salvarRespostaBoAnalise(@RequestBody BoAnaliseRequest boAnaliseRequest){
        boAnaliseService.salvarRespostaBoEmAnalise(boAnaliseRequest);
       // System.out.println("testando rota.");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/funcionario/analisadas")
    public ResponseEntity<?>  listarOcorrenciasAnalisadasFuncionario(){
        return ResponseEntity.ok().build();
    }
}
