package com.pessoaDeon.repository;

import com.pessoaDeon.model.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface LogradouroRepository extends JpaRepository<Long, Logradouro> {
    Optional<Logradouro> findByCep(String cep);
}
