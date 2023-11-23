package com.pessoaDeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.TipoArquivo;
import com.pessoaDeon.domain.model.util.ConfiguracaoUpload;

public interface ConfiguracaoUploadRepository extends JpaRepository<ConfiguracaoUpload, Integer> {
	
	ConfiguracaoUpload findByTipoArquivoAndStatusIsTrue(TipoArquivo fp);
	
}
