package com.pessoaDeon.domain.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnvolvidosDto {

	private EnvolvidoRequestDto vitima;
	private EnvolvidoRequestDto autor;
	private EnvolvidoRequestDto comuicante;
}
