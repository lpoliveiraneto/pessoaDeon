package com.pessoaDeon.domain.repository.bo;

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

public class BoQueryDSLRepositoryImpl implements BoQueryDSLRepository{
    @Override
    public List<BoDeon> getBosPendentes(EntityManager entityManager) {
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
        query.where(qPessoa.id.isNotNull());
        List<BoDeon> bosPendentes = query.fetch();
        return bosPendentes;
    }

    //	public Page<BosPessoaResponseDto> buscarPessoa(Integer idPessoa, Pageable pageable) {
//		QBoDeon qBoDeon = QBoDeon.boDeon;
//		QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;
//		QNaturezaDeon qNaturezaDeon = QNaturezaDeon.naturezaDeon;
//		QProtocolo qProtocolo = QProtocolo.protocolo;
//		QEnvolvimento qEnvolvimento = QEnvolvimento.envolvimento;
//		QEnvolvido qEnvolvido = QEnvolvido.envolvido;
//		QPessoa qPessoa = QPessoa.pessoa;
//
//		JPAQuery<BosPessoaResponseDto> query = new JPAQueryFactory(entityManager)
//				.select(Projections.bean(BosPessoaResponseDto.class, qBoDeon.idBo, qBoDeon.dataRegistro,
//						qProtocolo.numero, qNaturezaDeon.nome, qNaturezaDeon.codigo))
//				.from(qBoDeon).join(qBoDeon.listaNaturezas, qNaturezaBo).join(qNaturezaBo.naturezaDeon, qNaturezaDeon)
//				.join(qProtocolo).on(qProtocolo.bo.idBo.eq(qBoDeon.idBo)).join(qEnvolvimento)
//				.on(qEnvolvimento.naturezaBo.id.eq(qNaturezaBo.id)).join(qEnvolvimento.envolvido, qEnvolvido)
//				.join(qEnvolvido.pessoa, qPessoa).where(qPessoa.id.eq(idPessoa));
//
//		QueryResults<BosPessoaResponseDto> results = query.offset(pageable.getOffset()).limit(pageable.getPageSize())
//				.orderBy(qBoDeon.idBo.desc()).fetchResults();
//
//		return new PageImpl<>(results.getResults(), pageable, results.getTotal());
//	}
}
