package com.pessoaDeon.domain.service;

import com.pessoaDeon.domain.model.Pessoa;
import com.pessoaDeon.domain.model.dto.PessoaDtoInput;
import com.pessoaDeon.domain.model.dto.PessoaDtoOutput;
import com.pessoaDeon.domain.repository.pessoa.PessoaRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public PessoaDtoOutput salvarPessoa(PessoaDtoInput pessoaDto) {

        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(pessoaDto.getCpf());

        if(pessoa.isPresent()){
          throw new RuntimeException("ESTE FELA JÁ FOI ADICIONADO");
        }else{
            Pessoa newPessoa =  modelMapper.map(pessoaDto, Pessoa.class);
            pessoaRepository.save(newPessoa);
            return modelMapper.map(pessoa, PessoaDtoOutput.class);
        }


    }
}
