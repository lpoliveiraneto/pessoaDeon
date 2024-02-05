package com.pessoaDeon.domain.service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;

import com.pessoaDeon.domain.exception.PessoaAlreadyRegisteredException;
import com.pessoaDeon.domain.exception.PessoaNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.listas.TipoDocumento;
import com.pessoaDeon.domain.repository.listas.tipoDocumento.TipoDocumentoRepository;
import com.pessoaDeon.domain.repository.pessoa.PessoaRepository;

import jakarta.transaction.Transactional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;
   
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
    
    public Pessoa existsPessoa(Pessoa pessoa, TipoDocumento tipoDocumento) {
    	Optional<Pessoa> pessoaOpt = null;
    	
    	pessoaOpt = pessoaRepository.findByTipoDocumentoAndNumeroDocumento(tipoDocumento, pessoa.getNumeroDocumento());
    	
    	Pessoa pessoa2 = trataPessoa(pessoa);

    	if (!verificaPessoaTriChave(pessoa2).isEmpty() || pessoaOpt.isPresent() ) {
    		throw new PessoaAlreadyRegisteredException("Pessoa já consta na base de dados");
		} 
    	
    	return pessoa2;
    }
    
    /**
     * @author hilbertofilho
     * Esse metodo é invocado para o redefinir senha do usuario
     * quando o metodo recebe o tipo e numero do documento, vai ser feito uma busca na tabela TipoDocumento para saber qual é o valor 
     * após ter o tipoDocumento, vai ser feito a busca da pessoa pelo Tipo e pelo numero do documento
     * */
    public Optional<Pessoa> existsPessoaDeon(String numeroDocumento, String tipoDocumento) {
    	Optional<Pessoa> pessoaOpt = null;
    	TipoDocumento tipo = tipoDocumentoRepository.findByValor(tipoDocumento.toUpperCase());
    	pessoaOpt = pessoaRepository.findByTipoDocumentoAndNumeroDocumento(tipo, numeroDocumento);
    	if (pessoaOpt.isEmpty()) {
			throw new PessoaNotFoundException("Pessoa não consta na base de dados");
		}
    	return pessoaOpt;
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
	
	public Pessoa buscaPessoaCpf(String Cpf){
		return pessoaRepository.findByNumeroDocumento(Cpf).get();
	}
	
	public Optional<Pessoa> getPessoaByNumeroDocumento(String numeroDocumento) {
		return pessoaRepository.findByNumeroDocumento(numeroDocumento);
	}

	public boolean existsPessoaNumeroDocumento(String numeroDocumento){
		return pessoaRepository.existsByNumeroDocumento(numeroDocumento);
	}
}
