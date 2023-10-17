package com.pessoaDeon.api.controller.analista;

import com.pessoaDeon.domain.model.dto.BosAnalisadosResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.dto.BosPendentesResponseDto;
import com.pessoaDeon.domain.service.bo.BoService;

@RestController
@RequestMapping("/api/v1/ocorrencia")
public class AnalistaController {

    @Autowired
    private BoService boService;

    @GetMapping
    public Page<BosPendentesResponseDto> listarOcorrenciasParaAnalise(
    		@PageableDefault(size = 10, page = 0, sort = "idBo", 
    		direction = Sort.Direction.ASC)Pageable pageable){
        return boService.getBosPendentes(pageable);
    }

    @GetMapping("analisadas")
    public ResponseEntity<BosAnalisadosResponseDto> listarOcorrenciasAnalisadas(
            @PageableDefault(size = 10, page = 0, sort = "idBo",
                    direction = Sort.Direction.ASC)Pageable pageable){

        System.out.println("testando rota");
        return ResponseEntity.ok().build();
    }

    @GetMapping("funcionario/analisadas")
    public ResponseEntity<?>  listarOcorrenciasAnalisadasFuncionario(){
        return ResponseEntity.ok().build();
    }
}
