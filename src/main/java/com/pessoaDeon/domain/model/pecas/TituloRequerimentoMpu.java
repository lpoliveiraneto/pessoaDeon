package com.pessoaDeon.domain.model.pecas;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "titulo_requerimento_mpu", schema = "peca")
public class TituloRequerimentoMpu implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="fk_tipo_peca")
	private TipoPeca tipoPeca;
	
	private Boolean statusAtivo;
	
	@OneToMany(mappedBy = "tituloRequerimento",cascade=CascadeType.ALL)
	private List<RequerimentoMpu> listaProvidencias;
}