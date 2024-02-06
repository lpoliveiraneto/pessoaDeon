package com.pessoaDeon.domain.repository.boAnalise;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.pessoaDeon.domain.model.analista.BoAnalise;
import com.pessoaDeon.domain.model.analista.QBoAnalise;
import com.pessoaDeon.domain.model.bo.QBoDeon;
import com.pessoaDeon.domain.model.natureza.QNaturezaBo;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Component
public class BoAnaliseQueryDSLRepositoryImpl implements BoAnaliseQueryDSLRepository{
	
//	@Autowired
//	private BoAnaliseService analiseService;
	
//    @Override
//    public Page<BoAnalise> findByStatusFalseViolenciaDomestica(EntityManager entityManager, Pageable pageable) {
//
//        final Integer FK__VIOLENCIA_DOMESTICA = 1064;
//        QBoAnalise qBoAnalise = QBoAnalise.boAnalise;
//        QBoDeon qBoDeon = QBoDeon.boDeon;
//        QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;
//
//        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//
//        JPAQuery<Long> countQuery = queryFactory
//                .select(qBoAnalise.idAnalise.count())
//                .from(qBoAnalise)
//                .join(qBoAnalise.boDeon, qBoDeon)
//                .join(qBoDeon.listaNaturezas, qNaturezaBo)
//                .where(qBoAnalise.status.isFalse().and(qNaturezaBo.naturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)));
//        long total = countQuery.fetchOne();
//
//        List<BoAnalise> bosPendentes = queryFactory
//                .selectFrom(qBoAnalise)
//                .distinct()
//                .join(qBoAnalise.boDeon, qBoDeon)
//                .join(qBoDeon.listaNaturezas, qNaturezaBo)
//                .where(qBoAnalise.status.isFalse().and(qNaturezaBo.naturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//        return new PageImpl<>(bosPendentes, pageable, total);
//    }

    @Override
    public Page<BoAnalise> findByStatusTrueViolenciaDomestica(EntityManager entityManager, Pageable pageable) {

        final Integer FK__VIOLENCIA_DOMESTICA = 1064;
        QBoAnalise qBoAnalise = QBoAnalise.boAnalise;
        QBoDeon qBoDeon = QBoDeon.boDeon;
        QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<Long> countQuery = queryFactory
                .select(qBoAnalise.idAnalise.count())
                .from(qBoAnalise)
                .join(qBoAnalise.boDeon, qBoDeon)
                .join(qBoDeon.listaNaturezas, qNaturezaBo)
                .where(qBoAnalise.status.isTrue().and(qNaturezaBo.naturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)));
        long total = countQuery.fetchOne();
        List<BoAnalise> bosAnalisados = queryFactory
                .selectFrom(qBoAnalise)
                .distinct()
                .join(qBoAnalise.boDeon, qBoDeon)
                .join(qBoDeon.listaNaturezas, qNaturezaBo)
                .where(qBoAnalise.status.isTrue().and(qNaturezaBo.naturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qBoAnalise.idAnalise.desc())
                .fetch();
        return new PageImpl<>(bosAnalisados, pageable, total);
    }

