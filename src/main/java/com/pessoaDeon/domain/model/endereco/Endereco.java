package com.pessoaDeon.domain.model.endereco;

import com.pessoaDeon.domain.model.pessoa.Pessoa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer idEndereco;

    private String numero;

    private String referencia;
    
    private String complemento;

    private Boolean atual = false;

    @ManyToOne
    @JoinColumn(name = "fk_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "fk_logradouro")
    private Logradouro logradouro;
    
    @ManyToOne
    @JoinColumn(name = "fk_tipo_moradia")
    private TipoMoradia tipoMoradia;
}
