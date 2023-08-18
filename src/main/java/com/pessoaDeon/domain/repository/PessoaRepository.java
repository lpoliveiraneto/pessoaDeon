package com.pessoaDeon.domain.repository;

import com.pessoaDeon.domain.model.Email;
import com.pessoaDeon.domain.model.Pessoa;
import com.pessoaDeon.domain.model.TipoDocumento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	List<Pessoa> findByNomeAndNomeMaeAndDataNascimento(String nome, String nomeMae, LocalDate dataNascimento);

	Optional<Pessoa> findByTipoDocumentoAndNumeroDocumento(TipoDocumento tipoDocumento, String numeroDocumento);

	Optional<Pessoa> findByEmailEmail(String email);

}
