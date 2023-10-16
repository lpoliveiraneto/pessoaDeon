package com.pessoaDeon.domain.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.enumeration.Status;
import com.pessoaDeon.domain.model.util.EnumToObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoDtoResponse {

	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataRegistro;

	@JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East" , pattern = "yyyy-MM-dd")
	private LocalDate dataFato;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime horaFato;
	
	private String relato;

	private String relatoEditado;
	
	private String cep;
	
	private String referencia;
	
	private String complemento;
	
	private String numeroLocal;
	
	private String logradouro;
	
	private String bairroDescricao;

	private String cidadeDescricao;

	private String estadoDescricao;
	
    private String tipoLocal;
    
    private String protocolo;
    
    private List<NaturezaDeonResponseDto> listaNatureza;
    
    private List<EnvolvidoBoDto> listaEnvolvidos;
    
    private EnumToObject status;
    
}
