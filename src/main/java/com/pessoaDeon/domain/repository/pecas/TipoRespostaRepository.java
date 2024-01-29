package com.pessoaDeon.domain.repository.pecas;

import com.pessoaDeon.domain.model.pecas.formulario_risco.TipoRespostaFormularioRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRespostaRepository extends JpaRepository<TipoRespostaFormularioRisco, Integer>{
    
}
