package com.pessoaDeon.domain.service.bo;

import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.model.dto.EnvolvidosRequestDto;
import com.pessoaDeon.domain.model.envolvido.Envolvido;
import com.pessoaDeon.domain.model.envolvido.Envolvimento;
import com.pessoaDeon.domain.model.envolvido.TipoParticipacao;
import com.pessoaDeon.domain.model.natureza.NaturezaDeon;
import com.pessoaDeon.domain.repository.envolvido.TipoParticipacaoRepository;
import com.pessoaDeon.domain.repository.natureza.NaturezaDeonRepository;
import com.pessoaDeon.domain.service.envolvido.EnvolvidoService;
import com.pessoaDeon.domain.service.envolvido.EnvolvimentoService;
import com.pessoaDeon.domain.service.natureza.NaturezaBoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoDeonFactoryService {

    @Autowired
    BoService boService;
    @Autowired
    NaturezaBoService naturezaBoService;
    @Autowired
    EnvolvidoService envolvidoService;
    @Autowired
    EnvolvimentoService envolvimentoService;
    @Autowired
    TipoParticipacaoRepository tipoParticipacaoRepository;

    @Autowired
    NaturezaDeonRepository naturezaDeonRepository;

    @Transactional
    public void salvarBo(BoDto bo, EnvolvidosRequestDto envolvidos){

        var boDeon = (BoDeon)boService.salvar(bo);
        var naturezaDeon = naturezaDeonRepository.findById(bo.getFkNaturezaDeon()).get();
        var naturezaBo = naturezaBoService.salvarNaturezaBo(boDeon, naturezaDeon);

        var comunicante = envolvidoService.salvarEnvolvido(envolvidos.getComunicante());
        TipoParticipacao participacaoComunicante = tipoParticipacaoRepository.findByDescricao(envolvidos.getComunicante().getTipoParticipacao().getDescricao()).get();
        envolvimentoService.salvarEnvolvimento(comunicante, naturezaBo, participacaoComunicante);

        if(envolvidos.getAutor() != null){
            var autor = envolvidoService.salvarEnvolvido(envolvidos.getAutor());
            TipoParticipacao participacaoAutor = tipoParticipacaoRepository.findByDescricao(envolvidos.getAutor().getTipoParticipacao().getDescricao()).get();
            envolvimentoService.salvarEnvolvimento(autor, naturezaBo, participacaoAutor);
        }
        if(envolvidos.getVitima() != null){
            var vitima = envolvidoService.salvarEnvolvido(envolvidos.getVitima());
            TipoParticipacao paticipacaoVitima = tipoParticipacaoRepository.findByDescricao(envolvidos.getVitima().getTipoParticipacao().getDescricao()).get();
            envolvimentoService.salvarEnvolvimento(vitima, naturezaBo, paticipacaoVitima);
        }
    }
}
