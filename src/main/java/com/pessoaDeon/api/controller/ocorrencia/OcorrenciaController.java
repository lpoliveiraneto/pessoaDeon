package com.pessoaDeon.api.controller.ocorrencia;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.dto.bo.BoAnaliseRequest;
import com.pessoaDeon.domain.model.dto.bo.BosAnalisadosResponseDto;
import com.pessoaDeon.domain.model.dto.bo.BosPendentesResponseDto;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.enumeration.TipoPesquisa;
import com.pessoaDeon.domain.service.analista.BoAnaliseService;
import com.pessoaDeon.domain.service.bo.BoService;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/ocorrencia")
public class OcorrenciaController {

    @Autowired
    private BoService boService;

    @Autowired
    private BoAnaliseService boAnaliseService;
    
    @Autowired
    private EntityManager entityManager;
    
    @GetMapping
    public Page<BosPendentesResponseDto> listarOcorrenciasParaAnalise(
    		@PageableDefault(size = 10, page = 0, sort = "idBo", 
    		direction = Sort.Direction.ASC)Pageable pageable,
    		@RequestParam(name = "tipoPesquisa", required = false) TipoPesquisa tipoPesquisa,
    		@RequestParam(name = "parametro", required = false) String parametro){
        String perfil = "analista";
        return boService.getBosPendentes(pageable, tipoPesquisa, parametro, perfil);
    }
    
    @GetMapping("/pesquisarAnalisadas")
    public Page<BosAnalisadosResponseDto> pesquisarOcorrenciasAnalisadas(
    		@PageableDefault(size = 10, page = 0, sort = "idBo", 
    		direction = Sort.Direction.ASC)Pageable pageable,
    		@RequestParam(name = "tipoPesquisa", required = false) TipoPesquisa tipoPesquisa,
    		@RequestParam(name = "parametro", required = false) String parametro){
        String perfil = "analista";
        return boService.pesquisaBosAnalisados(pageable, tipoPesquisa, parametro, perfil);
    }
    

    @GetMapping("/todos")
    public Page<BosAnalisadosResponseDto> listarBosAnaliseTodos(
            @PageableDefault(size = 10, page = 0, sort = "idBo",
                    direction = Sort.Direction.ASC)Pageable pageable){

        return boAnaliseService.getBoAnalise(pageable);
    }
    
    /**
     * @author hilbertofilho
     * @return esse metodo é chamado quando o analista pega um bo para fazer a analise
     * */
    @PostMapping("/analisarBo")
	public ResponseEntity<?> analiseBo(@RequestBody BoAnaliseRequest analiseRequest, HttpServletRequest request, @RequestParam(value = "status") Status status){
		boAnaliseService.salvarBoEmAnalise(analiseRequest,request, status);
		return ResponseEntity.ok().build();
    }

    /**
     * @author hilbertofilho
     *@return retorna a lista de bos que por algum motivo não foi concluída a analise e está sob a posse de algum analista
     */
    @GetMapping("/emAnalise")
    public Page<BosAnalisadosResponseDto> listarBosEmAnalise(
    		@PageableDefault(size = 10, page = 0, sort = "idBo",
    		direction = Sort.Direction.ASC)Pageable pageable){
    	return boAnaliseService.findByStatusFalseNotViolenciaDomestica(entityManager,pageable);
    }
    
    
    @PostMapping("/recusarOcorrencia")
	public void recusarOcorrencia(@RequestBody BoAnaliseRequest boRequest, @RequestParam(name = "id") Integer idResposta, HttpServletRequest request){
    	boAnaliseService.recusarBoEmAnalise(boRequest, idResposta, request);
	}
    
    /**
     * @author hilbertofilho
     * Este metodo não esta sendo utilizado para retornar a lista de bos analisados
     * */
    @GetMapping("/analisadas")
    public Page<BosAnalisadosResponseDto> listarBosAnalisados(
            @PageableDefault(size = 10, page = 0, sort = "idBo",
                    direction = Sort.Direction.ASC)Pageable pageable){

        return boAnaliseService.findByStatusTrueNotViolenciaDomestica(entityManager, pageable);
    }
}
