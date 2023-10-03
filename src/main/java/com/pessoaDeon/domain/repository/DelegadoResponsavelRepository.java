package com.pessoaDeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoaDeon.domain.model.DelegadoResponsavel;

public interface DelegadoResponsavelRepository extends JpaRepository<DelegadoResponsavel, Integer>{

	DelegadoResponsavel findByStatusIsTrue();

}
