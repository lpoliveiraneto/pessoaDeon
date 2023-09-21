package com.pessoaDeon.domain.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnvolvidosRequestDto {

	private EnvolvidoDto vitima;
	private EnvolvidoDto autor;
	private EnvolvidoDto comunicante;
}
