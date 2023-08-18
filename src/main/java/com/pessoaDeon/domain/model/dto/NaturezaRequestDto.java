package com.pessoaDeon.domain.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NaturezaRequestDto {

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
	
}
