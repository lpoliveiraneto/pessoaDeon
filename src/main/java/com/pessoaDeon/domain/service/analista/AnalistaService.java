package com.pessoaDeon.domain.service.analista;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.pessoaDeon.domain.exception.AnalistaNotFoundException;
import com.pessoaDeon.domain.exception.EnviaBoSigmaException;
import com.pessoaDeon.domain.exception.PessoaNotFoundException;
import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.model.dto.analista.AnalistaRequest;
import com.pessoaDeon.domain.model.dto.analista.AnalistaResponseDto;
import com.pessoaDeon.domain.model.dto.analista.AnalistaReturnDto;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.security.Usuario;
import com.pessoaDeon.domain.repository.analista.AnalistaRepository;
import com.pessoaDeon.domain.repository.listas.perfil.PerfilRepository;
import com.pessoaDeon.domain.repository.pessoa.UsuarioRepository;
import com.pessoaDeon.domain.service.PessoaService;
import com.pessoaDeon.domain.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AnalistaService {

	@Autowired
	private AnalistaRepository analistaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Value("${url.api-integracao}")
	private String URL;
	
	public Optional<Analista> findById(Integer id) {
		return analistaRepository.findById(id);
	}

	/**
	 * 
	 * @return metodo para salvar analista
	 * */
	@Transactional
	public Analista salvarAnalista(AnalistaRequest analistaRequest, HttpServletRequest http, AnalistaResponseDto analistaSigma) {

		if(!verificaFuncionarioSigmaAtivo(analistaSigma)) {
			throw new PessoaNotFoundException("Funcionario não está ativo no SIGMA, cadastre-o!");
		}
		
		if(!pessoaService.existsPessoaNumeroDocumento(analistaRequest.cpf())){
			throw new PessoaNotFoundException("Pessoa não consta no banco de dados, cadastre-o!");
		}
		Pessoa pessoa = pessoaService.buscaPessoaCpf(analistaRequest.cpf());
		Usuario usuario = usuarioRepository.findByPessoa(pessoa).get();
		
		if(usuarioService.getUsuarioStatusAndContaAtiva(usuario.getIdUsuario(), Status.VA) == null ) {
			throw new PessoaNotFoundException("Usuario não está com conta ativa!");
		}
		
		Analista analista = analistaRequestToAnalista(analistaRequest,analistaSigma, pessoa);
		return analistaRepository.save(analista);
	}
	
	/**
	 * @param cpf
	 * @return verifica pelo cpf se o funcionario esta no banco do sigma 
	 * e se consta na tabela pessoa da DEON
	 * 
	 * */
	public AnalistaReturnDto verificaAnalista(String cpf, HttpServletRequest http) {
		var analistaSigma = buscaFuncionarioSigma(cpf, http);

		AnalistaReturnDto analistaReturnDto = new AnalistaReturnDto(analistaSigma, null, null);
		
		if(!verificaFuncionarioSigmaAtivo(analistaSigma)) {
			throw new AnalistaNotFoundException("Funcionario não está ativo no SIGMA, cadastre-o!");
		}
		
		if(!pessoaService.existsPessoaNumeroDocumento(cpf)){
			analistaReturnDto.setMessage("Pessoa não consta no banco de dados, cadastre-o!");
		}else {
			analistaReturnDto.setPessoa(verificaPessoaPeloNumeroDocumento(cpf));
		}
		return analistaReturnDto;
	}

	@Transactional
	private Analista analistaRequestToAnalista(AnalistaRequest analistaRequest, AnalistaResponseDto analistaSigma, Pessoa pessoa) {
		final long PERFIL_ANALISTA = 3;
		Analista analista = new Analista();
		
		var verifica = analistaRepository.existsByLogin(analistaRequest.cpf());
		if (verifica.equals(true)) {
			throw new AnalistaNotFoundException("Analista já está cadastrado!");
		}
		analista.setLogin(analistaRequest.cpf());
		analista.setMatricula(analistaRequest.matricula());
		analista.setNome(analistaRequest.nome());
		analista.setStatus(true);
		analista.setCargo(analistaRequest.cargo());
		analista.setFuncao(analistaRequest.funcao());
		analista.setSenha(new BCryptPasswordEncoder().encode(analistaRequest.senha()));
		analista.setAssinatura(analistaRequest.assinatura());
		analista.setData_alteracao(LocalDateTime.now());
		analista.setData_cadastro(LocalDateTime.now());
		analista.adicionarPerfil(perfilRepository.findById(PERFIL_ANALISTA).get());
		analista.setFuncionarioSigma(analistaSigma.getIdFuncionario());
		analista.setPessoa(pessoa);
		return analista;
	}

	public Pessoa verificaPessoaPeloNumeroDocumento(String numeroDocumento){
		return pessoaService.buscaPessoaCpf(numeroDocumento);
	}
	
	public AnalistaResponseDto buscaFuncionarioSigma(String cpf, HttpServletRequest http) {
		var tokenJWT = recuperarToken(http);
		if (tokenJWT != null) {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				headers.set("Authorization", tokenJWT);
				RestTemplate template = new RestTemplate();
				HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
				ResponseEntity<AnalistaResponseDto> response = template.exchange(URL + "buscaFuncionario?cpf=" + cpf,  HttpMethod.GET, requestEntity, AnalistaResponseDto.class);
				return response.getStatusCode().value() == 200 ? response.getBody() : null;					
			} catch (Exception e) {
				throw new EnviaBoSigmaException(e.getMessage());
			}
		}
 		return null;
	}
	
	private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ","");
        }
        return null;
    }
	
	private boolean verificaFuncionarioSigmaAtivo(AnalistaResponseDto analistaSigma) {
		return analistaSigma.getAtivo() == true;
	}

}
