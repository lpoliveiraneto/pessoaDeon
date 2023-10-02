package com.pessoaDeon.domain.model.envolvido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pessoaDeon.domain.model.natureza.NaturezaBo;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idEnvolvimento")
public class Envolvimento  implements Serializable {
    /**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnvolvimento;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_natureza_bo")
    private NaturezaBo naturezaBo;


    @OneToOne
    @JoinColumn(name = "fk_envolvido")
    private Envolvido envolvido;

    @OneToOne
    @JoinColumn(name = "fk_tipo_participacao")
    private TipoParticipacao tipoParticipacao;
}
