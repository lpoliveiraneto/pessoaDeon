package com.pessoaDeon.domain.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoaDeon.domain.model.ResetSenha;
import com.pessoaDeon.domain.model.enumeration.TipoEnvio;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.repository.SenhaResetRepository;
import com.pessoaDeon.domain.repository.usuario.UsuarioRepository;
import com.pessoaDeon.domain.service.usuario.UsuarioService;

@Service
public class SenhaResetService {

	@Autowired
	private SenhaResetRepository senhaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	EnvioEmailService envioEmailService;
	
	@Transactional
	public ResetSenha findByCodigo(String codigo) {
		return senhaRepository.findByCodigo(codigo);
	}
	
	@Transactional
	public ResetSenha findByUser(Usuario usuario) {
		return senhaRepository.findByUsuario(usuario);
	}
	
//	@Transactional
//	public SenhaReset save(Usuario usuario) {
//		SenhaReset reset = new SenhaReset();
//		reset.setUsuario(usuario);
//		reset.setCodigo(gerarCodigoResetSenha());
//		reset.setDataSolicitacao(LocalDateTime.now());
//		reset.setExpiracaoCodigo(LocalDateTime.now().plusHours(2));
//		return senhaRepository.save(reset);
//	}
	
	public Boolean calculaTempoExpiracaoCodigoResetSenha(LocalDateTime dataExpiracao) {
		OffsetDateTime start = LocalDateTime.now().atOffset(ZoneOffset.UTC);
		OffsetDateTime stop = dataExpiracao.atOffset(ZoneOffset.UTC);
		long tempoDecorrido = Duration.between(start, stop).toMinutes();
		System.err.println(tempoDecorrido);
		return tempoDecorrido > 0 && tempoDecorrido <= 120; 
	}
	
	public String gerarCodigoResetSenha() {
		UUID uuid = UUID.randomUUID();
		String codigoVerificacao = uuid.toString();		
		return codigoVerificacao;
	}

//	@Transactional
//	public ResponseEntity<?> ativarConta(String codigo) {
//		var verificacaoConta = findByCodigo(codigo);
//		if(verificacaoConta != null) {
//			var time = calculaTempoExpiracaoCodigoVerificacaoConta(verificacaoConta.getExpiracaoCodigo());
//			if (time) {
//				var usuario = usuarioService.findById(verificacaoConta.getUsuario().getIdUsuario()).get();
//				if (!usuario.getContaAtiva()) {
//					usuario.setContaAtiva(true);
//					usuarioRepository.save(usuario);
//				} else {
//					return ResponseEntity.status(HttpStatus.CONFLICT).body("Conta já esta ativa!");
//				}
//				return ResponseEntity.status(HttpStatus.OK).body("Sua conta está ativa!");
//			} else {
//				return ResponseEntity.status(HttpStatus.CONFLICT).body("Tempo de verificação expirado!");
//			}
//		}
//		return ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível ativar sua conta por esse token no momento! Tente novamente mais tarde");
//	}

	
	@Transactional
	public ResponseEntity<?> enviarCodigoResetSenha(String email, String numeroDocumento, LocalDate dataNascimento) {
	    Optional<Pessoa> pessoa = pessoaService.getPessoaByNumeroDocumento(numeroDocumento);
	    if (!pessoa.isPresent()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma pessoa encontrada com o número do documento ou data de nascimento informados!");
	    }
	    if (!pessoa.get().getDataNascimento().equals(dataNascimento)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma pessoa encontrada com o número do documento ou data de nascimento informados!");
		}
	    
	    List<Usuario> listUser = usuarioService.buscaUsuarioPorEmail(email);
	    var user = listUser.get(0);
	    
	    if (user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada! Verifique os campos informados.");
	    }
	    
	    if (!user.getContaAtiva()) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conta inativa, não é possível redefinir senha!");
	    }
	    
	    ResetSenha reset = new ResetSenha();
	    reset.setCodigo(gerarCodigoResetSenha());
	    reset.setDataSolicitacao(LocalDateTime.now());
	    reset.setExpiracaoCodigo(LocalDateTime.now().plusHours(2));
	    reset.setUsuario(user);
	    senhaRepository.save(reset);
	    envioEmailService.enviarCodigoEmail(user.getEmail(), reset.getCodigo(), TipoEnvio.RS);
	    
	    return ResponseEntity.status(HttpStatus.OK).body("Redefinição de senha enviada com sucesso!");
	}

	@Transactional
	public ResponseEntity<?> resetarSenha(String codigo, String novaSenha) {
		var reset = findByCodigo(codigo);
		if (reset == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma redefinição de senha solicitada!");
		}
		var time = calculaTempoExpiracaoCodigoResetSenha(reset.getExpiracaoCodigo());
		if (!time) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Token expirado!");
		}
		
		var user = usuarioService.findById(reset.getUsuario().getIdUsuario()).get();
		if (user.getContaAtiva()) {
			user.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
			usuarioRepository.save(user);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Senha alterada com sucesso!");
	}

	@Transactional
	public ResponseEntity<?> alterarMinhaSenha(Integer idUsuario, String senhaAntiga, String novaSenha) {
		var user = usuarioService.findById(idUsuario).get();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Boolean matches = passwordEncoder.matches(senhaAntiga, user.getPassword());
		Boolean senhaIgualAntiga = passwordEncoder.matches(novaSenha, user.getPassword());
		if (!matches) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Senha anterior invalida!");
		}
		if (senhaIgualAntiga) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("A senha informada não pode ser igual à senha anterior!");
		}
		user.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
		usuarioRepository.save(user);
		return ResponseEntity.status(HttpStatus.OK).body("Senha alterada com sucesso!");
	}
	
}
