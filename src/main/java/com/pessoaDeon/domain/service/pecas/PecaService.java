package com.pessoaDeon.domain.service.pecas;

import java.time.LocalDate;
import java.util.List;

import com.pessoaDeon.domain.model.pecas.requerimento_mpu.RequerimentoPecaMpu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pessoaDeon.domain.model.pecas.Peca;//import com.pessoaDeon.domain.model.pecas.RequerimentoPecaMpu;
import com.pessoaDeon.domain.model.pecas.formulario_risco.RespostaFormularioRisco;
import com.pessoaDeon.domain.model.pessoa.Pessoa;
import com.pessoaDeon.domain.repository.pecas.PecaRepository;

@Service
public class PecaService {

	@Autowired
	private PecaRepository pecaRepository;

    public void salvarPeca(Peca peca) {
        
		//List<PessoasPecas> listaPessoas = peca.getListaPessoasPeca();
		//List<RequerimentoPecaMpu> listaProvidencia = peca.getListProvidenciaPeca();
		//List<RespostaFormularioRisco> listaRespostas = peca.getListRespostaPeca();

		peca.setDataCriacao(LocalDate.now());
		peca.setStatus(true);
		pecaRepository.save(peca);

		//salvarPessoasPeca(peca, listaPessoas);
		//salvarProvidenciasPeca(peca, listaProvidencia);
		//salvarFormulario(peca, listaRespostas);

		//return peca;
        
    }
	public void salvarPessoasPeca(Peca peca, List<Pessoa> listaPessoas) {

	}

	public void salvarProvidenciasPeca(Peca peca, List<RequerimentoPecaMpu> listaRequerimento) {
		for (RequerimentoPecaMpu requerimentoPecaMpu : listaRequerimento) {
			if (requerimentoPecaMpu.getRequerimentoMpu() != null) {
				requerimentoPecaMpu.setDataCriacao(LocalDate.now());
				requerimentoPecaMpu.setStatus_ativo(true);
				requerimentoPecaMpu.setPeca(peca);
				//providenciaPecaRepository.save(providenciaPeca);
			}
		}
	}

	public void salvarFormulario(Peca peca, List<RespostaFormularioRisco> listaRespostas) {
		Peca formularioRisco = new Peca();
		formularioRisco.setDataCriacao(LocalDate.now());
		formularioRisco.setStatus(true);

		pecaRepository.save(formularioRisco);

	}
}
