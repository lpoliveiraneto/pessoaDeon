package com.pessoaDeon.domain.repository.endereco;

import com.pessoaDeon.domain.model.endereco.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogradouroRepository extends JpaRepository<Logradouro, Integer> {
    
	Optional<Logradouro> findByCep(String cep);
    
    Optional<Logradouro> findByCepAndCepDesconhecidoFalse(String cep);
    
}
