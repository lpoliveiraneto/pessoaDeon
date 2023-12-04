package com.pessoaDeon.api.controller.analista;

import com.pessoaDeon.domain.model.analista.RespostaAnaliseUsuario;
import com.pessoaDeon.domain.repository.analista.RespostaAnaliseUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/ocorrencia/respostaAnaliseUsuario")
public class RespostaAnaliseUsuarioController {

    @Autowired
    private RespostaAnaliseUsuarioRepository respostaUsuario;

    @GetMapping
    public List<RespostaAnaliseUsuario> listaRespostaAnaliseUsuario(){
        return respostaUsuario.findAll();
    }

}
