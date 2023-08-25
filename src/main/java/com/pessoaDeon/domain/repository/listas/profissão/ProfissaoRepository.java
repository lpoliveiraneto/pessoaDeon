package com.pessoaDeon.domain.repository.listas.profissão;

import com.pessoaDeon.domain.model.listas.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissaoRepository extends JpaRepository<Profissao, Integer> {
}
