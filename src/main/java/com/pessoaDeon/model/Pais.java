package com.pessoaDeon.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name="pais")
@Data
public class Pais {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id_pais")
     private int idPais;

     private String descricao;


}
