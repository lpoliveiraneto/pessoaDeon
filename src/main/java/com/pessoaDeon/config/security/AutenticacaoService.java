package com.pessoaDeon.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.repository.analista.AnalistaRepository;
import com.pessoaDeon.domain.repository.usuario.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private AnalistaRepository analistaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.contains("@")){
            return usuarioRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario inexistente!"));
        } else {
            return analistaRepository.findByLogin(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Dados Inválidos"));
    }}
}
