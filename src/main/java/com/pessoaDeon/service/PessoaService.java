package com.pessoaDeon.service;

import com.pessoaDeon.model.Pessoa;
import com.pessoaDeon.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPessoa(Long id) {
         Optional<Pessoa> pessoa = pessoaRepository.findById(id);
         return pessoa;
    }
}
