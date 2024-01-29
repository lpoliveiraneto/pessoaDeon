package com.pessoaDeon.domain.model.pecas.requerimento_mpu;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "titulo_requerimento_mpu", schema = "mpu")
public class TituloRequerimentoMpu implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	private Boolean statusAtivo;
	
	@OneToMany(mappedBy = "tituloRequerimento",cascade=CascadeType.ALL)
	private List<RequerimentoMpu> listaProvidencias;
}
