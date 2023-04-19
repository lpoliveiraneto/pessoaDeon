package com.pessoaDeon.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="pais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pais {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id_pais")
     private int idPais;

     private String descricao;


}
