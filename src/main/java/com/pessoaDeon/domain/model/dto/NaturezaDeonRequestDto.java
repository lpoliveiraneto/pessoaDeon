package com.pessoaDeon.domain.model.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaturezaDeonRequestDto {

	@NotNull
	private Integer idNaturezaSigma;
	
	@NotEmpty(message = "Não pode ser nulo ou vazio")
	private String codigo;
    
	@NotEmpty(message = "Não pode ser nulo ou vazio")
    private String glossario;
    
    @NotEmpty(message = "Nome não pode ser nulo e vazio")
    private String nome;
    
    @NotEmpty(message = "Não pode ser nulo ou vazio")
    private String pathSvg;
   
    @NotEmpty(message = "Não pode ser nulo ou vazio")
    private String tipoNatureza;
    
    private List<TituloRequestDto> titulos;
}
