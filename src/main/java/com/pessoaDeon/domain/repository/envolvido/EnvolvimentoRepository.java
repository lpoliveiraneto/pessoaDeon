package com.pessoaDeon.domain.repository.envolvido;

import com.pessoaDeon.domain.model.envolvido.Envolvimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvolvimentoRepository extends JpaRepository<Envolvimento, Integer> {
}
