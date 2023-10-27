package com.pessoaDeon.domain.service.analista;

import com.pessoaDeon.domain.exception.AnalistaNotFoundException;
import com.pessoaDeon.domain.exception.BoAnaliseNotFoundException;
import com.pessoaDeon.domain.exception.BoNotFoundException;
import com.pessoaDeon.domain.exception.RespostaAnaliseNotFoundException;
import com.pessoaDeon.domain.model.analista.BoAnalise;
import com.pessoaDeon.domain.model.dto.bo.BoAnaliseRequest;
import com.pessoaDeon.domain.model.dto.bo.BosAnalisadosResponseDto;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.repository.analista.BoAnaliseRepository;
import com.pessoaDeon.domain.repository.bo.BoRepository;
import com.pessoaDeon.domain.repository.bo.ProtocoloRepository;
import com.pessoaDeon.domain.repository.respostaBo.RespostaAnaliseBoRepository;
import com.pessoaDeon.domain.service.bo.BoService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class BoAnaliseService {

    @Autowired
    private BoAnaliseRepository boAnaliseRepository;
    @Autowired
    private ProtocoloRepository protocoloRepository;
    @Autowired
    private BoService boService;
    @Autowired
    private BoRepository boRepository;
    @Autowired
    private AnalistaService analistaService;
    @Autowired
    private RespostaAnaliseBoRepository respostaAnaliseBoRepository;

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
        List<BoAnalise> bosAnalise = boAnaliseRepository.findByStatusTrue();
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
    public void salvarBoEmAnalise(BoAnaliseRequest boAnaliseRequest) {
        BoAnalise boAnalise = new BoAnalise();
        boAnalise.setDataEntradaAnalise(LocalDateTime.now());
        var analista = analistaService.findById(boAnaliseRequest.fkAnalista())
                .orElseThrow(() -> new  AnalistaNotFoundException("Não existe analista com esse id"));
        var bodeon = boService.findById(boAnaliseRequest.fkBo())
                .orElseThrow(() -> new BoNotFoundException("Não existe analista com esse id"));
        boAnalise.setAnalista(analista);
        boAnalise.setBoDeon(bodeon);
        boAnaliseRepository.save(boAnalise);
    }

    public void salvarRespostaBoEmAnalise(BoAnaliseRequest boAnaliseRequest){
        final int FINALIZADO_COM_SUCESSO = 1;
        var boAnalise = boAnaliseRepository.findById(boAnaliseRequest.fkBoAnalise())
               .orElseThrow(() -> new BoAnaliseNotFoundException("Não existe analise para esse id"));
        if(boAnalise.isStatus()){
            return;
        }
        boAnalise.setDataAnalise(LocalDateTime.now());
        boAnalise.setStatus(true);
        var respostaAnaliseBo = respostaAnaliseBoRepository.findById(boAnaliseRequest.fkRespostaBo())
                .orElseThrow(() -> new RespostaAnaliseNotFoundException("Não existe resposta para esse id"));
        boAnalise.setRespostaAnaliseBo(respostaAnaliseBo);

        Status novoStatus = (boAnalise.getRespostaAnaliseBo().getIdRespostaAnalise() != FINALIZADO_COM_SUCESSO) ? Status.IV : Status.VA;
        boService.mudaStatusBoEmAnalise(boAnalise.getBoDeon(), novoStatus);

        boAnaliseRepository.saveAndFlush(boAnalise);
    }
}
