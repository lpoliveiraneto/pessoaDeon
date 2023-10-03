package com.pessoaDeon.domain.repository.bo;

import com.pessoaDeon.domain.model.enumeration.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.bo.BoDeon;

import java.util.List;

@Repository
public interface BoRepository extends JpaRepository<BoDeon, Integer>, QuerydslPredicateExecutor<BoDeon>, BoQueryDSLRepository {

    List<BoDeon> findBoDeonStatusEquals(Status status);

}
