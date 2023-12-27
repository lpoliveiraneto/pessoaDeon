package com.pessoaDeon.domain.service.analista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.exception.BoAnaliseNotFoundException;
import com.pessoaDeon.domain.exception.BoNotFoundException;
import com.pessoaDeon.domain.model.analista.BoAnalise;
import com.pessoaDeon.domain.model.dto.bo.BoAnaliseRequest;
import com.pessoaDeon.domain.model.dto.bo.BosAnalisadosResponseDto;
import com.pessoaDeon.domain.model.dto.integracao.BoRequestDto;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.repository.bo.ProtocoloRepository;
import com.pessoaDeon.domain.repository.boAnalise.BoAnaliseRepository;
import com.pessoaDeon.domain.repository.respostaBo.RespostaAnaliseBoRepository;
import com.pessoaDeon.domain.service.bo.BoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;


@Service
public class BoAnaliseService {

    @Autowired
    private BoAnaliseRepository boAnaliseRepository;
    @Autowired
    private ProtocoloRepository protocoloRepository;
    @Autowired
    private BoService boService;
    @Autowired
    private AnalistaService analistaService;
    @Autowired
    private RespostaAnaliseBoRepository respostaAnaliseBoRepository;

    @Autowired
    private EntityManager entityManager;

    public Page<BosAnalisadosResponseDto> getBoAnalise(Pageable pageable){

        List<BoAnalise> bosAnalise = boAnaliseRepository.findAll();
        List<BosAnalisadosResponseDto> bos = new ArrayList<>();
        bosAnalise.forEach(b -> {
            BosAnalisadosResponseDto bo = getBoAnalisetoBosAnalisadosResponseDto(b);
            bos.add(bo);
        });

        return new PageImpl<>(bos, pageable, bos.size());
    }

    public Page<BosAnalisadosResponseDto> getBoAnalisados(Pageable pageable){
        //List<BoAnalise> bosAnalise = boAnaliseRepository.findByStatusTrue();
        List<BoAnalise> bosAnalise = boAnaliseRepository.findByStatusFalseNotViolenciaDomestica(entityManager);
        List<BosAnalisadosResponseDto> bos = new ArrayList<>();
        bosAnalise.forEach(b ->{
            BosAnalisadosResponseDto bo = getBoAnalisetoBosAnalisadosResponseDto(b);
            bos.add(bo);
        });

        return new PageImpl<>(bos, pageable, bos.size());
    }

    public Page<BosAnalisadosResponseDto> getBoAnalisadosViolenciaDomestica(Pageable pageable){
        List<BoAnalise> bosAnalise = boAnaliseRepository.findByStatusTrueViolenciaDomestica(entityManager);
        List<BosAnalisadosResponseDto> bos = new ArrayList<>();
        bosAnalise.forEach(b ->{
            BosAnalisadosResponseDto bo = getBoAnalisetoBosAnalisadosResponseDto(b);
            bos.add(bo);
        });

        return new PageImpl<>(bos, pageable, bos.size());
    }

    public Page<BosAnalisadosResponseDto> getBoEmAnalise(Pageable pageable){
        List<BoAnalise> bosEmAnalise = boAnaliseRepository.findByStatusFalse();
        List<BosAnalisadosResponseDto> bos = new ArrayList<>();
        bosEmAnalise.forEach(b -> {
            BosAnalisadosResponseDto bo = getBoAnalisetoBosAnalisadosResponseDto(b);
            bos.add(bo);
        });
        return new PageImpl<>(bos, pageable, bos.size());
    }

