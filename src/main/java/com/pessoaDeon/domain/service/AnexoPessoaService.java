package com.pessoaDeon.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.AnexoPessoa;
import com.pessoaDeon.domain.model.util.ConfiguracaoUpload;
import com.pessoaDeon.domain.repository.AnexoPessoaRepository;
import com.pessoaDeon.domain.repository.ConfiguracaoUploadRepository;

@Service
public class AnexoPessoaService {

	@Autowired
	private AnexoPessoaRepository anexoPessoaRepository;
	
	@Transactional
	public AnexoPessoa salvarAnexoPessoa(AnexoPessoa anexoPessoa) {
		return anexoPessoaRepository.save(anexoPessoa);
	}

	public List<AnexoPessoa> findByPessoaIdPessoa(Integer idPessoa) {
		return anexoPessoaRepository.findByPessoaId(idPessoa);
	}
}
