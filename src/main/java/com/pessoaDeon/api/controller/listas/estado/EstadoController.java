package com.pessoaDeon.api.controller.listas.estado;

import com.pessoaDeon.domain.model.Estado;
import com.pessoaDeon.domain.repository.listas.estado.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estados")
@CrossOrigin(origins= "*")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> listarEstado(){
        return estadoRepository.findAll();
    }
}
