package com.pessoaDeon.domain.repository.listas.tipoLocal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.listas.TipoLocal;

@Repository
public interface TipoLocalRepository extends JpaRepository<TipoLocal, Integer> {

}
