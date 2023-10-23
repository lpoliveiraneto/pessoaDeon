package com.pessoaDeon.domain.service.analista;

import java.time.LocalDateTime;
import java.util.Optional;

import com.pessoaDeon.domain.exception.PessoaNotFoundException;
import com.pessoaDeon.domain.model.dto.analista.AnalistaRequest;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.repository.listas.perfil.PerfilRepository;
import com.pessoaDeon.domain.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.repository.analista.AnalistaRepository;

@Service
public class AnalistaService {

	@Autowired
	private AnalistaRepository analistaRepository;
	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PerfilRepository perfilRepository;
	
	public Optional<Analista> findById(Integer id) {
		return analistaRepository.findById(id);
	}

	public void salvarAnalista(AnalistaRequest analistaRequest){

		if(!verificaPessoaPeloNumeroDocumento(analistaRequest.cpf())){
			throw new PessoaNotFoundException("Pessoa não consta no banco de dados");
		}
		Pessoa pessoa = pessoaService.buscaPessoaCpf(analistaRequest.cpf());
		Analista analista = analistaRequestToAnalista(analistaRequest);
		analista.setPessoa(pessoa);
		analistaRepository.save(analista);
	}

	private Analista analistaRequestToAnalista(AnalistaRequest analistaRequest) {
		final long PERFIL_ANALISTA = 3;
		Analista analista = new Analista();
		analista.setLogin(analistaRequest.cpf());
		analista.setCargo(analistaRequest.cargo());
		analista.setFuncao(analistaRequest.funcao());
		analista.setSenha(new BCryptPasswordEncoder().encode(analistaRequest.senha()));
		analista.adicionarPerfil(perfilRepository.findById(PERFIL_ANALISTA).get());
		analista.setStatus(true);
		analista.setData_cadastro(LocalDateTime.now());
		return analista;
	}

	private boolean verificaPessoaPeloNumeroDocumento(String numeroDocumento){
		return pessoaService.existsPessoaNumeroDocumento(numeroDocumento);
	}

}
