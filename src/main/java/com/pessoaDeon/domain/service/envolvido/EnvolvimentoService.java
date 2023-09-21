package com.pessoaDeon.domain.service.envolvido;

import com.pessoaDeon.domain.model.envolvido.Envolvido;
import com.pessoaDeon.domain.model.envolvido.Envolvimento;
import com.pessoaDeon.domain.model.envolvido.TipoParticipacao;
import com.pessoaDeon.domain.model.natureza.NaturezaBo;
import com.pessoaDeon.domain.repository.envolvido.EnvolvimentoRepository;
import com.pessoaDeon.domain.repository.envolvido.TipoParticipacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EnvolvimentoService {

    @Autowired
    private EnvolvidoService envolvidoService;

    @Autowired
    private TipoParticipacaoRepository tipoParticipacaoRepository;

    @Autowired
    private EnvolvimentoRepository envolvimentoRepository;

    @Transactional
    public void salvarEnvolvimento(Envolvido envolvido, NaturezaBo naturezaBo, TipoParticipacao tipoParticipacao){

        Envolvimento envolvimento = new Envolvimento();
        envolvimento.setNaturezaBo(naturezaBo);
        envolvimento.setEnvolvido(envolvido);
        envolvimento.setTipoParticipacao(tipoParticipacao);

        envolvimentoRepository.save(envolvimento);

    }

    public Optional<Envolvimento> buscarEnvolvimento(Integer idEnvolvimento){
        return envolvimentoRepository.findById(idEnvolvimento);
    }



}
