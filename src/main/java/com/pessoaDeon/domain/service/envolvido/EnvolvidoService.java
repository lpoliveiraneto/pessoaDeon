package com.pessoaDeon.domain.service.envolvido;

import com.pessoaDeon.domain.exception.EnvolvidoNotFoundException;
import com.pessoaDeon.domain.model.envolvido.Envolvido;
import com.pessoaDeon.domain.repository.envolvido.EnvolvidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvolvidoService {

    @Autowired
    private EnvolvidoRepository envolvidoRepository;

    public Envolvido buscarEnvolvido(Integer idEnvolvido){
        Envolvido envolvido = envolvidoRepository.findById(idEnvolvido).orElseThrow(() ->
                new EnvolvidoNotFoundException("Envolvido não Encontrado = "  + idEnvolvido));
        return envolvido;
    }

    public void salvarEnvolvido(Envolvido envolvido){
        envolvidoRepository.save(envolvido);
    }
}
