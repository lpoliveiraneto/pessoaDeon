package com.pessoaDeon.domain.model.natureza;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pessoaDeon.domain.model.bo.BoDeon;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaturezaBo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@JoinColumn(name = "fk_bo")
	@ManyToOne(fetch = FetchType.LAZY)
	private BoDeon bo;
	
	@JsonIgnore
	@NotNull
	@JoinColumn(name = "fk_natureza_deon")
	@ManyToOne(fetch = FetchType.LAZY)
	private NaturezaDeon naturezaDeon;
}

