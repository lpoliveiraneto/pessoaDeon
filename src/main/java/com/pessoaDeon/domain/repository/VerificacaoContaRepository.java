package com.pessoaDeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.VerificacaoConta;
import com.pessoaDeon.domain.model.security.Usuario;

@Repository
public interface VerificacaoContaRepository extends JpaRepository<VerificacaoConta, Integer> {
	
	VerificacaoConta findByCodigo(String codigo);
	
	VerificacaoConta findByUsuario(Usuario usuario);

}
