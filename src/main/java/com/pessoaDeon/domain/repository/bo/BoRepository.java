package com.pessoaDeon.domain.repository.bo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.bo.BoDeon;

@Repository
public interface BoRepository extends JpaRepository<BoDeon, Integer>, QuerydslPredicateExecutor<BoDeon> {

}
