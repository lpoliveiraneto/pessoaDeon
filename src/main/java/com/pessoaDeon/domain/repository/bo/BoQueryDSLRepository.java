package com.pessoaDeon.domain.repository.bo;

import com.pessoaDeon.domain.model.bo.BoDeon;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface BoQueryDSLRepository {

    public List<BoDeon> getBosPendentes(EntityManager entityManager);
}
