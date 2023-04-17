package com.pessoaDeon.service;

import com.pessoaDeon.model.Endereco;
import com.pessoaDeon.repository.EnderecoRepository;
import com.pessoaDeon.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private LogradouroService logradouroService;

    public List<Endereco> listarEnderecos(){
        return enderecoRepository.findAll();
    }

}
