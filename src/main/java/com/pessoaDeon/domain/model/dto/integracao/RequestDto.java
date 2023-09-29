package com.pessoaDeon.domain.model.dto.integracao;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
	
	private BoRequestDto boDto;
	
	@NotNull(message = "Lista de envolvidos não está presente!")
	private List<EnvolvidoRequestDto> listaEnvolvidos;
	
	@NotNull(message = "Lista de naturezas não está presente!")
	private List<Integer> listaFkNatureza;
	
	@NotNull(message = "Lista de equipe do BO não está presente!")
	private List<EquipeRequestDto> listaEquipe;

}
