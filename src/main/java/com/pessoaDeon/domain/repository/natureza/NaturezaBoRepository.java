package com.pessoaDeon.domain.repository.natureza;

import com.pessoaDeon.domain.model.natureza.NaturezaBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaturezaBoRepository extends JpaRepository<NaturezaBo, Integer> {

}
