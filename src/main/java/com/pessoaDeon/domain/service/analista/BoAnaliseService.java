package com.pessoaDeon.domain.service.analista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.exception.BoAnaliseNotFoundException;
import com.pessoaDeon.domain.exception.BoNotFoundException;
import com.pessoaDeon.domain.model.analista.BoAnalise;
import com.pessoaDeon.domain.model.analista.QBoAnalise;
import com.pessoaDeon.domain.model.bo.QBoDeon;
import com.pessoaDeon.domain.model.dto.bo.BoAnaliseRequest;
import com.pessoaDeon.domain.model.dto.bo.BosAnalisadosResponseDto;
import com.pessoaDeon.domain.model.dto.integracao.BoRequestDto;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.natureza.QNaturezaBo;
import com.pessoaDeon.domain.repository.bo.ProtocoloRepository;
import com.pessoaDeon.domain.repository.boAnalise.BoAnaliseRepository;
import com.pessoaDeon.domain.repository.respostaBo.RespostaAnaliseBoRepository;
import com.pessoaDeon.domain.service.bo.BoService;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;


@Service
public class BoAnaliseService {

    @Autowired
    private BoAnaliseRepository boAnaliseRepository;
    @Autowired
    private ProtocoloRepository protocoloRepository;
    @Autowired
    private BoService boService;
    @Autowired
    private AnalistaService analistaService;
    @Autowired
    private RespostaAnaliseBoRepository respostaAnaliseBoRepository;

    @Autowired
    private EntityManager entityManager;

    public Page<BosAnalisadosResponseDto> getBoAnalise(Pageable pageable){

        Page<BoAnalise> bosAnalise = boAnaliseRepository.findAll(pageable);
        List<BosAnalisadosResponseDto> bos = new ArrayList<>();
        bosAnalise.forEach(b -> {
            BosAnalisadosResponseDto bo = getBoAnalisetoBosAnalisadosResponseDto(b);
            bos.add(bo);
        });

        return new PageImpl<>(bos, pageable, bos.size());
    }

    /**
     * A implementacao desse metodo abaixo era feita em metodo aqui dentro da classe, porém esta desativada, em caso de alterações, eles estão todos funcionando
     * */
    public Page<BosAnalisadosResponseDto> getBoAnalisados(Pageable pageable){
        Page<BosAnalisadosResponseDto> bosAnalisados = findByStatusTrueNotViolenciaDomestica(entityManager, pageable);
        return bosAnalisados;
    }

    /**
     * A implementacao desse metodo abaixo era feita em metodo aqui dentro da classe, porém esta desativada, em caso de alterações, eles estão todos funcionando
     * */
    public Page<BosAnalisadosResponseDto> getBoEmAnalise(Pageable pageable){
        Page<BosAnalisadosResponseDto> bosEmAnalise = findByStatusFalseNotViolenciaDomestica(entityManager, pageable);
        return bosEmAnalise;
    }
    
    /**
     * A implementacao desse metodo abaixo era feita em metodo aqui dentro da classe, porém esta desativada, em caso de alterações, eles estão todos funcionando
     * */
    public Page<BosAnalisadosResponseDto> getBoAnalisadosViolenciaDomestica(Pageable pageable){
    	Page<BosAnalisadosResponseDto> bosAnalisadosViolencia = findByStatusTrueViolenciaDomestica(entityManager, pageable);
    	return bosAnalisadosViolencia;
    }

    /**
     * A implementacao desse metodo abaixo era feita em metodo aqui dentro da classe, porém esta desativada, em caso de alterações, eles estão todos funcionando
     * */
    public Page<BosAnalisadosResponseDto> getBoEmAnaliseViolenciaDomestica(Pageable pageable){
        Page<BosAnalisadosResponseDto> bosEmAnaliseViolencia = findByStatusFalseViolenciaDomestica(entityManager, pageable);
        return bosEmAnaliseViolencia;
    }

    private BosAnalisadosResponseDto getBoAnalisetoBosAnalisadosResponseDto(BoAnalise b) {
        BosAnalisadosResponseDto bo = new BosAnalisadosResponseDto();
        bo.setIdBo(b.getBoDeon().getIdBo());
        bo.setProtocolo(protocoloRepository.findByBoIdBo(b.getBoDeon().getIdBo()).getNumero());
        bo.setNomeAnalista(b.getAnalista().getNome());
        var natureza = b.getBoDeon().getListaNaturezas().get(0).getNaturezaDeon();
        var codigo = ((natureza.getCodigo() != null && !natureza.getCodigo().isBlank()) ? " - " + natureza.getCodigo() : "");
        bo.setNatureza(natureza.getNome() + codigo);
        bo.setDataAnalise(b.getDataAnalise());
        bo.setDataRegistro(b.getBoDeon().getDataRegistro());
        return bo;
    }

