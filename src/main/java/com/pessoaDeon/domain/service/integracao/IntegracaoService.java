package com.pessoaDeon.domain.service.integracao;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.bo.EnderecoLocalFato;
import com.pessoaDeon.domain.model.dto.integracao.BoRequestDto;
import com.pessoaDeon.domain.model.dto.integracao.EnvolvidoRequestDto;
import com.pessoaDeon.domain.model.dto.integracao.RequestDto;
import com.pessoaDeon.domain.model.envolvido.EnderecoEnvolvido;
import com.pessoaDeon.domain.model.envolvido.Envolvido;
import com.pessoaDeon.domain.model.envolvido.Envolvimento;
import com.pessoaDeon.domain.service.bo.EnderecoLocalFatoService;

@Service
public class IntegracaoService {
	
	@Autowired
	private EnderecoLocalFatoService elfService;

	public void dadosBoToDto(BoDeon bo, List<Envolvimento> envolvimentos, Analista analista) {
		RequestDto request = new RequestDto();
		request.setBoDto(boToDto(bo));
//		request.setListaEnvolvidos();
	}
	
	private BoRequestDto boToDto(BoDeon bo) {
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
//		boDto.setFkPais(null);
		
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime combinedDateTime = zonedDateTime.with(bo.getHoraFato());
        Date convertToDate = Date.from(combinedDateTime.toInstant());
		
		boDto.setHoraFato(convertToDate);
		boDto.setIdBoDeon(bo.getIdBo());
//		boDto.setLatLong(null);
		boDto.setLogradouro(endLocal.getLogradouro());
		boDto.setNumeroLocal(endLocal.getNumeroLocal().toString());
//		boDto.setPais(null);
		boDto.setReferencia(endLocal.getReferencia());
		boDto.setRelato(bo.getRelato());
		boDto.setTipoLocal(endLocal.getTipoLocal());
		boDto.setUf(endLocal.getEstado().getUf());
		boDto.setUnidade(1025);
		
		return boDto;
	}

	private List<EnvolvidoRequestDto> listaEnvolvidos(List<Envolvimento> envolvimentos) {
		List<EnvolvidoRequestDto> listaEnvolvidosDto = new ArrayList<>();
		EnvolvidoRequestDto envDto = new EnvolvidoRequestDto();
		envolvimentos.forEach(env -> {
			Envolvido envolvido = env.getEnvolvido();
			EnderecoEnvolvido enderecoEnvolvido = envolvido.getEnderecoEnvolvido();
			if (envolvido.getPessoa() == null) {
				envDto.setApelido(envolvido.getAlcunha());
				envDto.setCidade(envolvido.getCidadeNaturalidade().getIdCidade());
				envDto.setContato(null);
				envDto.setCpf(envolvido.getNumeroDocumento());
				envDto.setDataNascimento(null);			}
			listaEnvolvidosDto.add(envDto);
		});
		return listaEnvolvidosDto;
	}
	
}
