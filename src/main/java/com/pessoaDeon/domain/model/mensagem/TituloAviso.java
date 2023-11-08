package com.pessoaDeon.domain.model.mensagem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pessoaDeon.domain.model.natureza.NaturezaDeon;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TituloAviso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTitulo;
	
	@NotEmpty
	private String nome;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "fk_natureza_deon")
	private NaturezaDeon naturezaDeon;
	
//	@JsonBackReference
	@OneToMany(mappedBy = "aviso", cascade = CascadeType.PERSIST)
	private List<DescricaoTitulo> listaDescricaoAviso = new ArrayList<>();
}
