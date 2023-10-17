package com.pessoaDeon.domain.model.analista;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoaDeon.domain.model.bo.BoDeon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
