package com.pessoaDeon.domain.service.bo;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.bo.EnderecoLocalFato;
import com.pessoaDeon.domain.model.bo.Protocolo;
import com.pessoaDeon.domain.model.bo.QBoDeon;
import com.pessoaDeon.domain.model.bo.QProtocolo;
import com.pessoaDeon.domain.model.dto.BoDto;
import com.pessoaDeon.domain.model.dto.BoDtoResponse;
import com.pessoaDeon.domain.model.dto.BosPendentesResponseDto;
import com.pessoaDeon.domain.model.dto.BosPessoaResponseDto;
import com.pessoaDeon.domain.model.dto.EnvolvidoBoDto;
import com.pessoaDeon.domain.model.dto.NaturezaDeonResponseDto;
import com.pessoaDeon.domain.model.dto.integracao.BoResponseDto;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.envolvido.Envolvido;
import com.pessoaDeon.domain.model.envolvido.Envolvimento;
import com.pessoaDeon.domain.model.envolvido.QEnvolvido;
import com.pessoaDeon.domain.model.envolvido.QEnvolvimento;
import com.pessoaDeon.domain.model.natureza.QNaturezaBo;
import com.pessoaDeon.domain.model.natureza.QNaturezaDeon;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.pessoa.QPessoa;
import com.pessoaDeon.domain.model.util.EnumToObject;
import com.pessoaDeon.domain.repository.bo.BoRepository;
import com.pessoaDeon.domain.repository.bo.EnderecoLocalFatoRepository;
import com.pessoaDeon.domain.repository.bo.ProtocoloRepository;
import com.pessoaDeon.domain.repository.envolvido.EnvolvimentoRepository;
import com.pessoaDeon.domain.service.envolvido.EnvolvimentoService;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;


@Service
public class BoService {

	@Autowired
	private BoRepository boRepository; 
	
	@Autowired
	private EnderecoLocalFatoRepository enderecoLocalFatoRepository;
	
	@Autowired
	private ProtocoloRepository protocoloRepository;
	
	@Autowired
	private EnvolvimentoRepository envolvimentoRepository;

	@Autowired
	private EnvolvimentoService envolvimentoService;

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public BoDeon salvar(BoDto bo) {
		BoDeon boletim = new BoDeon();
		var dataRegistro = Calendar.getInstance().getTime();
		
		boletim.setDataFato(bo.getDataFato());
		boletim.setHoraFato(bo.getHoraFato());
		boletim.setDataRegistro(dataRegistro); 
		boletim.setRelato(bo.getRelato());
		boletim.setRelatoEditado(bo.getRelatoEditado());
		boletim.setStatus(Status.PE);
		var boSave = boRepository.saveAndFlush(boletim);
		if(boSave != null) {
			this.salvarEnd(bo, boSave);
			this.salvarProtocolo(boSave);
		}
		return boSave;
	}
	
	@Transactional
	private EnderecoLocalFato salvarEnd(BoDto bo, BoDeon deon) {
		EnderecoLocalFato end = new EnderecoLocalFato();
		end.setCep(bo.getCep());
		end.setComplemento(bo.getComplemento());
		end.setLogradouro(bo.getLogradouro());
		end.setNumeroLocal(bo.getNumeroLocal());
		end.setBairro(bo.getBairro());
		end.setCidade(bo.getCidade());
		end.setEstado(bo.getEstado());
		end.setTipoLocal(bo.getTipoLocal());
		end.setReferencia(bo.getReferencia());
		end.setBo(deon);
		return enderecoLocalFatoRepository.save(end);
	}
	
	@Transactional
	private Protocolo salvarProtocolo(BoDeon bo) {
		Protocolo protocolo = new Protocolo();
		protocolo.setBo(bo);
		protocolo.setNumero(gerarProtocolo(bo));
		protocolo.setDataRegistro(LocalDateTime.now());
		return protocoloRepository.save(protocolo);
	}
	
	private String gerarProtocolo(BoDeon ocorrencia) {

		StringBuilder protocolo = new StringBuilder();
		
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(ocorrencia.getDataRegistro());		
		
		protocolo.append(ocorrencia.getIdBo());
		protocolo.append(calendario.get(Calendar.HOUR));
		protocolo.append(calendario.get(Calendar.MONTH));
		protocolo.append(calendario.get(Calendar.YEAR));
		protocolo.append(calendario.get(Calendar.SECOND));
		return protocolo.toString();
	}
	
