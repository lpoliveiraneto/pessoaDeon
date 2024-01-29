package com.pessoaDeon.domain.model.pecas;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.pessoaDeon.domain.model.bo.BoDeon;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="peca", schema="public")
public class Peca implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String narrativa;
	
	@ManyToOne
    @JoinColumn(name="fk_bo")
	private BoDeon bo; 
	
	private Boolean status;
	
	private String ip;
	
	@ManyToOne
    @JoinColumn(name = "fk_tipo_peca")
	private TipoPeca tipoPeca;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDate dataCriacao;
	
	// @JsonIgnore
	// @OneToMany(mappedBy="peca", cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	// private List<RespostaFormularioRisco> listRespostaPeca;
}
