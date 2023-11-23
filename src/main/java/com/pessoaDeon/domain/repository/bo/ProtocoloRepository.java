package com.pessoaDeon.domain.repository.bo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.bo.Protocolo;

@Repository
public interface ProtocoloRepository extends JpaRepository<Protocolo, Integer> {

	Protocolo findByBoIdBo(Integer idBo);

}
