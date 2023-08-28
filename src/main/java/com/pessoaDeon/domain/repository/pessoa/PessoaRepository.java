package com.pessoaDeon.domain.repository.pessoa;

import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.model.listas.TipoDocumento;

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

	Optional<Pessoa> findByNumeroDocumento(String numeroDocumento);

}
