package com.pessoaDeon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="pais")
@Data
public class Pais {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int idPais;

     private String descricao;


}
