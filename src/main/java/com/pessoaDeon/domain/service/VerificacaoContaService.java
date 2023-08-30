package com.pessoaDeon.domain.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

import com.pessoaDeon.domain.repository.pessoa.UsuarioRepository;

import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.VerificacaoConta;
import com.pessoaDeon.domain.model.listas.TipoDocumento;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.repository.VerificacaoContaRepository;

@Service
public class VerificacaoContaService {

	@Autowired
	private VerificacaoContaRepository contaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	EnvioEmailService envioEmailService;
	
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

	@Transactional
	public ResponseEntity<?> reenviarCodigoVerificacao(String email, String numeroDocumento) {
		Optional<Pessoa> pessoa = pessoaService.getPessoaByNumeroDocumento(numeroDocumento);
		Optional<Usuario> user = usuarioService.findByPessoa(pessoa.get());
		VerificacaoConta conta = findByUser(user.get());
		if (user.isPresent() && user.get().getEmail().equalsIgnoreCase(email) 
				&& pessoa.isPresent() && conta != null) {
			if (!user.get().getContaAtiva()) {
				conta.setCodigo(gerarCodigoVerificacaoConta());
				conta.setExpiracaoCodigo(LocalDateTime.now().plusHours(2));
				contaRepository.save(conta);
				envioEmailService.enviarCodigoEmail(user.get().getEmail(), conta.getCodigo());
				return ResponseEntity.status(HttpStatus.OK).body("Codigo reenviado com sucesso!");
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Sua conta já foi verificada!");
			}
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Conta não encontrada! Verifique os campos informados.");
	}
	
}
