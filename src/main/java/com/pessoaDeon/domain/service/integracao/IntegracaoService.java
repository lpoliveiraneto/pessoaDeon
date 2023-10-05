package com.pessoaDeon.domain.service.integracao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pessoaDeon.domain.exception.EnviaBoSigmaException;
import com.pessoaDeon.domain.model.DelegadoResponsavel;
import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.bo.EnderecoLocalFato;
import com.pessoaDeon.domain.model.dto.integracao.BoRequestDto;
import com.pessoaDeon.domain.model.dto.integracao.BoResponseDto;
import com.pessoaDeon.domain.model.dto.integracao.ContatoRequestDto;
import com.pessoaDeon.domain.model.dto.integracao.EnderecoRequestDto;
import com.pessoaDeon.domain.model.dto.integracao.EnvolvidoRequestDto;
import com.pessoaDeon.domain.model.dto.integracao.EquipeRequestDto;
import com.pessoaDeon.domain.model.dto.integracao.RequestDto;
import com.pessoaDeon.domain.model.endereco.Endereco;
import com.pessoaDeon.domain.model.endereco.Logradouro;
import com.pessoaDeon.domain.model.envolvido.EnderecoEnvolvido;
import com.pessoaDeon.domain.model.envolvido.Envolvido;
import com.pessoaDeon.domain.model.envolvido.Envolvimento;
import com.pessoaDeon.domain.model.listas.UnidadeDestino;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.repository.bo.BoRepository;
import com.pessoaDeon.domain.service.ContatoService;
import com.pessoaDeon.domain.service.DelegadoResponsavelService;
import com.pessoaDeon.domain.service.EnderecoService;
import com.pessoaDeon.domain.service.UnidadeDestinoService;
import com.pessoaDeon.domain.service.analista.AnalistaService;
import com.pessoaDeon.domain.service.bo.BoService;
import com.pessoaDeon.domain.service.bo.EnderecoLocalFatoService;

@Service
public class IntegracaoService {
	
	@Autowired
	private EnderecoLocalFatoService elfService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private UnidadeDestinoService unidadeDestinoService;
	
	@Autowired
	private AnalistaService analistaService;
	
	@Autowired
	private DelegadoResponsavelService delegadoService;
	
	@Autowired
	private ContatoService contatoService;
	
	@Autowired
	private BoService boService;
	
