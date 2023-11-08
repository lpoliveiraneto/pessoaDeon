package com.pessoaDeon.domain.repository.boAnalise;

import com.pessoaDeon.domain.model.analista.BoAnalise;
import com.pessoaDeon.domain.model.bo.BoDeon;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface BoAnaliseQueryDSLRepository {

 public List<BoAnalise> findByStatusFalseViolenciaDomestica(EntityManager entityManager);

 public List<BoAnalise> findByStatusTrueViolenciaDomestica(EntityManager entityManager);

 public List<BoDeon> getBosPendentesViolenciaDomestica(EntityManager entityManager);
}
