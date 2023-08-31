package com.pessoaDeon.domain.model.natureza;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pessoaDeon.domain.model.bo.BoDeon;

import com.pessoaDeon.domain.model.envolvido.Envolvimento;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class NaturezaBo implements Serializable{
	/**
	 * 
	 */
	@Serial
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

	@OneToMany(mappedBy = "naturezaBo", cascade = CascadeType.ALL)
	private List<Envolvimento> envolvimentos;
	
}

