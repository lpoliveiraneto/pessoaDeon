package com.pessoaDeon.domain.service.impressao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DataService {

	private final SimpleDateFormat FORMATANO = new SimpleDateFormat("yyyy");

	public int getIdade(Date dataNascimento, Date dataRegistro) {
		if (dataNascimento != null) {
			int anoFato = Integer.parseInt(FORMATANO.format(dataRegistro));
			int anoNascimento = Integer.parseInt(FORMATANO.format(dataNascimento));
			int idadeAtual = (anoFato - anoNascimento);
			if (idadeAtual > 0) {
				Calendar cNascimento = Calendar.getInstance();
				cNascimento.setTime(dataNascimento);
				Calendar cBo = Calendar.getInstance();
				cBo.setTime(dataRegistro);

				if (cNascimento.get(Calendar.MONTH) < cBo.get(Calendar.MONTH)) {
					return idadeAtual;
				} else if (cNascimento.get(Calendar.MONTH) > cBo.get(Calendar.MONTH)) {
					return idadeAtual - 1;
				} else if (cNascimento.get(Calendar.MONTH) == cBo.get(Calendar.MONTH)) {
					if (cNascimento.get(Calendar.DAY_OF_MONTH) <= cBo.get(Calendar.DAY_OF_MONTH)) {
						return idadeAtual - 1;
					} else {
						return idadeAtual;
					}
				}
			}
			return idadeAtual;
		}
		return 0;
	}

	public Boolean anosIguais(Date dataSalva) {
		Date dataAtual = new Date();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(dataAtual);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(dataSalva);
		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
	}

	public String formatarDataHora(Date dataFormatada) {
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat hora = new SimpleDateFormat("HH");
		SimpleDateFormat min = new SimpleDateFormat("mm");
		String dataRegistroFormatada = data.format(dataFormatada) + " às " + hora.format(dataFormatada) + "h "
				+ min.format(dataFormatada) + "min";
		return dataRegistroFormatada;
	}

	public String formatarDataNascimento(Date dataNascimento) {
		SimpleDateFormat dataNascimentoFormatada = new SimpleDateFormat("dd/MM/yyyy");
		return dataNascimentoFormatada.format(dataNascimento);
	}

	public Boolean eDataFutura(Date dataRequisicao) {
		Date dataAtual = new Date();
		if (dataRequisicao != null) {
			if (dataRequisicao.after(dataAtual)) {
				return true;
			} else {
				return false;
			}
		}
		return null;
	}

	public Boolean comparaDatas(Date dataRequisicao, Date dataRequisicao2) {
		if (dataRequisicao != null) {
			if (dataRequisicao.after(dataRequisicao2)) {
				return true;
			} else {
				return false;
			}
		}
		return null;
	}

	public Date dataServidor(String data, String tipo) throws ParseException {
		if (tipo != null && !tipo.isEmpty() && data != null && !data.isEmpty()) {
			switch (tipo) {
			case "registro":
				data = formatarDataServidor(data, tipo);
				return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data);
			case "ano":
				data = formatarDataServidor(data, tipo);
				return new SimpleDateFormat("yyyy").parse(data);
			case "data":
				data = formatarDataServidor(data, tipo);
				return new SimpleDateFormat("dd/MM/yyyy").parse(data);
			}
		}
		return null;
	}

	public String formatarDataServidor(String termo, String tipo) {
		switch (tipo) {
		case "registro":
			// dd/MM/yyyy HH:mm:ss
			return termo.substring(0, 2) + "/" + termo.substring(2, 4) + "/" + termo.substring(4, 8) + " "
					+ termo.substring(8, 10) + ":" + termo.substring(10, 12) + ":" + termo.substring(12, 14);
		case "ano":
			return termo;
		case "data":
			// dd/MM/yyyy
			return termo.substring(0, 2) + "/" + termo.substring(2, 4) + "/" + termo.substring(4, 8);
		}
		return null;
	}

	public String formatarAnoBo(Date anoBo) {
		SimpleDateFormat anoBoFormatado = new SimpleDateFormat("yyyy");
		return anoBoFormatado.format(anoBo);
	}

	public String formatarDataLocalFato(Date data, Date hora) {
		SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat horaFormatada = new SimpleDateFormat("HH");
		SimpleDateFormat minFormatada = new SimpleDateFormat("mm");
		return dataFormatada.format(data) + " às " + horaFormatada.format(hora) + "h " + minFormatada.format(hora)
				+ "min";
	}

	public String mesDoAno(int mes) {
		String[] mesNomes = { "janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro",
				"outubro", "novembro", "dezembro" };
		return mesNomes[mes];
	}

	public String dataMesAno(Date dataMesAno) {
		SimpleDateFormat dia = new SimpleDateFormat("dd");
		SimpleDateFormat ano = new SimpleDateFormat("yyyy");
		Calendar cData = Calendar.getInstance();
		cData.setTime(dataMesAno);
		String nomeDoMes = mesDoAno(cData.get(Calendar.MONTH));
		String dataString = dia.format(dataMesAno) + " de " + nomeDoMes + " de " + ano.format(dataMesAno);
		return dataString;
	}

	public String formatarHora(Date hora) {
		SimpleDateFormat horaFormatada = new SimpleDateFormat("HH");
		SimpleDateFormat minFormatada = new SimpleDateFormat("mm");
		return horaFormatada.format(hora) + "h " + minFormatada.format(hora) + "min";
	}

	public String TextoPecadataMesAno(Date dataCriacao) {
		Calendar cDataCriacao = Calendar.getInstance();
		cDataCriacao.setTime(dataCriacao);
		String nomeDoMes = mesDoAno(cDataCriacao.get(Calendar.MONTH));
		String textoData = "ao(s) " + cDataCriacao.get(Calendar.DAY_OF_MONTH) + " dia(s) do mês de " + nomeDoMes
				+ " de " + cDataCriacao.get(Calendar.YEAR);
		return textoData;
	}

}
