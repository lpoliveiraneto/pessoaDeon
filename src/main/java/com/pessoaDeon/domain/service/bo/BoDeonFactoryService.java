package com.pessoaDeon.domain.service.bo;

import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.dto.BoDto;
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
    public void salvarBo(BoDto bo, List<Envolvido> envolvidos){

        var boDeon = (BoDeon)boService.salvar(bo);
        var naturezaDeon = naturezaDeonRepository.findById(bo.getFkNaturezaDeon()).get();

        var naturezaBo = naturezaBoService.salvarNaturezaBo(boDeon, naturezaDeon);

        //List<Envolvido> listaDeEnvolvido = envolvidos.stream().map(e -> envolvidoService.salvarEnvolvido(e)).toList();
//        envolvidos.forEach(e ->{
//            var envolvido = envolvidoService.salvarEnvolvido(e);
//            TipoParticipacao tp = tipoParticipacaoRepository.findById(e.fkPaticipacao);
//            envolvimentoService.salvarEnvolvimento(naturezaDeon, envolvido,tp);
//        });
//        envolvimentoService.salvarEnvolvimento();

    }
}
