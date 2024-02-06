package com.pessoaDeon.domain.model.pecas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pessoaDeon.domain.model.pecas.formulario_risco.RespostaFormularioRisco;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.pessoaDeon.domain.model.bo.BoDeon;

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
	
	//private String narrativa;
	
	@ManyToOne
    @JoinColumn(name="fk_bo")
	private BoDeon bo; 
	
	//private Boolean status;

	
	@ManyToOne
    @JoinColumn(name = "fk_tipo_peca")
	private TipoPeca tipoPeca;
	
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDate dataCriacao;
	
	 @JsonIgnore
	 @OneToMany(mappedBy="peca", cascade= CascadeType.ALL, fetch=FetchType.LAZY)
	 private List<RespostaFormularioRisco> listRespostaPeca;

	 /**
	  * @autor: Leonides Neto
	  * Metódo estático para retornar uma peça com o bo criado e um tipoPeça escolhido
	  * */
	public static Peca criacaoPeca(BoDeon bo, TipoPeca tipoPeca){
		Peca peca = new Peca();
		peca.setBo(bo);
		peca.setTipoPeca(tipoPeca);
		peca.setDataCriacao(LocalDate.now());
		return peca;
	}
}
