package com.pessoaDeon.domain.model.listas;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="pais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pais", schema="listas")
public class Pais {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id_pais")
     private Integer idPais;

     private String descricao;


}
