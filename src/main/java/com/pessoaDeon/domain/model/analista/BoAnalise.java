package com.pessoaDeon.domain.model.analista;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.bo.BoDeon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="bo_analise")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoAnalise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bo_analise")
    private Integer idAnalise;

    @JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAnalise;

    @JsonFormat(shape=JsonFormat.Shape.STRING, locale = "pt-BR", timezone = "Brazil/East", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataEntradaAnalise;

    @OneToOne
    @JoinColumn(name = "fk_bo")
    private BoDeon boDeon;

    @ManyToOne
    @JoinColumn(name = "fk_analista")
    private Analista analista;

    private boolean status;

    @OneToOne
    @JoinColumn(name = "fk_respota_analise_bo")
    private RespostaAnaliseBo respostaAnaliseBo;
}