    @Transactional
    public void salvarBoEmAnalise(BoAnaliseRequest boAnaliseRequest, HttpServletRequest request, Status status) {
        Optional<BoAnalise> analise = boAnaliseRepository.findByBoDeon_IdBo(boAnaliseRequest.fkBo());
        BoAnalise boAnalise = analise.orElseGet(BoAnalise::new);

        var analista = analistaService.getAnalistaToken(request);
        var bodeon = boService.findById(boAnaliseRequest.fkBo())
                .orElseThrow(() -> new BoNotFoundException("Não existe analista com esse id"));

        if (bodeon != null) {
        	boService.mudaStatusBoEmAnalise(bodeon, status);
		}
        boAnalise.setDataEntradaAnalise(LocalDateTime.now());
        boAnalise.setAnalista(analista);
        boAnalise.setBoDeon(bodeon);
        boAnaliseRepository.save(boAnalise);
    }

    @Transactional
    public void aprovaBoEmAnalise(BoRequestDto request, Integer idResposta){

    	var resposta = respostaAnaliseBoRepository.findById(idResposta).orElseThrow(() ->
        	new BoAnaliseNotFoundException("Resposta não encontrado na base de dados"));
        var boDeon = boService.findById(request.getIdBoDeon())
               .orElseThrow(() -> new BoAnaliseNotFoundException("Não existe analise para esse id"));
        var analiseBo = boAnaliseRepository.findByBoDeon_IdBo(boDeon.getIdBo()).get();
        
        try {
        	if(boDeon.getStatus().equals(Status.EA)){
        		analiseBo.setStatus(true);
        		analiseBo.setDataAnalise(LocalDateTime.now());
        		analiseBo.setRespostaAnaliseBo(resposta);
    		Status novoStatus = (analiseBo.getRespostaAnaliseBo().getIdRespostaAnalise() != idResposta) ? Status.IV : Status.VA;
    		boService.mudaStatusBoEmAnalise(analiseBo.getBoDeon(), novoStatus);
    		boAnaliseRepository.saveAndFlush(analiseBo);
        	}
			
		} catch (RuntimeException e) {
			new BoAnaliseNotFoundException("Operação indisponivel com esse status");
		}
    }

	public void recusarBoEmAnalise(BoAnaliseRequest boRequest, Integer idResposta, HttpServletRequest request) {
		var resposta = respostaAnaliseBoRepository.findById(idResposta).orElseThrow(() ->
			new BoAnaliseNotFoundException("Resposta não encontrado na base de dados"));
		var boDeon = boService.findById(boRequest.fkBo())
				.orElseThrow(() -> new BoAnaliseNotFoundException("Não existe analise para esse id"));
		var analiseBo = boAnaliseRepository.findByBoDeon_IdBo(boDeon.getIdBo()).get();
		
		try {
			if (boDeon.getStatus().equals(Status.EA)) {
				analiseBo.setStatus(true);
				analiseBo.setAnalista(analistaService.getAnalistaToken(request));
				analiseBo.setDataAnalise(LocalDateTime.now());
				analiseBo.setRespostaAnaliseBo(resposta);
				
				Status status = (analiseBo.getRespostaAnaliseBo().getIdRespostaAnalise() != idResposta) ? Status.VA : Status.IV;
	    		boService.mudaStatusBoEmAnalise(analiseBo.getBoDeon(), status);
	    		boAnaliseRepository.saveAndFlush(analiseBo);
			}
		} catch (RuntimeException e) {
			throw new BoAnaliseNotFoundException("Operação indisponivel com esse status");
		}
	}
	
	  /**
	  * @author hilbertofilho
	  * encontra bos analisados que não sejam violência domestica
	  * 
	  * */
	public Page<BosAnalisadosResponseDto> findByStatusTrueNotViolenciaDomestica(EntityManager entityManager, Pageable pageable) {
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
	            .where(qBoAnalise.status.isTrue().and(qNaturezaBo.naturezaDeon.naturezaSigma.ne(FK__VIOLENCIA_DOMESTICA)));

	    Long total = countQuery.fetchOne();
	    List<BoAnalise> bosPendentes = queryFactory
	            .selectFrom(qBoAnalise)
	            .distinct()
	            .join(qBoAnalise.boDeon, qBoDeon)
	            .join(qBoDeon.listaNaturezas, qNaturezaBo)
	            .where(qBoAnalise.status.isTrue().and(qNaturezaBo.naturezaDeon.naturezaSigma.ne(FK__VIOLENCIA_DOMESTICA)))
	            .orderBy(qBoAnalise.dataAnalise.asc())
	            .distinct()
	            .offset(pageable.getOffset())
	            .limit(pageable.getPageSize())
	            .fetch();
	    List<BosAnalisadosResponseDto> bos = new ArrayList<>();
	    bosPendentes.forEach(bo -> {
	    	BosAnalisadosResponseDto bosAnalisadosResponseDto = getBoAnalisetoBosAnalisadosResponseDto(bo);
	    	bos.add(bosAnalisadosResponseDto);
	    });
	    return new PageImpl<>(bos, pageable, total);
	}
	
