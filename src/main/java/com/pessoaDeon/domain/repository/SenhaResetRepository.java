package com.pessoaDeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.ResetSenha;
import com.pessoaDeon.domain.model.security.Usuario;

@Repository
public interface SenhaResetRepository extends JpaRepository<ResetSenha, Integer> {
	
	ResetSenha findByCodigo(String codigo);
	
	ResetSenha findByUsuario(Usuario usuario);

}
