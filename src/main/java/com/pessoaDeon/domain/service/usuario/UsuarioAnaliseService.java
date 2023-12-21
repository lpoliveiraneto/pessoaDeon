package com.pessoaDeon.domain.service.usuario;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.exception.PessoaNotFoundException;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.model.usuario.UsuarioAnalise;
import com.pessoaDeon.domain.model.usuario.UsuarioAnaliseRequest;
import com.pessoaDeon.domain.repository.usuario.RespostaUsuarioAnaliseRepository;
import com.pessoaDeon.domain.repository.usuario.UsuarioAnaliseRepository;
import com.pessoaDeon.domain.service.analista.AnalistaService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsuarioAnaliseService {

	@Autowired
	private RespostaUsuarioAnaliseRepository respostaUsuarioAnaliseRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioAnaliseRepository analiseRepository;

	@Autowired
	private AnalistaService analistaService;

	/**
	 * @return salva o usuario durante a analise para ter registro
	 */
	@Transactional
	public void salvarUsuarioEmTabelaAnalise(UsuarioAnaliseRequest usuarioRequest, HttpServletRequest request,
			Status status) {
		UsuarioAnalise usuarioAnalise = new UsuarioAnalise();
		var user = usuarioService.findById(usuarioRequest.fkUsuario())
				.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

		if (user != null) {
			usuarioService.mudaStatusUsuarioEmAnalise(user, status);
		}
		usuarioAnalise.setUsuario(user);
		usuarioAnalise.setAnalista(analistaService.getAnalistaToken(request));
		usuarioAnalise.setDataEntradaAnalise(LocalDateTime.now());
		analiseRepository.save(usuarioAnalise);
	}

//	public void salvarRespostaUsuarioEmAnalise(UsuarioAnaliseRequest usuarioRequest) {
//		
//        var analise = analiseRepository.findById(usuarioRequest.fkUsuarioAnalise())
	// orElseThrow(() -> new RuntimeException("Não existe usuario para analise com
	// esse id"));
//        
//        var respostaAnaliseUsuario = respostaUsuarioAnaliseRepository.findById(usuarioRequest.fkRespostaUsuario())
//        		.orElseThrow(() -> new RespostaAnaliseNotFoundException("Não existe resposta para esse id"));
//		
//        if (analise.isStatus()) {
//			return;
//		}
//		analise.setStatus(true);
//		analise.setDataAnalise(LocalDateTime.now());
//        analise.setRespostaAnalise(respostaAnaliseUsuario);
//
//        Status novoStatus = (analise.getRespostaAnalise().getIdRespostaAnalise() != USUARIO_VALIDADO_COM_SUCESSO) ? Status.IV : Status.VA;
//        usuarioService.mudaStatusUsuarioEmAnalise(analise.getUsuario(), novoStatus);
//		analiseRepository.saveAndFlush(analise);
//	}

	public void aprovarUsuarioEmAnalise(UsuarioAnaliseRequest usuarioRequest, Integer id) {

		var resposta = respostaUsuarioAnaliseRepository.findById(id);
		var analise = analiseRepository.findByUsuario_IdUsuario(usuarioRequest.fkUsuario())
				.orElseThrow(() -> new RuntimeException("Não existe usuario para analise com esse id"));
		Optional<Usuario> usuario = usuarioService.findById(usuarioRequest.fkUsuario());

		try {
			if (usuario.get().getStatus().equals(Status.EA)) {
				analise.setStatus(true);
				analise.setDataAnalise(LocalDateTime.now());
				analise.setRespostaAnalise(resposta.get());

				Status novoStatus = (analise.getRespostaAnalise().getIdRespostaAnalise() != id) ? Status.IV : Status.VA;
				usuarioService.mudaStatusUsuarioEmAnalise(analise.getUsuario(), novoStatus);
				analiseRepository.saveAndFlush(analise);
			}
		} catch (RuntimeException e) {
			throw new PessoaNotFoundException("Operação indisponivel com esse status");
		}

	}

	public void recusarUsuarioEmAnalise(UsuarioAnaliseRequest usuarioRequest, Integer id, HttpServletRequest request) {
		var analise = analiseRepository.findByUsuario_IdUsuario(usuarioRequest.fkUsuario())
				.orElseThrow(() -> new RuntimeException("Não existe usuario para analise com esse id"));
		var respostaRecusar = respostaUsuarioAnaliseRepository.findById(id);

		Optional<Usuario> usuario = usuarioService.findById(usuarioRequest.fkUsuario());
		try {
			if (usuario.get().getStatus().equals(Status.EA)) {
				analise.setStatus(false);
				analise.setAnalista(analistaService.getAnalistaToken(request));
				analise.setDataAnalise(LocalDateTime.now());
				analise.setRespostaAnalise(respostaRecusar.get());

				Status novoStatus = (analise.getRespostaAnalise().getIdRespostaAnalise() != id) ? Status.VA : Status.IV;
				usuarioService.mudaStatusUsuarioEmAnalise(analise.getUsuario(), novoStatus);
				analiseRepository.saveAndFlush(analise);
			}

		} catch (RuntimeException e) {
			throw new PessoaNotFoundException("Operação indisponivel com esse status");
		}
	}
}
