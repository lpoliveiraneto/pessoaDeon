package com.pessoaDeon.domain.repository.boAnalise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pessoaDeon.domain.model.analista.BoAnalise;

import jakarta.persistence.EntityManager;

/**
 * esses metodos estão configurados no arquivo BoAnaliseQueryDSLRepositoryImpl.java
 * */
public interface BoAnaliseQueryDSLRepository {

// public Page<BoAnalise> findByStatusFalseViolenciaDomestica(EntityManager entityManager, Pageable pageable);

 public Page<BoAnalise> findByStatusTrueViolenciaDomestica(EntityManager entityManager, Pageable pageable);

// public Page<BoDeon> getBosPendentesViolenciaDomestica(EntityManager entityManager, Pageable pageable);

// public Page<BoAnalise> findByStatusFalseNotViolenciaDomestica(EntityManager entityManager, Pageable pageable);
 
// public List<BoAnalise> findByStatusTrueNotViolenciaDomestica(EntityManager entityManager);
 
}
