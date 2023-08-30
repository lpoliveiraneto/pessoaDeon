package com.pessoaDeon.domain.repository.envolvido;

import com.pessoaDeon.domain.model.envolvido.Envolvido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvolvidoRepository extends JpaRepository<Envolvido, Integer> {
}
