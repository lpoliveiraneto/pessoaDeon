package com.pessoaDeon.domain.model.dto.bo;

import java.util.List;

import com.pessoaDeon.domain.model.dto.EnvolvidosRequestDto;
import com.pessoaDeon.domain.model.dto.pecas.RequerimentoMpuDto;
import com.pessoaDeon.domain.model.dto.pecas.RespostasFormularioDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoOcorrenciaMulherDto{
	private BoDto bo;
	private EnvolvidosRequestDto envolvidos;
	private List<RespostasFormularioDto> respostasFormulario;
	private List<RequerimentoMpuDto> respostaRequerimento;
}