    /**
     * @author hilbertofilho
     * busca todos os boletins que estão pendentes para analise
     * */
//    @Override
//    public Page<BoDeon> getBosPendentesViolenciaDomestica(EntityManager entityManager, Pageable pageable) {
//    	final Integer FK__VIOLENCIA_DOMESTICA = 1064;
//        QBoDeon qBoDeon = QBoDeon.boDeon;
//        QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;
//        QNaturezaDeon qNaturezaDeon = QNaturezaDeon.naturezaDeon;
//        QEnvolvimento qEnvolvimento = QEnvolvimento.envolvimento;
//        QEnvolvido qEnvolvido = QEnvolvido.envolvido;
//        QPessoa qPessoa = QPessoa.pessoa;
//
//        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//
//        //subconsulta
//        JPAQuery<Long> countQuery = queryFactory
//                .select(qBoDeon.idBo.count())
//                .from(qBoDeon)
//                .join(qBoDeon.listaNaturezas, qNaturezaBo)
//                .join(qNaturezaBo.naturezaDeon, qNaturezaDeon)
//                .join(qEnvolvimento).on(qEnvolvimento.naturezaBo.id.eq(qNaturezaBo.id))
//                .join(qEnvolvimento.envolvido, qEnvolvido)
//                .join(qEnvolvido.pessoa, qPessoa)
//                .where(qPessoa.id.isNotNull().and(qNaturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)));
//
//        //total de resultados da subconsulta
//        long total = countQuery.fetchOne();
//        List<BoDeon> bosPendentes = queryFactory
//                .selectFrom(qBoDeon)
//                .distinct()
//                .join(qBoDeon.listaNaturezas, qNaturezaBo)
//                .join(qNaturezaBo.naturezaDeon, qNaturezaDeon)
//                .join(qEnvolvimento).on(qEnvolvimento.naturezaBo.id.eq(qNaturezaBo.id))
//                .join(qEnvolvimento.envolvido, qEnvolvido)
//                .join(qEnvolvido.pessoa, qPessoa)
//                .where(qPessoa.id.isNotNull().and(qNaturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        return new PageImpl<>(bosPendentes, pageable, total);
//    }
    
//    /**
//     * @author hilbertofilho
//     * encontra bos não analisados que não sejam violencia domestica
//     * 
//     * */
//    public Page<BoAnalise> findByStatusFalseNotViolenciaDomestica(EntityManager entityManager, Pageable pageable) {
//
//    	final Integer FK__VIOLENCIA_DOMESTICA = 1064;
//        QBoAnalise qBoAnalise = QBoAnalise.boAnalise;
//        QBoDeon qBoDeon = QBoDeon.boDeon;
//        QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;
//
//        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//
//        //subconsulta para contar o total de resultados
//        JPAQuery<Long> countQuery = queryFactory
//                .select(qBoAnalise.idAnalise.count())
//                .from(qBoAnalise)
//                .join(qBoAnalise.boDeon, qBoDeon)
//                .join(qBoDeon.listaNaturezas, qNaturezaBo)
//                .where(qBoAnalise.status.isFalse().and(qNaturezaBo.naturezaDeon.naturezaSigma.ne(FK__VIOLENCIA_DOMESTICA)));
//
//        //total de resultados
//        long total = countQuery.fetchOne();
//
//        //consulta principal
//        List<BoAnalise> bosPendentes = queryFactory
//                .selectFrom(qBoAnalise)
//                .distinct()
//                .join(qBoAnalise.boDeon, qBoDeon)
//                .join(qBoDeon.listaNaturezas, qNaturezaBo)
//                .where(qBoAnalise.status.isFalse().and(qNaturezaBo.naturezaDeon.naturezaSigma.ne(FK__VIOLENCIA_DOMESTICA)))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//        return new PageImpl<>(bosPendentes, pageable, total);
//    }

//    /**
//     * @author hilbertofilho
//     * encontra bos analisados que não sejam violência domestica
//     * 
//     * */
//	@Override
//	public Page<BoAnalise> findByStatusTrueNotViolenciaDom(EntityManager entityManager, Pageable pageable) {
//		final Integer FK__VIOLENCIA_DOMESTICA = 1064;
//	    QBoAnalise qBoAnalise = QBoAnalise.boAnalise;
//	    QBoDeon qBoDeon = QBoDeon.boDeon;
//	    QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;
//
//	    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//	    JPAQuery<Long> countQuery = queryFactory
//	            .select(qBoAnalise.idAnalise.count())
//	            .from(qBoAnalise)
//	            .join(qBoAnalise.boDeon, qBoDeon)
//	            .join(qBoDeon.listaNaturezas, qNaturezaBo)
//	            .where(qBoAnalise.status.isTrue().and(qNaturezaBo.naturezaDeon.naturezaSigma.ne(FK__VIOLENCIA_DOMESTICA)));
//
//	    Long total = countQuery.fetchOne();
//	    List<BoAnalise> bosPendentes = queryFactory
//	            .selectFrom(qBoAnalise)
//	            //.distinct()
//	            .join(qBoAnalise.boDeon, qBoDeon)
//	            .join(qBoDeon.listaNaturezas, qNaturezaBo)
//	            .where(qBoAnalise.status.isTrue().and(qNaturezaBo.naturezaDeon.naturezaSigma.ne(FK__VIOLENCIA_DOMESTICA)))
//	            .distinct()
//	            .offset(pageable.getOffset())
//	            .limit(pageable.getPageSize())
//	            .fetch();
//	    List<BosAnalisadosResponseDto> bos = new ArrayList<>();
//	    bosPendentes.forEach(bo -> {
//	    	BosAnalisadosResponseDto bosAnalisadosResponseDto = analiseService.getBoAnalisetoBosAnalisadosResponseDto(bo);
//	    	bos.add(bosAnalisadosResponseDto);
//	    });
//	    return new PageImpl<>(bosPendentes, pageable, total);
//	}
}
