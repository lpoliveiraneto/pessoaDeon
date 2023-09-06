package com.pessoaDeon.api.controller.seguranca;

import java.sql.Date;
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
import com.pessoaDeon.domain.model.dto.seguranca.DadosAlterarSenhaDTO;
import com.pessoaDeon.domain.model.dto.seguranca.DadosAutenticacao;
import com.pessoaDeon.domain.model.dto.seguranca.DadosTokenJwt;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.service.PessoaService;
import com.pessoaDeon.domain.service.SenhaResetService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private PessoaService pessoaService;
    
    @Autowired
    private SenhaResetService senhaResetService;

    @PostMapping("login")
    public ResponseEntity<?> efetuarLoginCliente(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        try{
            var authentication = manager.authenticate(authenticationToken);
            Usuario user = (Usuario) authentication.getPrincipal();
            if (user.getContaAtiva()) {
            	var tokenJWT = tokenService.gerarToken(user);
            	var pessoa = pessoaService.buscaPessoaEmail(dados.email());
                String primeiroNomeUltimo = primeiroEUltimonome(pessoa.getNome());
            	return ResponseEntity.ok(new DadosTokenJwt(pessoa.getUsuario().getIdUsuario().toString(),primeiroNomeUltimo,tokenJWT, pessoa.getUsuario().getPerfis()));
            } else {
            	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sua conta ainda não esta ativa. Por favor, verifique sua caixa de e-mail.");
            }

        }catch (AuthenticationException e){
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
    
    @GetMapping("/esqueciMinhaSenha")
    public ResponseEntity<?> EsqueciMinhaSenha(@RequestParam(name = "email", required = true) String email, 
    		@RequestParam(name = "dataNascimento", required = true) LocalDate dataNascimento,
    		@RequestParam(name = "cpf", required = true) String cpf){
    	return senhaResetService.enviarCodigoResetSenha(email, cpf, dataNascimento);	
    }
    
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
