package com.pessoaDeon.api.controller.respostaBo;

import com.pessoaDeon.domain.model.analista.RespostaAnaliseBo;
import com.pessoaDeon.domain.repository.respostaBo.RespostaAnaliseBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/ocorrencia/respostaBo")
public class RespostaBoAnaliseController {

    @Autowired
    private RespostaAnaliseBoRepository respostabo;

    @GetMapping
    public List<RespostaAnaliseBo> listaRespostaBo(){
        return respostabo.findAll();
    }
}