	@Value("${url.api-integracao}")
	private String URL;
	
//	monta o RequestDTO que vai ser enviado p/ API de integracao com SIGMA
	public RequestDto dadosBoToDto(BoDeon bo, List<Envolvimento> envolvimentos, Integer idAnalista) {
		RequestDto request = new RequestDto();
		
		var listaEnvolvidos = listaEnvolvidos(envolvimentos);
		var listaEquipeBO = listaEquipeBOtoDto(idAnalista);
		var listaNatureza = listaFkNaturezaBo(bo);
		var boDto = boToDto(bo);
		
//		se a verificacao retorna false, as listas não estão vazias e o método continua
		if (verificaListasNulasVazia(listaEnvolvidos, listaEquipeBO, listaNatureza, boDto)) {
	        return null;
	    }
		
		request.setBoDto(boDto);
		request.setListaEnvolvidos(listaEnvolvidos);
		request.setListaEquipe(listaEquipeBO);
		request.setListaFkNatureza(listaNatureza);
		return request;
	}

//	verifica se alguma das lista do DTO está nula ou vazia, em caso positivo retorna TRUE, caso não retorna FALSE
	private Boolean verificaListasNulasVazia(List<EnvolvidoRequestDto> listaEnvolvidos, 
            List<EquipeRequestDto> listaEquipeBO,
            List<Integer> listaNatureza, 
            BoRequestDto boDto) {
		return listaEnvolvidos.isEmpty() || listaEquipeBO.isEmpty() || listaNatureza.isEmpty() || boDto == null;
	}
	
//	monta o BoRequestDto com base no BO da DEON
	private BoRequestDto boToDto(BoDeon bo) {
//		seta a unidade de destino, no caso (Delegacia Online)
		UnidadeDestino unidade = unidadeDestinoService.getUnidadeDestino();
		
		if (unidade != null) {
//			seta o endereco do local do fato do BO pelo idBo
			EnderecoLocalFato endLocal = elfService.findByIdBo(bo.getIdBo());
			BoRequestDto boDto = new BoRequestDto();
			boDto.setBairro(endLocal.getBairro().getDescricao());
			boDto.setCep(endLocal.getCep());
			boDto.setCidade(endLocal.getCidade().getDescricao());
			boDto.setComplemento(endLocal.getComplemento());
			boDto.setDataFato(bo.getDataFato());
			boDto.setDataRegistro(bo.getDataRegistro());
			boDto.setDataRegistroRascunho(bo.getDataRegistro());
			boDto.setFkBairro(endLocal.getBairro().getIdBairro());
			boDto.setFkCidade(endLocal.getCidade().getIdCidade());
			boDto.setFkEstado(endLocal.getEstado().getIdEstado());
			boDto.setFkPais(1);
			boDto.setHoraFato(localTimeToDate(bo.getHoraFato()));
			boDto.setIdBoDeon(bo.getIdBo());
			boDto.setLatLong(null);
			boDto.setLogradouro(endLocal.getLogradouro());
			boDto.setNumeroLocal(endLocal.getNumeroLocal().toString());
			boDto.setPais("Brasil");
			boDto.setReferencia(endLocal.getReferencia());
			boDto.setRelato(bo.getRelatoEditado() != null && bo.getRelatoEditado() != "" ? bo.getRelatoEditado() : bo.getRelato());
			boDto.setTipoLocal(endLocal.getTipoLocal().getValor());
			boDto.setUf(endLocal.getEstado().getUf());
			boDto.setUnidade(unidade.getFkUnidadeSigma());
			return boDto;
		}
		return null;
	}
	
//	converte a hora do fato de LocalTime do BO da DEON p/ Date, como esperado pelo SIGMA
	private Date localTimeToDate(LocalTime horaFato) {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime combinedDateTime = zonedDateTime.with(horaFato);
        Date convertToDate = Date.from(combinedDateTime.toInstant());
        return convertToDate;
	}

//	monta uma lista de EnvolvidosDto com base nos envolvimentos do BO da DEON
	private List<EnvolvidoRequestDto> listaEnvolvidos(List<Envolvimento> envolvimentos) {
		List<EnvolvidoRequestDto> listaEnvolvidosDto = new ArrayList<>();
		envolvimentos.forEach(env -> {
			EnvolvidoRequestDto envolvidoDto = new EnvolvidoRequestDto();
			Envolvido envolvido = env.getEnvolvido();
			Pessoa pessoa = env.getEnvolvido().getPessoa() != null ? env.getEnvolvido().getPessoa() : null; 
			if (envolvido.getPessoa() == null) {
				envolvidoDto = envolvidoToDto(envolvido, env.getTipoParticipacao().getValor());
			}
			if (pessoa != null) {
				envolvidoDto = pessoaToEnvolvidoDto(pessoa, env.getTipoParticipacao().getValor());
			}
			listaEnvolvidosDto.add(envolvidoDto);
		});
		return listaEnvolvidosDto;
	}
	
//	monta o DTO de endereco do envolvido caso nao seja o comunicante/usuario
	private EnderecoRequestDto enderecoEnvolvidoToDto(EnderecoEnvolvido endereco) {
		if (endereco != null) {
			EnderecoRequestDto enderecoDto = new EnderecoRequestDto();
			enderecoDto.setBairro(endereco.getBairro() != null ? endereco.getBairro().getDescricao() : null);
			enderecoDto.setCep(endereco.getCep());
			enderecoDto.setCidadeEndereco(endereco.getCidade() != null ? endereco.getCidade().getDescricao() : null);
			enderecoDto.setComplemento(endereco.getComplemento());
			enderecoDto.setEstadoEndereco(endereco.getEstado() != null ? endereco.getEstado().getDescricao() : null);
			enderecoDto.setIdBairro(endereco.getBairro() != null ? endereco.getBairro().getIdBairro() : null);
			enderecoDto.setIdCidadeEndereco(endereco.getCidade() != null ? endereco.getCidade().getIdCidade() : null);
			enderecoDto.setIdEstadoEndereco(endereco.getEstado() != null ? endereco.getEstado().getIdEstado() : null);
			enderecoDto.setLogradouro(endereco.getLogradouro());
			enderecoDto.setNumero(endereco.getNumeroLocal());
			enderecoDto.setPaisEndereco(null);
			enderecoDto.setReferencia(endereco.getReferencia());
			enderecoDto.setUfEndereco(endereco.getEstado() != null ? endereco.getEstado().getUf() : null);
			return enderecoDto;
		}
		return null;
	}
	
//	monta o DTO de endereco da pessoa, no caso COMUNICANTE
	private EnderecoRequestDto enderecoPessoaToDto(Endereco endereco) {
		if (endereco != null) {
			Logradouro logradouro = endereco.getLogradouro();
			EnderecoRequestDto enderecoDto = new EnderecoRequestDto();
			enderecoDto.setBairro(logradouro.getBairro() != null ? logradouro.getBairro().getDescricao() : null);
			enderecoDto.setCep(logradouro.getCep());
			enderecoDto.setCidadeEndereco(logradouro.getCidade() != null ? logradouro.getCidade().getDescricao() : null);
			enderecoDto.setComplemento(endereco.getComplemento());
			enderecoDto.setEstadoEndereco(logradouro.getEstado() != null ? logradouro.getEstado().getDescricao() : null);
			enderecoDto.setIdBairro(logradouro.getBairro() != null ? logradouro.getBairro().getIdBairro() : null);
			enderecoDto.setIdCidadeEndereco(logradouro.getCidade() != null ? logradouro.getCidade().getIdCidade() : null);
			enderecoDto.setIdEstadoEndereco(logradouro.getEstado() != null ? logradouro.getEstado().getIdEstado() : null);
			enderecoDto.setLogradouro(endereco.getLogradouro() != null ? endereco.getLogradouro().getLogradouro() : null);
			enderecoDto.setNumero(endereco.getNumero());
			enderecoDto.setPaisEndereco(null);
			enderecoDto.setReferencia(endereco.getReferencia());
			enderecoDto.setUfEndereco(logradouro.getEstado() != null ? logradouro.getEstado().getUf() : null);
			return enderecoDto;
		}
		return null;
	}
	
//	monta o DTO de envolvido caso nao seja o comunicante
	private EnvolvidoRequestDto envolvidoToDto(Envolvido envolvido, String tipoParticipacao) {
		EnvolvidoRequestDto envDto = new EnvolvidoRequestDto();
		EnderecoEnvolvido enderecoEnvolvido = envolvido.getEnderecoEnvolvido() != null ? envolvido.getEnderecoEnvolvido() : null;
		if (enderecoEnvolvido != null) {
			envDto.setApelido(envolvido.getAlcunha());
			envDto.setCidade(envolvido.getCidadeNaturalidade() != null ? envolvido.getCidadeNaturalidade().getIdCidade() : null);
			envDto.setContato(contatoEnvolvidoToDto(envolvido));
			envDto.setCpf(envolvido.getNumeroDocumento());
			envDto.setDataNascimento(envolvido.getDataNascimento() != null ? localDateToDate(envolvido.getDataNascimento()) : null);
			envDto.setDeficiencia(envolvido.getDeficiencia() != null ? envolvido.getDeficiencia().getValor() : null);
			envDto.setDesconhecido(tipoParticipacao.equals("II") ? true : false);
			envDto.setEndereco(enderecoEnvolvidoToDto(enderecoEnvolvido));
			envDto.setEscolaridade(envolvido.getEscolaridade() != null ? envolvido.getEscolaridade().getValor() : null);
			envDto.setEstado(envolvido.getEstadoNaturalidade() != null ? envolvido.getEstadoNaturalidade().getIdEstado() : null);
			envDto.setEstadoCivil(envolvido.getEstadoCivil() != null ? envolvido.getEstadoCivil().getValor() : null);
			envDto.setIdentidadeGenero(envolvido.getIdentidadeGenero() != null ? envolvido.getIdentidadeGenero().getValor() : null);
			envDto.setNome(envolvido.getNome());
			envDto.setNomeMae(envolvido.getNomeMae());
			envDto.setNomePai(envolvido.getNomePai());
			envDto.setNomeSocial(envolvido.getNomeSocial());
			envDto.setOrientacaoSexual(envolvido.getOrientacaoSexual() != null ? envolvido.getOrientacaoSexual().getValor() : null);				
			envDto.setPais(envolvido.getPais() != null ? envolvido.getPais().getIdPais() : null);
			envDto.setProfissao(envolvido.getProfissao() != null ? envolvido.getProfissao().getIdProfissao() : null);
			envDto.setRacaCor(envolvido.getCorPele() != null ? envolvido.getCorPele().getValor() : null);
			envDto.setSexo(envolvido.getSexo() != null ? envolvido.getSexo().getValor() : null);
			envDto.setTipoParticipacao(tipoParticipacao);				
			envDto.setUsarNomeSocial(false);
		}
		return envDto;
	}
	
//	monta o DTO de envolvido p/ pessoa COMUNICANTE
	private EnvolvidoRequestDto pessoaToEnvolvidoDto(Pessoa pessoa, String tipoParticipacao) {
		EnvolvidoRequestDto envDto = new EnvolvidoRequestDto();
		Endereco endereco = enderecoService.getEnderecoByIdPessoa(pessoa.getId());
		envDto.setApelido(pessoa.getAlcunha());
		envDto.setCidade(pessoa.getCidadeNaturalidade() != null ? pessoa.getCidadeNaturalidade().getIdCidade() : null);
		envDto.setContato(contatoPessoaToDto(pessoa.getId()));
		envDto.setCpf(pessoa.getNumeroDocumento());
		envDto.setDataNascimento(localDateToDate(pessoa.getDataNascimento()));
		envDto.setDeficiencia(pessoa.getDeficiencia() != null ? pessoa.getDeficiencia().getValor() : null);
		envDto.setDesconhecido(tipoParticipacao.equals("II") ? true : false);
		envDto.setEndereco(enderecoPessoaToDto(endereco));
		envDto.setEscolaridade(pessoa.getEscolaridade() != null ? pessoa.getEscolaridade().getValor() : null);
		envDto.setEstado(pessoa.getEstadoNaturalidade() != null ? pessoa.getEstadoNaturalidade().getIdEstado() : null);
		envDto.setEstadoCivil(pessoa.getEstadoCivil() != null ? pessoa.getEstadoCivil().getValor() : null);
		envDto.setIdentidadeGenero(pessoa.getIdentidadeGenero() != null ? pessoa.getIdentidadeGenero().getValor() : null);
		envDto.setNome(pessoa.getNome());
		envDto.setNomeMae(pessoa.getNomeMae());
		envDto.setNomePai(pessoa.getNomePai());
		envDto.setNomeSocial(pessoa.getNumeroDocumento());
		envDto.setOrientacaoSexual(pessoa.getOrientacaoSexual() != null ? pessoa.getOrientacaoSexual().getValor() : null);				
		envDto.setPais(pessoa.getPais() != null ? pessoa.getPais().getIdPais() : null);
		envDto.setProfissao(pessoa.getProfissao() != null ? pessoa.getProfissao().getIdProfissao() : null);
		envDto.setRacaCor(pessoa.getCorPele() != null ? pessoa.getCorPele().getValor() : null);
		envDto.setSexo(pessoa.getSexo() != null ? pessoa.getSexo().getValor() : null);
		envDto.setTipoParticipacao(tipoParticipacao);				
		envDto.setUsarNomeSocial(false);
		return envDto;
	}
	
