package com.pessoaDeon.domain.repository;

import com.pessoaDeon.domain.model.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String email);

    @Query("SELECT u FROM Usuario u where u.email = ?1")
    List<Usuario> BuscarUsuarioPorEmail(String email);
}
