package com.pessoaDeon.domain.service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

import com.pessoaDeon.domain.exception.PessoaAlreadyRegisteredException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.Pessoa;
import com.pessoaDeon.domain.model.TipoDocumento;
import com.pessoaDeon.domain.repository.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
   
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPessoa(Integer idPessoa) {
         Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
         return pessoa;
    }


    @Transactional
	public Pessoa salvarPessoaDeon(Pessoa pessoa) {
    	Pessoa pessoa2 = existsPessoa(pessoa, pessoa.getTipoDocumento());
    	if (pessoa2 != null) {
    		return pessoaRepository.save(pessoa2);
		}
		return pessoa2;
    }
    
    private Pessoa existsPessoa(Pessoa pessoa, TipoDocumento tipoDocumento) {
    	Optional<Pessoa> pessoaOpt = null;
    	
    	pessoaOpt = pessoaRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, pessoa.getNumeroDocumento());
    	
    	Pessoa pessoa2 = trataPessoa(pessoa);

    	if (!verificaPessoaTriChave(pessoa2).isEmpty() || pessoaOpt.isPresent() ) {
    		throw new PessoaAlreadyRegisteredException("Pessoa já consta na base de dados");
		} 
    	
    	return pessoa2;
    }
    
    private List<Pessoa> verificaPessoaTriChave(Pessoa pessoa) {

		System.out.println("Nome"+pessoa.getNome());
		System.out.println("Mãe"+pessoa.getNomeMae());
		System.out.println("Data de Nascimento"+ pessoa.getDataNascimento());
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

		return pessoa;
	}
	
	public static String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public Pessoa buscaPessoaEmail(String email){
		return pessoaRepository.findByEmailEmail(email).get();
	}
	
}
