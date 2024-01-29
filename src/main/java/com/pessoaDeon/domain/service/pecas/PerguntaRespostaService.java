package com.pessoaDeon.domain.service.pecas;

import java.util.ArrayList;
import java.util.List;

import com.pessoaDeon.domain.model.pecas.formulario_risco.TipoRespostaFormularioRisco;
import com.pessoaDeon.domain.model.pecas.formulario_risco.PerguntaRespostaFormularioRisco;
import com.pessoaDeon.domain.model.pecas.requerimento_mpu.TituloRequerimentoMpu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.dto.pecas.PerguntaRespostaDto;
import com.pessoaDeon.domain.model.dto.pecas.TipoPerguntaPecaDto;
import com.pessoaDeon.domain.model.pecas.formulario_risco.TipoPerguntaFormularioRisco;
//import com.pessoaDeon.domain.model.pecas.TituloRequerimentoMpu;
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
        List<TipoPerguntaFormularioRisco> perguntasPorBloco = listaPerguntasPorBloco(bloco);
        PerguntaRespostaDto list = new PerguntaRespostaDto();
        
        if (!perguntasPorBloco.isEmpty()) {
            list.setBlocoDescricao(perguntasPorBloco.get(0).getBloco().getDescricao());

            for (TipoPerguntaFormularioRisco pergunta : perguntasPorBloco) {
                listaResposta.addAll(mapearPerguntaRespostaParaDto(pergunta).getTipoPerguntaPeca());
            }

            list.setTipoPerguntaPeca(listaResposta);
        }
        
        return list;
    }

    private PerguntaRespostaDto mapearPerguntaRespostaParaDto(TipoPerguntaFormularioRisco tipoPerguntaFormularioRisco) {
        PerguntaRespostaDto perguntaRespostaDto = new PerguntaRespostaDto();
        List<TipoPerguntaPecaDto> listaTipoPerguntaPecaDto = new ArrayList<>();
        List<PerguntaRespostaFormularioRisco> resultado = findByPerguntaAndAtivaIsTrueOrderByIdAsc(tipoPerguntaFormularioRisco);

        listaTipoPerguntaPecaDto.add(montaDto(resultado));
        perguntaRespostaDto.setTipoPerguntaPeca(listaTipoPerguntaPecaDto);
        return perguntaRespostaDto;
    }

    private TipoPerguntaPecaDto montaDto(List<PerguntaRespostaFormularioRisco> resposta) {
//        List<Integer> listaIdsPerguntaResposta = new ArrayList<>();
        List<TipoRespostaFormularioRisco> listaResposta = new ArrayList<>();
        TipoPerguntaPecaDto tipoPerguntaPecaDto = new TipoPerguntaPecaDto();

        for (PerguntaRespostaFormularioRisco perguntaRespostaFormularioRisco : resposta) {
//            listaIdsPerguntaResposta.add(perguntaResposta.getId());
            tipoPerguntaPecaDto.setId(perguntaRespostaFormularioRisco.getPergunta().getId());
            tipoPerguntaPecaDto.setAtivo(perguntaRespostaFormularioRisco.getPergunta().getAtivo());
            tipoPerguntaPecaDto.setPergunta(perguntaRespostaFormularioRisco.getPergunta().getPergunta());
            listaResposta.add(perguntaRespostaFormularioRisco.getResposta());
        }

//        tipoPerguntaPecaDto.setFkPerguntaResposta(listaIdsPerguntaResposta); 
        tipoPerguntaPecaDto.setTipoRespostaFormularioRisco(listaResposta);

        return tipoPerguntaPecaDto;
    }


    public List<PerguntaRespostaFormularioRisco> obterRespostasPorPergunta(Integer perguntaId) {
        return perguntaRespostaRepository.findByPerguntaId(perguntaId);
    }

    private List<TipoPerguntaFormularioRisco> listaPerguntasPorBloco(Integer bloco) {
    	return tipoPerguntaRepository.findByAtivoIsTrueAndBlocoIdOrderByIdAsc(bloco);
    }

    private List<PerguntaRespostaFormularioRisco> findByPerguntaAndAtivaIsTrueOrderByIdAsc(TipoPerguntaFormularioRisco tipoPerguntaFormularioRisco){
        return perguntaRespostaRepository.findByPerguntaAndAtivaIsTrueOrderByIdAsc(tipoPerguntaFormularioRisco);
    }

    /**
     *
     * @param :tp
     * @return retorna a lista de titulos para a medida protetiva
     */
    public List<TituloRequerimentoMpu> listaTituloRequerimento() {
        List<TituloRequerimentoMpu> lista = tituloRequerimentoMpuRepository.findByStatusAtivoIsTrue();
        return lista;
    }
}
