package com.pessoaDeon.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.RemetenteEmail;

@Repository
public interface RemetenteEmailRepository extends JpaRepository<RemetenteEmail, Integer> {

	Optional<RemetenteEmail> findByIdRemetenteAndStatusIsTrue(Integer id);

}
