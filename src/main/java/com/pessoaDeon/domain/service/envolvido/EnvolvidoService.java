package com.pessoaDeon.domain.service.envolvido;

import com.pessoaDeon.domain.repository.envolvido.EnvolvidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvolvidoService {

    @Autowired
    private EnvolvidoRepository envolvidoRepository;
}
