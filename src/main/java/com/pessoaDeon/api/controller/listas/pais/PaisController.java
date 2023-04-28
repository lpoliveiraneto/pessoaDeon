package com.pessoaDeon.api.controller.listas.pais;

import com.pessoaDeon.domain.model.Pais;
import com.pessoaDeon.domain.repository.listas.pais.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pais")
@CrossOrigin(origins= "*")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping
    public List<Pais> listarPais(){
        return paisRepository.findAll();
    }

}
