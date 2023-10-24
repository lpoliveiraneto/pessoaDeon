package com.pessoaDeon.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.util.ConfiguracaoUpload;
import com.pessoaDeon.domain.repository.ConfiguracaoUploadRepository;

@Service
public class ConfiguracaoUploadService {

	@Autowired
	private ConfiguracaoUploadRepository configuracaoUploadRepository;
	
	public ConfiguracaoUpload getConfiguracaoUploadAtiva() {
		return configuracaoUploadRepository.findByStatusIsTrue();
	}
}
