package com.pessoaDeon.domain.service.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.bo.EnderecoLocalFato;
import com.pessoaDeon.domain.repository.bo.EnderecoLocalFatoRepository;

@Service
public class EnderecoLocalFatoService {

	@Autowired
	private EnderecoLocalFatoRepository elfRespository;
	
	public EnderecoLocalFato findByIdBo(Integer id) {
		return elfRespository.findByBoIdBo(id);
	}
	
}
