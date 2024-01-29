package com.pessoaDeon.domain.service.pecas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.dto.pecas.PerguntaRespostaDto;
import com.pessoaDeon.domain.model.dto.pecas.TipoPerguntaPecaDto;
import com.pessoaDeon.domain.model.pecas.PerguntaResposta;
import com.pessoaDeon.domain.model.pecas.TipoPerguntaPeca;
import com.pessoaDeon.domain.model.pecas.TipoRespostaPeca;
import com.pessoaDeon.domain.model.pecas.TituloRequerimentoMpu;
import com.pessoaDeon.domain.repository.pecas.PerguntaRespostaRepository;
import com.pessoaDeon.domain.repository.pecas.TipoPerguntaRepository;
import com.pessoaDeon.domain.repository.pecas.TituloRequerimentoMpuRepository;

@Service
public class PerguntaRespostaService {

  @Autowired
  private TipoPerguntaRepository tipoPerguntaRepository;

  @Autowired
  private PerguntaRespostaRepository perguntaRespostaRepository;

  @Autowired
  private TituloRequerimentoMpuRepository tituloRequerimentoMpuRepository;
  

    public PerguntaRespostaDto listaResposta(Integer bloco) {
        List<TipoPerguntaPecaDto> listaResposta = new ArrayList<>();
        List<TipoPerguntaPeca> perguntasPorBloco = listaPerguntasPorBloco(bloco);
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
            listaResposta.add(tipoRespostaPeca.getResposta());
        }

        tipoPerguntaPecaDto.setTipoRespostaPeca(listaResposta);

        return tipoPerguntaPecaDto;
    }

    public List<PerguntaResposta> obterRespostasPorPergunta(Integer perguntaId) {
        return perguntaRespostaRepository.findByPerguntaId(perguntaId);
    }

    private List<TipoPerguntaPeca> listaPerguntasPorBloco(Integer bloco) {
    	return tipoPerguntaRepository.findByAtivoIsTrueAndBlocoIdOrderByIdAsc(bloco);
    }

    private List<PerguntaResposta> findByPerguntaAndAtivaIsTrueOrderByIdAsc(TipoPerguntaPeca tipoPerguntaPeca){
        return perguntaRespostaRepository.findByPerguntaAndAtivaIsTrueOrderByIdAsc(tipoPerguntaPeca);
    }

    /**
     *
     * @param tp
     * @return retorna a lista de titulos para a medida protetiva
     */
    public List<TituloRequerimentoMpu> listaTituloRequerimento() {
        List<TituloRequerimentoMpu> lista = tituloRequerimentoMpuRepository.findByStatusAtivoIsTrue();
        return lista;
    }
}
