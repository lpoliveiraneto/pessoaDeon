package com.pessoaDeon.api.controller.listas.estado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.listas.Estado;
import com.pessoaDeon.domain.repository.listas.estado.EstadoRepository;

@RestController
@RequestMapping("/api/v1/lista/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> listarEstado(){
        return estadoRepository.findAll();
    }
}
