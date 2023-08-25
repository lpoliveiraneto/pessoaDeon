package com.pessoaDeon.domain.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.VerificacaoConta;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.repository.UsuarioRepository;
import com.pessoaDeon.domain.repository.VerificacaoContaRepository;

@Service
public class VerificacaoContaService {

	@Autowired
	private VerificacaoContaRepository contaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    UsuarioRepository usuarioRepository;
	
	@Transactional
	public VerificacaoConta findByCodigo(String codigo) {
		return contaRepository.findByCodigo(codigo);
	}
	
	@Transactional
	public VerificacaoConta findByUser(Usuario usuario) {
		return contaRepository.findByUsuario(usuario);
	}
	
	@Transactional
	public VerificacaoConta save(Usuario usuario) {
		VerificacaoConta conta = new VerificacaoConta();
		conta.setUsuario(usuario);
		conta.setCodigo(gerarCodigoVerificacaoConta());
		conta.setDataSolicitacao(LocalDateTime.now());
		conta.setExpiracaoCodigo(LocalDateTime.now().plusHours(2));
		return contaRepository.save(conta);
	}
	
	public Boolean calculaTempoExpiracaoCodigoVerificacaoConta(LocalDateTime dataExpiracao) {
		OffsetDateTime start = LocalDateTime.now().atOffset(ZoneOffset.UTC);
		OffsetDateTime stop = dataExpiracao.atOffset(ZoneOffset.UTC);
		long tempoDecorrido = Duration.between(start, stop).toMinutes();
		System.err.println(tempoDecorrido);
		return tempoDecorrido > 0 && tempoDecorrido <= 120; 
	}
	
	public String gerarCodigoVerificacaoConta() {
		UUID uuid = UUID.randomUUID();
		String codigoVerificacao = uuid.toString();		
		return codigoVerificacao;
	}

	@Transactional
	public ResponseEntity<?> ativarConta(String codigo) {
		var verificacaoConta = findByCodigo(codigo);
		if(verificacaoConta != null) {
			var time = calculaTempoExpiracaoCodigoVerificacaoConta(verificacaoConta.getExpiracaoCodigo());
			if (time) {
				var usuario = usuarioService.findById(verificacaoConta.getUsuario().getIdUsuario()).get();
				if (!usuario.getContaAtiva()) {
					usuario.setContaAtiva(true);
					usuarioRepository.save(usuario);
				} else {
					return ResponseEntity.status(HttpStatus.CONFLICT).body("Conta já esta ativa!");
				}
				return ResponseEntity.status(HttpStatus.OK).body("Sua conta está ativa!");
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Tempo de verificação expirado!");
			}
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível ativar sua conta por esse token no momento! Tente novamente mais tarde");
	}
	
}
