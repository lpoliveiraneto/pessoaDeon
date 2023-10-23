package com.pessoaDeon.domain.repository.analista;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.analista.Analista;

@Repository
public interface AnalistaRepository extends JpaRepository<Analista, Integer> {

   	Optional<Analista> findByLogin(String login);
    
}