	@Transactional
	public BoDtoResponse buscarBoPorId(BoDeon bo) {
		BoDtoResponse response = new BoDtoResponse();
		EnderecoLocalFato endereco = enderecoLocalFatoRepository.findById(bo.getIdBo()).get();
		Protocolo protocolo = protocoloRepository.findById(bo.getIdBo()).get();
		response.setDataFato(bo.getDataFato());
		response.setHoraFato(bo.getHoraFato());
		response.setRelato(bo.getRelato());
		response.setRelatoEditado(bo.getRelatoEditado());
		response.setLogradouro(endereco.getLogradouro());
		response.setReferencia(endereco.getReferencia());
		response.setBairroDescricao(endereco.getBairro().getDescricao());
		response.setComplemento(endereco.getComplemento());
		response.setCidadeDescricao(endereco.getCidade().getDescricao());
		response.setEstadoDescricao(endereco.getEstado().getDescricao());
		response.setCep(endereco.getCep());
		response.setNumeroLocal(endereco.getNumeroLocal());
		response.setTipoLocal(endereco.getTipoLocal().getNome());
		response.setProtocolo(protocolo.getNumero());
		response.setStatus(new EnumToObject(bo.getStatus().name(), bo.getStatus().getDescricao()));
		List<NaturezaDeonResponseDto> naturezaDto = new ArrayList<>();
		bo.getListaNaturezas().forEach(n -> {
			NaturezaDeonResponseDto nt = new NaturezaDeonResponseDto();
			BeanUtils.copyProperties(n.getNaturezaDeon(), nt);
			naturezaDto.add(nt);
		});
		response.setListaNatureza(naturezaDto);
		var listaEnvolvimento = envolvimentoService.getListaEnvolvimentoBo(bo.getIdBo());
		List<EnvolvidoBoDto> listaEnvolvidosBo = new ArrayList<>();
		listaEnvolvimento.forEach(e -> {
			listaEnvolvidosBo.add(montaDtoTipoEnvolvido(e));
		});
		response.setListaEnvolvidos(listaEnvolvidosBo);
		return response;
	}

	private EnvolvidoBoDto montaDtoTipoEnvolvido(Envolvimento envolvimento) {
		if (envolvimento.getEnvolvido().getPessoa() != null) {
			Pessoa comunicante = envolvimento.getEnvolvido().getPessoa();
			EnvolvidoBoDto env = new EnvolvidoBoDto();
			env.setDataNascimento(comunicante.getDataNascimento());
			env.setIdEnvolvido(envolvimento.getEnvolvido().getIdEnvolvido());
			env.setNome(comunicante.getNome());
			env.setNomeMae(comunicante.getNomeMae());
			env.setTipoParticipacao(envolvimento.getTipoParticipacao().getDescricao());
			return env;
		}

		if (envolvimento.getEnvolvido() != null) {
			Envolvido envolvido = envolvimento.getEnvolvido();
			EnvolvidoBoDto env = new EnvolvidoBoDto();
			env.setDataNascimento(envolvido.getDataNascimento() != null ? envolvido.getDataNascimento() : null);
			env.setIdEnvolvido(envolvido.getIdEnvolvido());
			env.setNome(envolvido.getNome() != null ? envolvido.getNome() : "NÃO INFORMADO");
			env.setNomeMae(envolvido.getNomeMae() != null ? envolvido.getNomeMae() : "NÃO INFORMADO");
			env.setTipoParticipacao(envolvimento.getTipoParticipacao() != null ? envolvimento.getTipoParticipacao().getDescricao() : "NÃO ENCONTREI");
			return env;
		}
		return null;
	}

