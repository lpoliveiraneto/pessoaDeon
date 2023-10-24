package com.pessoaDeon.domain.service.analista;

import com.pessoaDeon.domain.model.analista.BoAnalise;
import com.pessoaDeon.domain.model.dto.BosAnalisadosResponseDto;
import com.pessoaDeon.domain.repository.analista.BoAnaliseRepository;
import com.pessoaDeon.domain.repository.bo.ProtocoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoAnaliseService {

    @Autowired
    private BoAnaliseRepository boAnaliseRepository;

    @Autowired
    private ProtocoloRepository protocoloRepository;

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

    private BosAnalisadosResponseDto getBoAnalisetoBosAnalisadosResponseDto(BoAnalise b) {
        BosAnalisadosResponseDto bo = new BosAnalisadosResponseDto();
        bo.setIdBo(b.getBoDeon().getIdBo());
        bo.setProtocolo(protocoloRepository.findByBo(b.getBoDeon()).getNumero());
        bo.setNomeAnalista(b.getAnalista().getPessoa().getNome());
        var natureza = b.getBoDeon().getListaNaturezas().get(0).getNaturezaDeon();
        var codigo = ((natureza.getCodigo() != null && !natureza.getCodigo().isBlank()) ? " - " + natureza.getCodigo() : "");
        bo.setNatureza(natureza.getNome() + codigo);
        bo.setDataAnalise(b.getDataAnalise());
        bo.setDataRegistro(b.getBoDeon().getDataRegistro());
        return bo;
    }

}
