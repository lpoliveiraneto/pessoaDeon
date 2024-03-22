package com.pessoaDeon.domain.model.dto.pecas;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequerimentoMpuDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id; 
	private String descricao;
	private String especificacao;
	

}