	@ReadOnlyProperty
	public Page<BosPessoaResponseDto> buscarBosPessoa(Integer idPessoa, Pageable pageable) {
		QBoDeon qBoDeon = QBoDeon.boDeon;
		QNaturezaBo qNaturezaBo = QNaturezaBo.naturezaBo;
		QNaturezaDeon qNaturezaDeon = QNaturezaDeon.naturezaDeon;
		QProtocolo qProtocolo = QProtocolo.protocolo;
		QEnvolvimento qEnvolvimento = QEnvolvimento.envolvimento;
		QEnvolvido qEnvolvido = QEnvolvido.envolvido;
		QPessoa qPessoa = QPessoa.pessoa;

		JPAQuery<BoDeon> query = new JPAQueryFactory(entityManager).selectFrom(qBoDeon).distinct();
		query.join(qBoDeon.listaNaturezas, qNaturezaBo);
		query.join(qNaturezaBo.naturezaDeon, qNaturezaDeon);
		query.join(qProtocolo).on(qProtocolo.bo.idBo.eq(qBoDeon.idBo));
		query.join(qEnvolvimento).on(qEnvolvimento.naturezaBo.id.eq(qNaturezaBo.id));
		query.join(qEnvolvimento.envolvido, qEnvolvido);
		query.join(qEnvolvido.pessoa, qPessoa);
		query.where(qPessoa.id.eq(idPessoa)).fetch();

		int countResults = query.fetch().size();
		List<BoDeon> results = query.offset(pageable.getOffset()).limit(pageable.getPageSize())
			.orderBy(qBoDeon.idBo.desc()).fetch();

		List<BosPessoaResponseDto> resultsDto = new ArrayList<>();
		results.forEach(bo -> {
			BosPessoaResponseDto dto = new BosPessoaResponseDto();
			dto.setIdBo(bo.getIdBo());
			dto.setDataRegistro(bo.getDataRegistro());
			dto.setProtocolo(protocoloRepository.findByBo(bo).getNumero());
			dto.setNomeNatureza(bo.getListaNaturezas().get(0).getNaturezaDeon().getNome());
			dto.setCodigoNatureza(bo.getListaNaturezas().get(0).getNaturezaDeon().getCodigo());
			dto.setStatus(new EnumToObject(bo.getStatus().name(), bo.getStatus().getDescricao()));
			resultsDto.add(dto);
		});
		return new PageImpl<>(resultsDto, pageable, countResults);
	}

	public Optional<BoDeon> findById(Integer idBo) {
		return boRepository.findById(idBo);
	}
	
	@Transactional
	public BoDeon atualizarNumeroStatus(BoResponseDto response) {
		BoDeon bo = findById(response.getIdBoDeon()).orElse(null);
		if (bo != null) {
			bo.setNumeroBo(response.getNumeroBo());
			bo.setStatus(Status.VA);
			return boRepository.saveAndFlush(bo);
		}
		return null;
	}

	public Page<BosPendentesResponseDto> getBosPendentes(Pageable pageable){
		List<BoDeon> bosPendentes = boRepository.findByStatusEquals(Status.PE);
		List<BosPendentesResponseDto> bos = new ArrayList<BosPendentesResponseDto>();
		bosPendentes.forEach( b-> {
			BosPendentesResponseDto bo = new BosPendentesResponseDto();
			bo.setIdBo(b.getIdBo());
			var natureza = b.getListaNaturezas().get(0).getNaturezaDeon();
			var codigo = ((natureza.getCodigo() != null && !natureza.getCodigo().isBlank()) ? " - " + natureza.getCodigo() : "");
			bo.setNatureza(natureza.getNome() + codigo);
			bo.setDataDoRegistro(LocalDateTime.ofInstant(b.getDataRegistro().toInstant(), ZoneId.systemDefault()));
			var envolvimento = envolvimentoRepository.findByNaturezaBoBoIdBoAndTipoParticipacaoValorOrNaturezaBoBoIdBoAndTipoParticipacaoValor(b.getIdBo(),"CM", b.getIdBo(), "CV");
			bo.setNome(envolvimento.getEnvolvido() != null ? envolvimento.getEnvolvido().getPessoa().getNome() : "NÃO CONSEGUI ENCONTRAR");
			bo.setProtocolo(protocoloRepository.findByBo(b).getNumero());
			bos.add(bo);
		});
		return new PageImpl<>(bos, pageable, bos.size());
	}

	@ReadOnlyProperty
	public Boolean verificaBoEmAnalise(Integer idBo) {
		return boRepository.existsByIdBoAndStatus(idBo, Status.EA);
	}

	public void mudaStatusBoEmAnalise(BoDeon bo, Status status) {
		if (bo != null) {
			bo.setStatus(status);
			boRepository.saveAndFlush(bo);
		}
	}
	
}

