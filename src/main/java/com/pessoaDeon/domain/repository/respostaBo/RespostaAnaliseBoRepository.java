package com.pessoaDeon.domain.repository.respostaBo;

import com.pessoaDeon.domain.model.analista.RespostaAnaliseBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaAnaliseBoRepository extends JpaRepository<RespostaAnaliseBo, Integer> {
}
