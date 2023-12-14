package com.pessoaDeon.domain.repository.boAnalise;

import com.pessoaDeon.domain.model.analista.BoAnalise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoAnaliseRepository extends JpaRepository<BoAnalise, Integer>, BoAnaliseQueryDSLRepository{

    List<BoAnalise> findByStatusTrue();

    List<BoAnalise> findByStatusFalse();

	Optional<BoAnalise> findByBoDeon_IdBo(Integer idBo);

}
