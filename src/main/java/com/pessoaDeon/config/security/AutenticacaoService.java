package com.pessoaDeon.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.repository.analista.AnalistaRepository;
import com.pessoaDeon.domain.repository.pessoa.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private AnalistaRepository analistaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email.contains(":")){
            int colonIndex = email.lastIndexOf(":");
            String userNamePart = email.substring(0, colonIndex);
            Analista funcionario = analistaRepository.findByLogin(userNamePart)
            .orElseThrow(() -> new UsernameNotFoundException("Dados Inválidos"));
            return funcionario;
        
        } else {
        
        return usuarioRepository.findByEmail(email)
        		.orElseThrow(() -> new UsernameNotFoundException("Usuario inexistente!"));
        }
    }
}
