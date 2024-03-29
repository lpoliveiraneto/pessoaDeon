package com.pessoaDeon.domain.repository.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.pessoa.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer> {

	Email findByPessoaId(Integer idPessoa);

}
