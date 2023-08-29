package com.pessoaDeon.domain.service;

import com.pessoaDeon.domain.exception.UsuarioAlreadyRegisteredException;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.repository.pessoa.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvarUsuario(Usuario usuario){

        if(buscaUsuarioPorEmail(usuario.getEmail()).isEmpty()){
            return usuarioRepository.save(usuario);
        }
        throw new UsuarioAlreadyRegisteredException("Usuario já consta na Base de dados");
    }

    public List<Usuario> buscaUsuarioPorEmail(String email){
        return usuarioRepository.BuscarUsuarioPorEmail(email);
    }

	public Optional<Usuario> findById(Long idUsuario) {
		return usuarioRepository.findById(idUsuario);
	}
	
	public Optional<Usuario> findByPessoa(Pessoa pessoa) {
		return usuarioRepository.findByPessoa(pessoa);
	}
}