	/**
     * @author hilbertofilho
     * encontra bos não analisados que não sejam violencia domestica
     * busca o bos que por algum motivo ficaram EM ANALISE
     * 
     * */
    public Page<BosAnalisadosResponseDto> findByStatusFalseNotViolenciaDomestica(EntityManager entityManager, Pageable pageable) {

    	final Integer FK__VIOLENCIA_DOMESTICA = 1064;
        QBoAnalise qBoAnalise = QBoAnalise.boAnalise;
        QBoDeon qBoDeon = QBoDeon.boDeon;
        QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        //subconsulta para contar o total de resultados
        JPAQuery<Long> countQuery = queryFactory
                .select(qBoAnalise.idAnalise.count())
                .from(qBoAnalise)
                .join(qBoAnalise.boDeon, qBoDeon)
                .join(qBoDeon.listaNaturezas, qNaturezaBo)
                .where(qBoAnalise.status.isFalse().and(qNaturezaBo.naturezaDeon.naturezaSigma.ne(FK__VIOLENCIA_DOMESTICA)));

        //total de resultados
        long total = countQuery.fetchOne();

        //consulta principal
        List<BoAnalise> bosEmAnalise = queryFactory
                .selectFrom(qBoAnalise)
                .distinct()
                .join(qBoAnalise.boDeon, qBoDeon)
                .join(qBoDeon.listaNaturezas, qNaturezaBo)
                .where(qBoAnalise.status.isFalse().and(qNaturezaBo.naturezaDeon.naturezaSigma.ne(FK__VIOLENCIA_DOMESTICA)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        List<BosAnalisadosResponseDto> bos = new ArrayList<>();
        bosEmAnalise.forEach(b -> {
            BosAnalisadosResponseDto bo = getBoAnalisetoBosAnalisadosResponseDto(b);
            bos.add(bo);
        });
        
        return new PageImpl<>(bos, pageable, total);
    }
    
    public Page<BosAnalisadosResponseDto> findByStatusTrueViolenciaDomestica(EntityManager entityManager, Pageable pageable) {

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
                .where(qBoAnalise.status.isTrue().and(qNaturezaBo.naturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA))
                		.and(qBoAnalise.boDeon.status.eq(Status.VA)).and(qBoAnalise.boDeon.status.eq(Status.IV)));
        long total = countQuery.fetchOne();
        List<BoAnalise> bosAnalisadosViolencia = queryFactory
                .selectFrom(qBoAnalise)
                .distinct()
                .join(qBoAnalise.boDeon, qBoDeon)
                .join(qBoDeon.listaNaturezas, qNaturezaBo)
                .where(qBoAnalise.status.isTrue().and(qNaturezaBo.naturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qBoAnalise.idAnalise.desc())
                .fetch();
        
        List<BosAnalisadosResponseDto> bos = new ArrayList<>();
        bosAnalisadosViolencia.forEach(b ->{
            BosAnalisadosResponseDto bo = getBoAnalisetoBosAnalisadosResponseDto(b);
            bos.add(bo);
        });
        return new PageImpl<>(bos, pageable, total);
    }
    
    public Page<BosAnalisadosResponseDto> findByStatusFalseViolenciaDomestica(EntityManager entityManager, Pageable pageable) {

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
                .where(qBoAnalise.status.isFalse().and(qNaturezaBo.naturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)));
        long total = countQuery.fetchOne();

        List<BoAnalise> bosEmAnalise = queryFactory
                .selectFrom(qBoAnalise)
                .distinct()
                .join(qBoAnalise.boDeon, qBoDeon)
                .join(qBoDeon.listaNaturezas, qNaturezaBo)
                .where(qBoAnalise.status.isFalse().and(qNaturezaBo.naturezaDeon.naturezaSigma.eq(FK__VIOLENCIA_DOMESTICA)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        List<BosAnalisadosResponseDto> bos = new ArrayList<>();
        bosEmAnalise.forEach(b -> {
            BosAnalisadosResponseDto bo = getBoAnalisetoBosAnalisadosResponseDto(b);
            bos.add(bo);
        });
        
        return new PageImpl<>(bos, pageable, total);
    }
}
