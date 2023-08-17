package com.pessoaDeon.api.controller.seguranca;

import com.pessoaDeon.domain.model.Pessoa;
import com.pessoaDeon.domain.model.dto.seguranca.DadosAutenticacao;
import com.pessoaDeon.domain.model.dto.seguranca.DadosTokenJwt;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.config.security.TokenService;
import com.pessoaDeon.domain.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity efetuarLoginCliente(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        try{
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
            var pessoa = pessoaService.buscaPessoaEmail(dados.email());
            return ResponseEntity.ok(new DadosTokenJwt(pessoa.getUsuario().getIdUsuario().toString(),pessoa.getNome(),tokenJWT));

        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
}
