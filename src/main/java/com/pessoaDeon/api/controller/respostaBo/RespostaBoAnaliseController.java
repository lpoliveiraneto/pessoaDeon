package com.pessoaDeon.api.controller.respostaBo;

import com.pessoaDeon.domain.model.analista.RespostaAnaliseBo;
import com.pessoaDeon.domain.repository.respostaBo.RespostaAnaliseBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/lista/respostaOcorrencia")
public class RespostaBoAnaliseController {

    @Autowired
    private RespostaAnaliseBoRepository respostabo;

    @GetMapping("/todas")
    public List<RespostaAnaliseBo> listaRespostaBo(){
        return respostabo.findAll();
    }
    
    @GetMapping
    public List<RespostaAnaliseBo> listarRespostaRecusa(){
    	final int BOLETIM_ESTÁ_VALIDO = 0;
    	var lista = respostabo.findAll();
    	lista.remove(BOLETIM_ESTÁ_VALIDO);
    	return lista;
    }
}
