package com.pessoaDeon.api.controller.endereco;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.domain.model.endereco.Endereco;
import com.pessoaDeon.domain.service.EnderecoService;

@RestController
@RequestMapping("api/v1/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;
    
    @GetMapping("/listEnderecos") 	
    public List<Endereco> listarEnderecos(){
        return enderecoService.listarEnderecos();
    }
    
    @ResponseBody
    @GetMapping("/{idEndereco}")
    public ResponseEntity<?> buscarEnderecoPorId(@PathVariable(value = "idEndereco") Integer idEndereco){
    	Optional<Endereco> endereco = enderecoService.getById(idEndereco);
    	return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }

//    @ResponseBody
//    @PostMapping("/salvarEndereco")
//    public ResponseEntity<?> salvarEndereco(@RequestBody EnderecoDtoInput endereco, Integer idPessoa) {
//    	
//    	Endereco enderecoNovo = enderecoService.salvarEnderecoPessoa(endereco, idPessoa);
//    	return ResponseEntity.status(HttpStatus.OK).body(enderecoNovo);
//    }
}