	private ContatoRequestDto contatoPessoaToDto(Integer idPessoa) {
		return contatoService.contatoToDto(idPessoa);
	}
	
	private ContatoRequestDto contatoEnvolvidoToDto(Envolvido envolvido) {
		ContatoRequestDto contatoDto = new ContatoRequestDto();
		contatoDto.setEmail(envolvido.getEmail());
		contatoDto.setNumero(envolvido.getNumeroDocumento());
		contatoDto.setWhatsapp(false);
		return contatoDto;
	}
	
//	converte a data de nascimento de LocalDate p/ Date como esperado pelo SIGMA
	private Date localDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
//	monta o DTO com a lista da equipe do BO composta pelo Analista/Registrante e o Delegado responsavel pela DEON p/ SIGMA 
	private List<EquipeRequestDto> listaEquipeBOtoDto(Integer idAnalista) {
//		seta o analista/registrante do BO da DEON
		Analista analista = analistaService.findById(idAnalista).orElse(null);
		
//		seta o delegado responsavel pela DEON, definido automaticamente pelo status ativo.
		DelegadoResponsavel delegado = delegadoService.getDelegadoResponsavel().orElse(null);
		
		List<EquipeRequestDto> listaEquipe = new ArrayList<>();

		if ((analista == null) || (delegado == null)) {
			return listaEquipe;
		}
		
		if (analista != null) {
			EquipeRequestDto analistaDto = new EquipeRequestDto();
			analistaDto.setAutoridade(false);
			analistaDto.setFkFuncionarioSigma(analista.getFuncionarioSigma());
			analistaDto.setRegistrante(true);
			listaEquipe.add(analistaDto);
		}
		if (delegado != null) {
			EquipeRequestDto delegadoDto = new EquipeRequestDto();
			delegadoDto.setAutoridade(true);
			delegadoDto.setFkFuncionarioSigma(delegado.getFuncionarioSigma());
			delegadoDto.setRegistrante(false);
			listaEquipe.add(delegadoDto);
		}
		return listaEquipe;
	}
	
//	monta uma lista de Integer com o FK da natureza do SIGMA com base na natureza do BO da DEON
	private List<Integer> listaFkNaturezaBo(BoDeon bo) {
		List<Integer> listaFkNatureza = new ArrayList<>();
		bo.getListaNaturezas().forEach(natureza -> 
			listaFkNatureza.add(natureza.getNaturezaDeon().getNaturezaSigma()));
		return listaFkNatureza;
	}
	
	public BoResponseDto enviaBoSigma(RequestDto request, String token) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", token);
			RestTemplate template = new RestTemplate();
			HttpEntity<RequestDto> requestEntity = new HttpEntity<RequestDto>(request, headers);
			ResponseEntity<BoResponseDto> response = template.exchange( URL + "salvarBoSigma", HttpMethod.POST, requestEntity, BoResponseDto.class);
			return response.getStatusCode().value() == 200 ? response.getBody() : null;					
		} catch (Exception e) {
			throw new EnviaBoSigmaException(e.getMessage());
		}
	}
	
	public BoDeon atualizaNumeroStatusBo(BoResponseDto responseDto) {
		return boService.atualizarNumeroStatus(responseDto);
	}
	
	
	
	
	
}
