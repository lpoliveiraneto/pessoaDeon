package com.pessoaDeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.VerificacaoConta;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.model.util.ConfiguracaoUpload;

public interface ConfiguracaoUploadRepository extends JpaRepository<ConfiguracaoUpload, Integer> {
	
	ConfiguracaoUpload findByStatusIsTrue();
	
}
