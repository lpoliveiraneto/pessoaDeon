package com.pessoaDeon.domain.repository.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.security.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

//	UserDetails findByEmail(String email);
	Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u where u.email = ?1")
    List<Usuario> BuscarUsuarioPorEmail(String email);
    
    Optional<Usuario> findByPessoa(Pessoa pessoa);
    
	List<Usuario> findByStatusAndContaAtivaIsTrue(Status pe);
	
    Usuario findByIdUsuarioAndStatusAndContaAtivaIsTrue(Long idUsuario, Status va);
	
}
