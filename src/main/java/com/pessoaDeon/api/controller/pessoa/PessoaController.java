package com.pessoaDeon.api.controller.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.service.PessoaService;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listar(){
        return pessoaService.listarPessoas();
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<?> getPessoa(@PathVariable Integer idPessoa){
        Optional<Pessoa> pessoa = pessoaService.buscarPessoa(idPessoa);

        if(pessoa.isPresent()){
            Pessoa pesquisaPessoa = pessoa.get();
            return ResponseEntity.ok().body(pesquisaPessoa);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
//
//    @ResponseBody
//    @PostMapping("/salvar")
//    public ResponseEntity<PessoaDtoOutput> cadastrarPessoa(@RequestBody PessoaDtoInput pessoaDto){
//        PessoaDtoOutput pessoa = pessoaService.salvarPessoa(pessoaDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
//    }
}
