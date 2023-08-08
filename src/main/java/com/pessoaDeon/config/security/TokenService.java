package com.pessoaDeon.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pessoaDeon.config.validacao.TratadorDeErros;
import com.pessoaDeon.domain.model.security.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try{
            var algoritmo = Algorithm.HMAC256(secret);
            Map informacaoToken = new HashMap<String, Object>();
            informacaoToken.put("id", usuario.getIdUsuario());
            informacaoToken.put("email", usuario.getEmail());

            return JWT.create()
                    .withIssuer("Api NovaDeon")
                    .withPayload(informacaoToken)
                    //.withKeyId(usuario.getIdUsuario().toString())
                    //.withSubject(usuario.getEmail())
                    //.withSubject(usuario.getEmail())
                    //.withSubject(usuario.getIdUsuario().toString())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        }catch (JWTCreationException exception){
            throw  new RuntimeException("Erro ao Gerar Token", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("Api NovaDeon")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido");
        }
    }

    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
