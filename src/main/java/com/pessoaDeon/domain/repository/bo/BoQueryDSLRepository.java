package com.pessoaDeon.domain.repository.bo;

import com.pessoaDeon.domain.model.analista.BoAnalise;
import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.enumeration.TipoPesquisa;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface BoQueryDSLRepository {

    public List<BoDeon> getBosPendentes(EntityManager entityManager);

    public JPAQuery<BoDeon> buildQuery(TipoPesquisa tipoPesquisa, String parametro, Predicate predicate, EntityManager entityManager);
    
    public JPAQuery<BoAnalise> pesquisaBosAnalisados(TipoPesquisa tipoPesquisa, String parametro, Predicate predicate, EntityManager entityManager);
}
