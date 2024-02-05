package com.pessoaDeon.domain.repository.pecas;

import java.util.List;

import com.pessoaDeon.domain.model.pecas.requerimento_mpu.RequerimentoMpu;
import com.pessoaDeon.domain.model.pecas.requerimento_mpu.TituloRequerimentoMpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import com.pessoaDeon.domain.model.pecas.RequerimentoMpu;
//import com.pessoaDeon.domain.model.pecas.TituloRequerimentoMpu;

@Repository
public interface RequerimentoMpuRepository extends JpaRepository<RequerimentoMpu, Integer>{

	List<RequerimentoMpu> findByTituloRequerimentoAndStatusAtivoIsTrue(TituloRequerimentoMpu tituloProvidencia);

}
