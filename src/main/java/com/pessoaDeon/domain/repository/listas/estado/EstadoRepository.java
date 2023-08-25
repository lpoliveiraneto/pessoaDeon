package com.pessoaDeon.domain.repository.listas.estado;

import com.pessoaDeon.domain.model.listas.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	Estado findByUf(String uf);
}
