package com.pessoaDeon.domain.model.pessoa;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="telefone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTelefone;

    private String telefone;

    private Boolean atual = false;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_pessoa")
    private Pessoa pessoa;
    
    private Boolean tipowhatsapp = false;
    
    private Boolean tipotelegram = false;
    
}
