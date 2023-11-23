package com.pessoaDeon.domain.model.envolvido;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pessoaDeon.domain.model.endereco.TipoMoradia;
import com.pessoaDeon.domain.model.listas.Bairro;
import com.pessoaDeon.domain.model.listas.Cidade;
import com.pessoaDeon.domain.model.listas.Estado;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class EnderecoEnvolvido {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEnderecoEnvolvido;

	private String cep;
	
	private String complemento;
	
	private String numeroLocal;
	
	private String referencia;
	
	private String logradouro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	@JoinColumn(name = "fk_bairro")
	private Bairro bairro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	@JoinColumn(name = "fk_cidade")
	private Cidade cidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	@JoinColumn(name = "fk_estado")
	private Estado estado;
	
	@ManyToOne
    @JoinColumn(name = "fk_tipo_moradia")
    private TipoMoradia tipoMoradia;
}
