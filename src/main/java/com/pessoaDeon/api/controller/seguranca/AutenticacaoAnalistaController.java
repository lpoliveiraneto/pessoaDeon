package com.pessoaDeon.api.controller.seguranca;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pessoaDeon.config.security.TokenService;
import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.model.dto.seguranca.DadosAlterarSenhaDTO;
import com.pessoaDeon.domain.model.dto.seguranca.DadosAutenticacaoAnalista;
import com.pessoaDeon.domain.model.dto.seguranca.DadosTokenAnalistaJwt;
import com.pessoaDeon.domain.service.PessoaService;
import com.pessoaDeon.domain.service.SenhaResetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/autenticacaoAnalista/")
public class AutenticacaoAnalistaController {
    
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private PessoaService pessoaService;
    
    @Autowired
    private SenhaResetService senhaResetService;

    @PostMapping("login")
    public ResponseEntity<?> efetuarLoginCliente(@RequestBody @Valid DadosAutenticacaoAnalista dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.cpf() , dados.senha());
        try{
            var authentication = manager.authenticate(authenticationToken);
            Analista user = (Analista) authentication.getPrincipal();
            var tokenJWT = tokenService.gerarTokenAnalista(user);
            var pessoa = pessoaService.buscaPessoaCpf(dados.cpf());
            String primeiroNomeUltimo = primeiroEUltimonome(pessoa.getNome());
            return ResponseEntity.ok(new DadosTokenAnalistaJwt(user.getIdAnalista().toString(),primeiroNomeUltimo,tokenJWT, user.getPerfis(), pessoa.getId()));

        }catch (AuthenticationException e){
//        	String msg = "Usuario ou senha invalidos!";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

    private String primeiroEUltimonome(String nome){
        String[] nomeCurto = nome.split(" ");
        if(nomeCurto.length == 1){
            return nomeCurto[0];
        }else{
            return nomeCurto[0]+" "+nomeCurto[nomeCurto.length-1];
        }
    }
    
//    @GetMapping("/esqueciMinhaSenha")
//    public ResponseEntity<?> EsqueciMinhaSenha(@RequestParam(name = "email", required = true) String email, 
//    		@RequestParam(name = "dataNascimento", required = true) LocalDate dataNascimento,
//    		@RequestParam(name = "cpf", required = true) String cpf){
//    	return senhaResetService.enviarCodigoResetSenha(email, cpf, dataNascimento);	
//    }
    
    @PostMapping("/resetSenha")
    public ResponseEntity<?> resetSenha(@RequestParam(name = "codigo") String codigo,
    		@RequestParam(name = "novaSenha", required = true) String novaSenha){
    	return senhaResetService.resetarSenha(codigo, novaSenha);
    }

    @PostMapping("/alterarMinhaSenha")
    public ResponseEntity<?> alterarMinhaSenha(@RequestBody @Valid DadosAlterarSenhaDTO dados){
    	return senhaResetService.alterarMinhaSenha(dados.getIdUsuario(), dados.getSenhaAntiga(), dados.getSenhaNova());
    }
}
