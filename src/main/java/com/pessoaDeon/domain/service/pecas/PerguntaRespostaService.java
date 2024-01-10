package com.pessoaDeon.domain.service.pecas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.dto.pecas.PerguntaRespostaDto;
import com.pessoaDeon.domain.model.dto.pecas.TipoPerguntaPecaDto;
import com.pessoaDeon.domain.model.pecas.PerguntaResposta;
import com.pessoaDeon.domain.model.pecas.TipoPeca;
import com.pessoaDeon.domain.model.pecas.TipoPerguntaPeca;
import com.pessoaDeon.domain.model.pecas.TipoRespostaPeca;
import com.pessoaDeon.domain.repository.pecas.PerguntaRespostaRepository;
import com.pessoaDeon.domain.repository.pecas.TipoPecaRepository;
import com.pessoaDeon.domain.repository.pecas.TipoPerguntaRepository;

@Service
public class PerguntaRespostaService {

  @Autowired
  private TipoPerguntaRepository tipoPerguntaRepository;

  @Autowired
  private PerguntaRespostaRepository perguntaRespostaRepository;

  @Autowired
  private TipoPecaRepository tipoPecaRepository;

  public PerguntaRespostaDto listaResposta(TipoPeca tp, Integer bloco) {
    List<TipoPerguntaPecaDto> listaResposta = new ArrayList<>();
    List<TipoPerguntaPeca> perguntasPorBloco = listaPerguntasPorBloco(tp, bloco);
    PerguntaRespostaDto list = new PerguntaRespostaDto();
    
    if (!perguntasPorBloco.isEmpty()) {
        list.setBlocoDescricao(perguntasPorBloco.get(0).getBloco().getDescricao());

        for (TipoPerguntaPeca pergunta : perguntasPorBloco) {
            listaResposta.addAll(mapearPerguntaRespostaParaDto(pergunta).getTipoPerguntaPeca());
        }

        list.setTipoPerguntaPeca(listaResposta);
    }
    
    return list;
}

private PerguntaRespostaDto mapearPerguntaRespostaParaDto(TipoPerguntaPeca tipoPerguntaPeca) {
    PerguntaRespostaDto perguntaRespostaDto = new PerguntaRespostaDto();
    List<TipoPerguntaPecaDto> listaTipoPerguntaPecaDto = new ArrayList<>();
    List<PerguntaResposta> resultado = findByPerguntaAndAtivaIsTrueOrderByIdAsc(tipoPerguntaPeca);

    listaTipoPerguntaPecaDto.add(montaDto(resultado));
    perguntaRespostaDto.setTipoPerguntaPeca(listaTipoPerguntaPecaDto);
    return perguntaRespostaDto;
}

private TipoPerguntaPecaDto montaDto(List<PerguntaResposta> resposta){
    List<TipoRespostaPeca> listaResposta = new ArrayList<>();
    TipoPerguntaPecaDto tipoPerguntaPecaDto = new TipoPerguntaPecaDto();

    for (PerguntaResposta tipoRespostaPeca : resposta) {
        tipoPerguntaPecaDto.setId(tipoRespostaPeca.getPergunta().getId());
        tipoPerguntaPecaDto.setAtivo(tipoRespostaPeca.getPergunta().getAtivo());
        tipoPerguntaPecaDto.setPergunta(tipoRespostaPeca.getPergunta().getPergunta());
        tipoPerguntaPecaDto.setTipoPeca(tipoRespostaPeca.getPergunta().getTipoPeca().getIdTipo());
        listaResposta.add(tipoRespostaPeca.getResposta());
    }

    tipoPerguntaPecaDto.setTipoRespostaPeca(listaResposta);

    return tipoPerguntaPecaDto;
}

public List<PerguntaResposta> obterRespostasPorPergunta(Integer perguntaId) {
    return perguntaRespostaRepository.findByPerguntaId(perguntaId);
}

private List<TipoPerguntaPeca> listaPerguntasPorBloco(TipoPeca tp, Integer bloco) {
    TipoPeca peca = tipoPecaRepository.findById(tp.getIdTipo()).orElse(null);

    if (peca != null) {
        return tipoPerguntaRepository.findByTipoPecaAndAtivoIsTrueAndBlocoIdOrderByIdAsc(peca, bloco);
    } else {
        return Collections.emptyList();
    }
}

private List<PerguntaResposta> findByPerguntaAndAtivaIsTrueOrderByIdAsc(TipoPerguntaPeca tipoPerguntaPeca){
    return perguntaRespostaRepository.findByPerguntaAndAtivaIsTrueOrderByIdAsc(tipoPerguntaPeca);
}
}
