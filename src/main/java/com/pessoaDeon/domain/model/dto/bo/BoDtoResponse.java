package com.pessoaDeon.domain.model.dto.bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.analista.RespostaAnaliseBo;
import com.pessoaDeon.domain.model.dto.EnvolvidoBoDto;
import com.pessoaDeon.domain.model.dto.NaturezaDeonResponseDto;
import com.pessoaDeon.domain.model.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    
    private String tokenBoSigma;
    
    private String numeroBo;

    private Status statusBo;
    
    private String descricaoStatusBo;
    
    private RespostaAnaliseBo respostaOcorrencia;
}
