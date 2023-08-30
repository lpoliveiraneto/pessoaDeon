package com.pessoaDeon.domain.model.natureza;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pessoaDeon.domain.model.bo.BoDeon;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class NaturezaBo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -727340197336367701L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_bo")
	private BoDeon bo;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_natureza_deon")
	private NaturezaDeon naturezaDeon;
	
}

