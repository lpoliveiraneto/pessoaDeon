package com.pessoaDeon.domain.service.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.model.dto.EnvolvidosRequestDto;
import com.pessoaDeon.domain.model.envolvido.TipoParticipacao;
import com.pessoaDeon.domain.repository.envolvido.TipoParticipacaoRepository;
import com.pessoaDeon.domain.repository.natureza.NaturezaDeonRepository;
import com.pessoaDeon.domain.service.envolvido.EnvolvidoService;
import com.pessoaDeon.domain.service.envolvido.EnvolvimentoService;
import com.pessoaDeon.domain.service.natureza.NaturezaBoService;

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

        var boDeon = boService.salvar(bo);
        var naturezaDeon = naturezaDeonRepository.findById(bo.getFkNaturezaDeon()).get();
        var naturezaBo = naturezaBoService.salvarNaturezaBo(boDeon, naturezaDeon);

        var comunicante = envolvidoService.salvarEnvolvido(envolvidos.getComunicante());
        TipoParticipacao participacaoComunicante = tipoParticipacaoRepository.findByValor(envolvidos.getComunicante().getTipoParticipacao().getValor()).get();
        envolvimentoService.salvarEnvolvimento(comunicante, naturezaBo, participacaoComunicante);

        if(envolvidos.getAutor() != null){
            var autor = envolvidoService.salvarEnvolvido(envolvidos.getAutor());
            TipoParticipacao participacaoAutor = tipoParticipacaoRepository.findByValor(envolvidos.getAutor().getTipoParticipacao().getValor()).get();
            envolvimentoService.salvarEnvolvimento(autor, naturezaBo, participacaoAutor);
        }
        if(envolvidos.getVitima() != null){
            var vitima = envolvidoService.salvarEnvolvido(envolvidos.getVitima());
            TipoParticipacao paticipacaoVitima = tipoParticipacaoRepository.findByValor(envolvidos.getVitima().getTipoParticipacao().getValor()).get();
            envolvimentoService.salvarEnvolvimento(vitima, naturezaBo, paticipacaoVitima);
        }
    }
}
