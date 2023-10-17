package com.pessoaDeon.domain.model.dto.seguranca;

import java.util.List;

import com.pessoaDeon.domain.model.security.Perfil;

public record DadosTokenAnalistaJwt(String id_analista, String nome , String token, List<Perfil> perfis, Integer fk_pessoa) {
    
}