    private BosAnalisadosResponseDto getBoAnalisetoBosAnalisadosResponseDto(BoAnalise b) {
        BosAnalisadosResponseDto bo = new BosAnalisadosResponseDto();
        bo.setIdBo(b.getBoDeon().getIdBo());
        bo.setProtocolo(protocoloRepository.findByBoIdBo(b.getBoDeon().getIdBo()).getNumero());
        bo.setNomeAnalista(b.getAnalista().getNome());
        var natureza = b.getBoDeon().getListaNaturezas().get(0).getNaturezaDeon();
        var codigo = ((natureza.getCodigo() != null && !natureza.getCodigo().isBlank()) ? " - " + natureza.getCodigo() : "");
        bo.setNatureza(natureza.getNome() + codigo);
        bo.setDataAnalise(b.getDataAnalise());
        bo.setDataRegistro(b.getBoDeon().getDataRegistro());
        return bo;
    }

    @Transactional
    public void salvarBoEmAnalise(BoAnaliseRequest boAnaliseRequest, HttpServletRequest request, Status status) {
        BoAnalise boAnalise = new BoAnalise();
        
        var analista = analistaService.getAnalistaToken(request);
        var bodeon = boService.findById(boAnaliseRequest.fkBo())
                .orElseThrow(() -> new BoNotFoundException("Não existe analista com esse id"));

        if (bodeon != null) {
        	boService.mudaStatusBoEmAnalise(bodeon, status);
		}
        boAnalise.setDataEntradaAnalise(LocalDateTime.now());
        boAnalise.setAnalista(analista);
        boAnalise.setBoDeon(bodeon);
        boAnaliseRepository.save(boAnalise);
    }

    @Transactional
    public void aprovaBoEmAnalise(BoRequestDto request, Integer idResposta){

    	var resposta = respostaAnaliseBoRepository.findById(idResposta).orElseThrow(() ->
        	new BoAnaliseNotFoundException("Resposta não encontrado na base de dados"));
        var boDeon = boService.findById(request.getIdBoDeon())
               .orElseThrow(() -> new BoAnaliseNotFoundException("Não existe analise para esse id"));
        var analiseBo = boAnaliseRepository.findByBoDeon_IdBo(boDeon.getIdBo()).get();
        
        try {
        	if(boDeon.getStatus().equals(Status.EA)){
        		analiseBo.setStatus(true);
        		analiseBo.setDataAnalise(LocalDateTime.now());
        		analiseBo.setRespostaAnaliseBo(resposta);
    		Status novoStatus = (analiseBo.getRespostaAnaliseBo().getIdRespostaAnalise() != idResposta) ? Status.IV : Status.VA;
    		boService.mudaStatusBoEmAnalise(analiseBo.getBoDeon(), novoStatus);
    		boAnaliseRepository.saveAndFlush(analiseBo);
        	}
			
		} catch (RuntimeException e) {
			new BoAnaliseNotFoundException("Operação indisponivel com esse status");
		}
    }

	public void recusarBoEmAnalise(BoAnaliseRequest boRequest, Integer idResposta, HttpServletRequest request) {
		var resposta = respostaAnaliseBoRepository.findById(idResposta).orElseThrow(() ->
			new BoAnaliseNotFoundException("Resposta não encontrado na base de dados"));
		var boDeon = boService.findById(boRequest.fkBo())
				.orElseThrow(() -> new BoAnaliseNotFoundException("Não existe analise para esse id"));
		var analiseBo = boAnaliseRepository.findByBoDeon_IdBo(boDeon.getIdBo()).get();
		
		try {
			if (boDeon.getStatus().equals(Status.EA)) {
				analiseBo.setStatus(false);
				analiseBo.setAnalista(analistaService.getAnalistaToken(request));
				analiseBo.setDataAnalise(LocalDateTime.now());
				analiseBo.setRespostaAnaliseBo(resposta);
				
				Status status = (analiseBo.getRespostaAnaliseBo().getIdRespostaAnalise() != idResposta) ? Status.VA : Status.IV;
	    		boService.mudaStatusBoEmAnalise(analiseBo.getBoDeon(), status);
	    		boAnaliseRepository.saveAndFlush(analiseBo);
			}
		} catch (RuntimeException e) {
			throw new BoAnaliseNotFoundException("Operação indisponivel com esse status");
		}
	}
}
