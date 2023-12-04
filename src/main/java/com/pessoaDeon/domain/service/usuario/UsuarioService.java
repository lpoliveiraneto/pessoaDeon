package com.pessoaDeon.domain.service.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.exception.UsuarioAlreadyRegisteredException;
import com.pessoaDeon.domain.model.dto.UsuariosPendentesResponseDto;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.repository.usuario.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {

		if (buscaUsuarioPorEmail(usuario.getEmail()).isEmpty()) {
			return usuarioRepository.save(usuario);
		}
		throw new UsuarioAlreadyRegisteredException("Usuario já consta na Base de dados");
	}

	public List<Usuario> buscaUsuarioPorEmail(String email) {
		return usuarioRepository.BuscarUsuarioPorEmail(email);
	}

	public Optional<Usuario> findById(Integer idUsuario) {
		return usuarioRepository.findById(idUsuario);
	}

	public Optional<Usuario> findByPessoa(Pessoa pessoa) {
		return usuarioRepository.findByPessoa(pessoa);
	}

	public List<Usuario> findByStatusAndContaAtivaIsTrue(Status pe) {
		return usuarioRepository.findByStatusAndContaAtivaIsTrue(pe);
	}

	public Usuario getUsuarioStatusAndContaAtiva(Integer idUsuario, Status va) {
		return usuarioRepository.findByIdUsuarioAndStatusAndContaAtivaIsTrue(idUsuario, va);
	}

	public Page<UsuariosPendentesResponseDto> getUsuariosAnalise(Pageable pageable) {
		List<Usuario> usuarios = findByStatusAndContaAtivaIsTrue(Status.PE);
		List<UsuariosPendentesResponseDto> usuariosPendentesDto = new ArrayList<>();
		usuarios.forEach(usuario -> {
			UsuariosPendentesResponseDto usuarioDto = new UsuariosPendentesResponseDto();
			usuarioDto.setCpf(usuario.getPessoa().getNumeroDocumento());
			usuarioDto.setDataCadastro(usuario.getDataCadastro());
			usuarioDto.setEmail(usuario.getEmail());
			usuarioDto.setIdPessoa(usuario.getPessoa().getId());
			usuarioDto.setIdUsuario(usuario.getIdUsuario());
			usuarioDto.setNome(usuario.getPessoa().getNome());
			usuariosPendentesDto.add(usuarioDto);
		});
		return new PageImpl<>(usuariosPendentesDto, pageable, usuariosPendentesDto.size());
	}
	
	public void mudaStatusUsuarioEmAnalise(Usuario usuario, Status status) {
		if (usuario != null) {
			usuario.setStatus(status);
			usuarioRepository.saveAndFlush(usuario);
		}
	}
	
}