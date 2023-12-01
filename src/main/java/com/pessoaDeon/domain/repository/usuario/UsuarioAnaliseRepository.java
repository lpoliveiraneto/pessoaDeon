package com.pessoaDeon.domain.repository.usuario;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.usuario.UsuarioAnalise;

public interface UsuarioAnaliseRepository extends JpaRepository<UsuarioAnalise, Integer> {
	
	UsuarioAnalise findByUsuario(Integer idUsuario);
	Optional<UsuarioAnalise> findByUsuario_IdUsuario(Integer idUsuario);

}
