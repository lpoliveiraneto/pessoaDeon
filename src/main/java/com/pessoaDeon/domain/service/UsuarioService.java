package com.pessoaDeon.domain.service;

import com.pessoaDeon.domain.exception.UsuarioAlreadyRegisteredException;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.repository.pessoa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario){

        if(buscaUsuarioPorEmail(usuario.getEmail()).isEmpty()){
            return usuarioRepository.save(usuario);
        }
        throw new UsuarioAlreadyRegisteredException("Usuario já consta na Base de dados");
    }

    public List<Usuario> buscaUsuarioPorEmail(String email){
        return usuarioRepository.BuscarUsuarioPorEmail(email);
    }
}
