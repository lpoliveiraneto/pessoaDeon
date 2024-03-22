package com.pessoaDeon.domain.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class EnvolvidosRequestDto {

	private EnvolvidoDto vitima;
	private EnvolvidoDto autor;
	private EnvolvidoDto comunicante;
}
