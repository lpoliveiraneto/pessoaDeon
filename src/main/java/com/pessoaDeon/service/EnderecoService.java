package com.pessoaDeon.service;

import com.pessoaDeon.model.Endereco;
import com.pessoaDeon.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> listarEnderecos(){
        return enderecoRepository.findAll();
    }

}
