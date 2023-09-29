package com.pessoaDeon.domain.repository.analista;

import com.pessoaDeon.domain.model.analista.Analista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalistaRepository extends JpaRepository<Analista, Integer> {
}
