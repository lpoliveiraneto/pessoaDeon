package com.pessoaDeon.domain.model.usuario;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.analista.Analista;
import com.pessoaDeon.domain.model.security.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAnalise implements Serializable { 

	private static final long serialVersionUID = 1L;

	@Id	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "id_usuario_analise")
    private Integer idUsuarioAnalise;

    @JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAnalise;

    @JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataEntradaAnalise;

    @OneToOne
    @JoinColumn(name = "fk_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_analista")
    private Analista analista;

    @OneToOne
    @JoinColumn(name = "fk_resposta_analise_usuario")
    private RespostaAnaliseUsuario respostaAnalise;
    
    private boolean status;
}
