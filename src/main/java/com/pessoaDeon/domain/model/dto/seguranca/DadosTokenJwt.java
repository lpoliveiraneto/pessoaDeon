package com.pessoaDeon.domain.model.dto.seguranca;

import com.pessoaDeon.domain.model.security.Perfil;

import java.util.List;

public record DadosTokenJwt(String id_usuario, String nome , String token, List<Perfil> perfis) {
}
