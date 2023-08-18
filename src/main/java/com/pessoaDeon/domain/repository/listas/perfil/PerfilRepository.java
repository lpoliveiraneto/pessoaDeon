package com.pessoaDeon.domain.repository.listas.perfil;

import com.pessoaDeon.domain.model.security.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {


}
