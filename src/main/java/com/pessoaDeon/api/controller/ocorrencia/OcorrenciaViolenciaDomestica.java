package com.pessoaDeon.api.controller.ocorrencia;

import com.pessoaDeon.domain.model.dto.bo.BosAnalisadosResponseDto;
import com.pessoaDeon.domain.model.dto.bo.BosPendentesResponseDto;
import com.pessoaDeon.domain.model.enumeration.TipoPesquisa;
import com.pessoaDeon.domain.service.analista.BoAnaliseService;
import com.pessoaDeon.domain.service.bo.BoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/violenciaDomestica")
public class OcorrenciaViolenciaDomestica {

    @Autowired
    private BoService boService;
     @Autowired
     private BoAnaliseService boAnaliseService;

    @GetMapping
    public Page<BosPendentesResponseDto> listarOcorrenciasParaAnalise(
            @PageableDefault(size = 10, page = 0, sort = "idBo",
                    direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(name = "tipoPesquisa", required = false) TipoPesquisa tipoPesquisa,
            @RequestParam(name = "parametro", required = false) String parametro){
        String perfil = "mulher";
        return boService.getBosPendentes(pageable, tipoPesquisa, parametro, perfil);
    }

    @GetMapping("/analisadas")
    public Page<BosAnalisadosResponseDto> listarBosAnalisados(
            @PageableDefault(size = 10, page = 0, sort = "idBo",
                    direction = Sort.Direction.ASC)Pageable pageable){

        return boAnaliseService.getBoAnalisadosViolenciaDomestica(pageable);
    }
//    @GetMapping
//    public ResponseEntity<?> listarOcorrenciaViolenciaDomestica(){
//        System.out.println("testando a rota de violencia domestica");
//        return ResponseEntity.ok().build();
//    }
}
