package com.pessoaDeon.domain.repository.listas.pais;

import com.pessoaDeon.domain.model.listas.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
}
