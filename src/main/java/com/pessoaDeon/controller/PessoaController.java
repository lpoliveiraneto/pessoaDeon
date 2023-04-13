package com.pessoaDeon.controller;

import com.pessoaDeon.model.Pessoa;
import com.pessoaDeon.service.PessoaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listar(){
        return pessoaService.listarPessoas();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPessoa(@PathVariable Long id){
        Optional<Pessoa> pessoa = pessoaService.buscarPessoa(id);

        if(pessoa.isPresent()){
            return ResponseEntity.ok().body(pessoa.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
