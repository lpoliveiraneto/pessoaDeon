package com.pessoaDeon.domain.repository.boAnalise;

import com.pessoaDeon.domain.model.analista.BoAnalise;
import com.pessoaDeon.domain.model.bo.BoDeon;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * esses metodos estão configurados no arquivo BoAnaliseQueryDSLRepositoryImpl.java
 * */
public interface BoAnaliseQueryDSLRepository {

 public Page<BoAnalise> findByStatusFalseViolenciaDomestica(EntityManager entityManager, Pageable pageable);

 public Page<BoAnalise> findByStatusTrueViolenciaDomestica(EntityManager entityManager, Pageable pageable);

 public Page<BoDeon> getBosPendentesViolenciaDomestica(EntityManager entityManager, Pageable pageable);

 public Page<BoAnalise> findByStatusFalseNotViolenciaDomestica(EntityManager entityManager, Pageable pageable);
 
 public Page<BoAnalise> findByStatusTrueNotViolenciaDom(EntityManager entityManager, Pageable pageable);
 
}
