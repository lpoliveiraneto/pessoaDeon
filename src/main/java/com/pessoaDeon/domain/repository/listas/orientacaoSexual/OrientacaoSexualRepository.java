package com.pessoaDeon.domain.repository.listas.orientacaoSexual;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.listas.OrientacaoSexual;

@Repository
public interface OrientacaoSexualRepository extends JpaRepository<OrientacaoSexual, Integer> {

}
