package com.pessoaDeon.domain.service.natureza;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.dto.DescricaoRequestDto;
import com.pessoaDeon.domain.model.dto.NaturezaDeonRequestDto;
import com.pessoaDeon.domain.model.dto.NaturezaDeonResponseDto;
import com.pessoaDeon.domain.model.dto.TituloRequestDto;
import com.pessoaDeon.domain.model.mensagem.DescricaoTitulo;
import com.pessoaDeon.domain.model.mensagem.TituloAviso;
import com.pessoaDeon.domain.model.natureza.NaturezaDeon;
import com.pessoaDeon.domain.model.natureza.NaturezaSigma;
import com.pessoaDeon.domain.model.natureza.QNaturezaSigma;
import com.pessoaDeon.domain.repository.natureza.AvisoNaturezaRepository;
import com.pessoaDeon.domain.repository.natureza.NaturezaDeonRepository;
import com.pessoaDeon.domain.repository.natureza.NaturezaSigmaRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class NaturezaService {

	@Autowired
	private NaturezaSigmaRepository naturezaRepository;

	@Autowired
	private NaturezaDeonRepository repository;

	@Autowired
	private AvisoNaturezaRepository avisoNaturezaRepository;

	@ReadOnlyProperty
	public Page<NaturezaSigma> listNatureza(Pageable pageable, String nome) {
		QNaturezaSigma naturezaSigma = QNaturezaSigma.naturezaSigma;
		BooleanBuilder bb = new BooleanBuilder();
		if (nome != null) {
			bb.and(naturezaSigma.nome.containsIgnoreCase(nome));
		}
		return naturezaRepository.findAll(bb, pageable);
	}

	@Transactional
	public Page<NaturezaSigma> filtroNome(String nome, Pageable pageable) {
		return naturezaRepository.findAllByNomeContains(nome, pageable);
	}

	@Transactional
	public Page<NaturezaSigma> filtroGlossario(String glossario, Pageable pageable) {
		return naturezaRepository.findAllByGlossarioContains(glossario, pageable);
	}

	@Transactional
	public NaturezaDeon salvar(NaturezaDeonRequestDto dto) {

		NaturezaDeon deon = new NaturezaDeon();
		deon.setNome(!dto.getNome().isEmpty() ? dto.getNome() : null);
		deon.setDescricao(dto.getGlossario());
		deon.setCodigo(dto.getCodigo());
		deon.setTipoNatureza(dto.getTipoNatureza());
		deon.setPathSvg(dto.getPathSvg());
		deon.setNaturezaSigma(dto.getIdNaturezaSigma());
		deon.setStatus(true);
		var natSave = repository.saveAndFlush(deon);

		if (natSave != null) {
			this.saveTitulo(dto.getTitulos(), natSave);
		}
		return natSave;
	}

	@Transactional
	public List<TituloAviso> saveTitulo(List<TituloRequestDto> tituloAviso, NaturezaDeon deon) {

		List<TituloAviso> titulosSalvos = new ArrayList<>();
		List<DescricaoTitulo> descTitulos = new ArrayList<>();

		tituloAviso.forEach(t -> {
			TituloAviso titulo = new TituloAviso();
			titulo.setNome(t.getNome());
			titulo.setNaturezaDeon(deon);

			List<DescricaoRequestDto> lista = t.getListaDescricaoAviso();
			lista.forEach(l -> {
				DescricaoTitulo descricaoTitulo = new DescricaoTitulo();
				descricaoTitulo.setNome(l.getNome());
				descricaoTitulo.setAviso(titulo);
				descTitulos.add(descricaoTitulo);
			});
			titulo.setListaDescricaoAviso(descTitulos);
			titulosSalvos.add(avisoNaturezaRepository.save(titulo));
		});
		return titulosSalvos;
	}
	
	public NaturezaSigma buscarNaturezaSigma(Integer idNaturezaSigma) {
		// NaturezaSigma nat =
		// naturezaRepository.findById(idNaturezaSigma).orElseThrow(() ->
		// new RuntimeException("Natureza não encontrada = " + idNaturezaSigma));
		// return nat;
		NaturezaSigma nat = naturezaRepository.findById(idNaturezaSigma).get();
		return nat;
	}

	public Boolean existeNatureza(NaturezaDeonRequestDto dto) {
		return repository.existsByNaturezaSigma(dto.getIdNaturezaSigma());
	}

	public ResponseEntity<?> listarNaturezaDeonAtiva() {
		List<NaturezaDeon> naturezas = repository.findByStatusIsTrue();
		if (!naturezas.isEmpty()) {
			return ResponseEntity.ok().body(naturezas);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe naturezas ativas");
		}
	}

	public ResponseEntity<?> listaNaturezasFront() {
		List<NaturezaDeon> naturezas = repository.findByStatusIsTrue();
		List<NaturezaDeonResponseDto> listResponse = new ArrayList<>();
		if (!naturezas.isEmpty()) {
			naturezas.forEach(n -> {
				NaturezaDeonResponseDto natDto = new NaturezaDeonResponseDto();
				BeanUtils.copyProperties(n, natDto);
				listResponse.add(natDto);
			});
			return ResponseEntity.ok().body(listResponse);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe naturezas ativas");
		}
	}
}
