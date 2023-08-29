package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.natureza.NaturezaBo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoDtoResponse {

	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "yyyy-MM-dd")
	private LocalDate dataFato;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime horaFato;
	
	private String relato;

	private String relatoEditado;
	
	private String cep;
	
	private String complemento;
	
	private Integer numeroLocal;
	
	private String logradouro;
	
	private String bairroDescricao;

	private String cidadeDescricao;

	private String estadoDescricao;
	
    private String tipoLocal;
    
    private String protocolo;
    
    private List<NaturezaBo> listaNaturezaBo = new ArrayList<>();
}
