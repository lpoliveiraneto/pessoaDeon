package com.pessoaDeon.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.FotoPerfil;
import com.pessoaDeon.domain.repository.FotoPerfilRepository;

@Service
public class FotoPerfilService {

	@Autowired
	private FotoPerfilRepository fotoPerfilRepository;
	
	@Transactional
	public FotoPerfil salvarFotoPerfil(FotoPerfil fotoPerfil) {
		return fotoPerfilRepository.save(fotoPerfil);
	}

	public FotoPerfil findByPessoaIdPessoa(Integer idPessoa) {
		return fotoPerfilRepository.findByPessoa(idPessoa);
	}
}
