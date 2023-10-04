package com.pessoaDeon.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.DelegadoResponsavel;

public interface DelegadoResponsavelRepository extends JpaRepository<DelegadoResponsavel, Integer>{

	Optional<DelegadoResponsavel> findByStatusIsTrue();

}
