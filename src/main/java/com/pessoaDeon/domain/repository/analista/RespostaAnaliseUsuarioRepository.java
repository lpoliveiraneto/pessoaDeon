package com.pessoaDeon.domain.repository.analista;

import com.pessoaDeon.domain.model.analista.RespostaAnaliseUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaAnaliseUsuarioRepository extends JpaRepository<RespostaAnaliseUsuario, Integer> {
}
