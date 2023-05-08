package com.pessoaDeon.domain.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.exception.EnderecoNotFoundException;
import com.pessoaDeon.domain.exception.PessoaNotFoundException;
import com.pessoaDeon.domain.model.Endereco;
import com.pessoaDeon.domain.model.Pessoa;
import com.pessoaDeon.domain.model.dto.EnderecoDtoInput;
import com.pessoaDeon.domain.repository.EnderecoRepository;

import jakarta.transaction.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private PessoaService pessoaService;
    
    @Autowired
    private ModelMapper modelMapper;

    public List<Endereco> listarEnderecos(){
        return enderecoRepository.findAll();
    }

    @Transactional
	public Endereco salvarEnderecoPessoa(EnderecoDtoInput endereco, Integer idPessoa) {
    	
    	Optional<Pessoa> pessoa = pessoaService.buscarPessoa(endereco.getPessoa().getId());
    	if(pessoa.isPresent()) {
    		Endereco novoEndereco = modelMapper.map(endereco, Endereco.class);
    		return enderecoRepository.save(novoEndereco);
    	}
    	else {
    		throw new PessoaNotFoundException("Não existe uma pessoa vinculada a esse endereco");
    	}
	}

	public Optional<Endereco> getById(Integer idEndereco) throws EnderecoNotFoundException {
		var enderecoId = enderecoRepository.findByIdEndereco(idEndereco);
		if(enderecoId.isPresent()) {
			return (enderecoId);
    	}else {
    		throw new EnderecoNotFoundException("Endereço não encontrado na base de dados");
    	}
	}

}
