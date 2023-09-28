package com.pessoaDeon.domain.repository.listas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pessoaDeon.domain.model.endereco.ListaEndereco;

@Repository
public interface ListaEnderecoRepository extends JpaRepository<ListaEndereco, Integer> {

}
