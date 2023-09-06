package com.pessoaDeon.domain.service.natureza;

import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.natureza.NaturezaBo;
import com.pessoaDeon.domain.model.natureza.NaturezaDeon;
import com.pessoaDeon.domain.repository.natureza.NaturezaBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NaturezaBoService {

    @Autowired
    private NaturezaBoRepository naturezaBoRepository;

    public void salvarNaturezaBo(BoDeon bo, NaturezaDeon natureza){
        NaturezaBo naturezaBo = new NaturezaBo();
        naturezaBo.setNaturezaDeon(natureza);
        naturezaBo.setBo(bo);
        naturezaBoRepository.save(naturezaBo);
    }

    public Optional<NaturezaBo> buscarNaturezaBo(Integer idNaturezaBo){
        return naturezaBoRepository.findById(idNaturezaBo);
    }


}
