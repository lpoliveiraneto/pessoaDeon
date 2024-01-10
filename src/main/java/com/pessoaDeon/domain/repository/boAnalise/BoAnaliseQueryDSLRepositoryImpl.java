package com.pessoaDeon.domain.repository.boAnalise;

import java.util.List;

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

public class BoAnaliseQueryDSLRepositoryImpl implements BoAnaliseQueryDSLRepository{
    @Override
    public List<BoAnalise> findByStatusFalseViolenciaDomestica(EntityManager entityManager) {

        final Integer FK__VIOLENCIA_DOMESTICA = 1064;
        QBoAnalise qBoAnalise = QBoAnalise.boAnalise;
        QBoDeon qBoDeon = QBoDeon.boDeon;
        QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;
        JPAQuery<BoAnalise> query = new JPAQueryFactory(entityManager).selectFrom(qBoAnalise).distinct();
        query.join(qBoAnalise.boDeon, qBoDeon);
        query.join(qBoDeon.listaNaturezas,qNaturezaBo);
        query.where(qBoAnalise.status.isFalse().and(qNaturezaBo.naturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)));
        List<BoAnalise> bosPendentes = query.fetch();
        return bosPendentes;
    }

    @Override
    public List<BoAnalise> findByStatusTrueViolenciaDomestica(EntityManager entityManager) {

        final Integer FK__VIOLENCIA_DOMESTICA = 1064;
        QBoAnalise qBoAnalise = QBoAnalise.boAnalise;
        QBoDeon qBoDeon = QBoDeon.boDeon;
        QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;
        JPAQuery<BoAnalise> query = new JPAQueryFactory(entityManager).selectFrom(qBoAnalise).distinct();
        query.join(qBoAnalise.boDeon, qBoDeon);
        query.join(qBoDeon.listaNaturezas,qNaturezaBo);
        query.where(qBoAnalise.status.isTrue().and(qNaturezaBo.naturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)));
        List<BoAnalise> bosAnalisados = query.fetch();
        return bosAnalisados;
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

    public List<BoAnalise> findByStatusFalseNotViolenciaDomestica(EntityManager entityManager) {

        final Integer FK__VIOLENCIA_DOMESTICA = 1064;
        QBoAnalise qBoAnalise = QBoAnalise.boAnalise;
        QBoDeon qBoDeon = QBoDeon.boDeon;
        QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;
        JPAQuery<BoAnalise> query = new JPAQueryFactory(entityManager).selectFrom(qBoAnalise).distinct();
        query.join(qBoAnalise.boDeon, qBoDeon);
        query.join(qBoDeon.listaNaturezas,qNaturezaBo);
        query.where(qBoAnalise.status.isFalse().and(qNaturezaBo.naturezaDeon.naturezaSigma.ne(FK__VIOLENCIA_DOMESTICA)));
        List<BoAnalise> bosPendentes = query.fetch();
        return bosPendentes;
    }
}
