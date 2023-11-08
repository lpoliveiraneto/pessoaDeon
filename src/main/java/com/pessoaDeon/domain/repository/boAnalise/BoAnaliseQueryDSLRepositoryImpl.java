package com.pessoaDeon.domain.repository.boAnalise;

import com.pessoaDeon.domain.model.analista.BoAnalise;
import com.pessoaDeon.domain.model.analista.QBoAnalise;
import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.bo.QBoDeon;
import com.pessoaDeon.domain.model.envolvido.QEnvolvido;
import com.pessoaDeon.domain.model.envolvido.QEnvolvimento;
import com.pessoaDeon.domain.model.natureza.QNaturezaBo;
import com.pessoaDeon.domain.model.natureza.QNaturezaDeon;
import com.pessoaDeon.domain.model.pessoa.QPessoa;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BoAnaliseQueryDSLRepositoryImpl implements BoAnaliseQueryDSLRepository{
    @Override
    public List<BoAnalise> findByStatusFalseViolenciaDomestica(EntityManager entityManager) {
        return null;
    }

    @Override
    public List<BoAnalise> findByStatusTrueViolenciaDomestica(EntityManager entityManager) {
        return null;
    }

    @Override
    public List<BoDeon> getBosPendentesViolenciaDomestica(EntityManager entityManager) {
        final Integer FK__VIOLENCIA_DOMESTICA = 1064;
        QBoDeon qBoDeon = QBoDeon.boDeon;
        QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;
        QNaturezaDeon qNaturezaDeon = QNaturezaDeon.naturezaDeon;
        QEnvolvimento qEnvolvimento = QEnvolvimento.envolvimento;
        QEnvolvido qEnvolvido = QEnvolvido.envolvido;
        QPessoa qPessoa = QPessoa.pessoa;
        JPAQuery<BoDeon> query = new JPAQueryFactory(entityManager).selectFrom(qBoDeon).distinct();
        query.join(qBoDeon.listaNaturezas,qNaturezaBo);
        query.join(qNaturezaBo.naturezaDeon, qNaturezaDeon);
        query.join(qEnvolvimento).on(qEnvolvimento.naturezaBo.id.eq(qNaturezaBo.id));
        query.join(qEnvolvimento.envolvido, qEnvolvido);
        query.join(qEnvolvido.pessoa, qPessoa);
        query.where(qPessoa.id.isNotNull().and(qNaturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)));
        List<BoDeon> bosPendentes = query.fetch();
        return bosPendentes;
    }
}
