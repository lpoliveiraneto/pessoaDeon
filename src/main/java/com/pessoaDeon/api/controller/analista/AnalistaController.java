package com.pessoaDeon.api.controller.analista;

import com.pessoaDeon.domain.model.dto.BosPendentesResponseDto;
import com.pessoaDeon.domain.service.bo.BoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("api/v1/ocorrencia")
public class AnalistaController {

    @Autowired
    private BoService boService;

    @GetMapping
    public List<BosPendentesResponseDto> listarOcorrenciasParaAnalise(){
        return boService.getBosPendentes();
    }

    @GetMapping("analisadas")
    public ResponseEntity<?> listarOcorrenciasAnalisadas(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("funcionario/analisadas")
    public ResponseEntity<?>  listarOcorrenciasAnalisadasFuncionario(){
        return ResponseEntity.ok().build();
    }
}
