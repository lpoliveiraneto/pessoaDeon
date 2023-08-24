package com.pessoaDeon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.Protocolo;

@Repository
public interface ProtocoloRepository extends JpaRepository<Protocolo, Integer> {

}
