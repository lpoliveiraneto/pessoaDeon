package com.pessoaDeon.domain.service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.exception.PessoaNotFoundException;
import com.pessoaDeon.domain.model.Pessoa;
import com.pessoaDeon.domain.model.dto.PessoaDtoInput;
import com.pessoaDeon.domain.model.dto.PessoaDtoOutput;
import com.pessoaDeon.domain.repository.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private ModelMapper modelMapper;


    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPessoa(Integer idPessoa) {
         Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
         return pessoa;
    }

    @Transactional
    public PessoaDtoOutput salvarPessoa(PessoaDtoInput pessoaDto) {

        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(pessoaDto.getCpf());

        if(pessoa.isPresent()){
          throw new PessoaNotFoundException("Pessoa já consta na base de dados");
        }else{
            Pessoa newPessoa = modelMapper.map(pessoaDto, Pessoa.class);
            var p = pessoaRepository.save(newPessoa);
            return modelMapper.map(p, PessoaDtoOutput.class);
        }
    }

    @Transactional
	public Pessoa salvarPessoaDeon(Pessoa pessoa) {
    	Pessoa pessoa2 = existsPessoa(pessoa);
    	if (pessoa2 != null) {
    		return pessoaRepository.save(pessoa2);
		}
		return pessoa2;
    }
    
    private Pessoa existsPessoa(Pessoa pessoa) {
    	Optional<Pessoa> pessoaOpt = null;
    	if(pessoa.getCpf() != null && pessoa.getCpf() != "") {
    		pessoaOpt = pessoaRepository.findByCpf(pessoa.getCpf());
    	} else if(pessoa.getRne() != null) {
    		pessoaOpt = pessoaRepository.findByRne(pessoa.getRne());
    	} 
    	
    	if (pessoaOpt.isPresent()) {
    		throw new PessoaNotFoundException("Pessoa já consta na base de dados");			
		}
    	
    	Pessoa pessoa2 = trataPessoa(pessoa);

    	if (!verificaPessoaTriChave(pessoa2).isEmpty()) {
    		throw new PessoaNotFoundException("Pessoa já consta na base de dados");
		} 
    	
    	return pessoa2;
    }
    
    private List<Pessoa> verificaPessoaTriChave(Pessoa pessoa) {
    	return pessoaRepository.findByNomeAndNomeMaeAndDataNascimento(pessoa.getNome(), pessoa.getNomeMae(), pessoa.getDataNascimento());
    }
    

	public Pessoa trataPessoa(Pessoa pessoa) {
		if (pessoa.getNome() != null && !pessoa.getNome().isEmpty()) {
			pessoa.setNome(removerAcentos(pessoa.getNome()).toUpperCase().trim());
		}
		if (pessoa.getAlcunha() != null && !pessoa.getAlcunha().isEmpty()) {
			pessoa.setAlcunha(removerAcentos(pessoa.getAlcunha()).toUpperCase().trim());
		}
		if (pessoa.getNomeMae() != null && !pessoa.getNomeMae().isEmpty()) {
			pessoa.setNomeMae(removerAcentos(pessoa.getNomeMae()).toUpperCase().trim());
		}
		if (pessoa.getNomePai() != null && !pessoa.getNomePai().isEmpty()) {
			pessoa.setNomePai(removerAcentos(pessoa.getNomePai()).toUpperCase().trim());
		}
		if (pessoa.getNomeSocial() != null && !pessoa.getNomeSocial().isEmpty()) {
			pessoa.setNomeSocial(removerAcentos(pessoa.getNomeSocial()).toUpperCase().trim());
		}
		if (pessoa.getRne() != null && !pessoa.getRne().isEmpty()) {
			pessoa.setRne(removerAcentos(pessoa.getRne()).toUpperCase().trim());
		}
//		if (pessoa.getPassaporte() != null && !pessoa.getPassaporte().isEmpty()) {
//			pessoa.setPassaporte(removerAcentos(pessoa.getPassaporte()).trim());
//		}
		return pessoa;
	}
	
	public static String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
}
