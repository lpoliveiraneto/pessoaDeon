//package com.pessoaDeon.domain.service.impressao;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.pessoaDeon.domain.model.bo.BoDeon;
//import com.pessoaDeon.domain.model.bo.EnderecoLocalFato;
//import com.pessoaDeon.domain.model.dto.EnvolvidoBoDto;
//import com.pessoaDeon.domain.model.dto.integracao.EnderecoRequestDto;
//import com.pessoaDeon.domain.model.dto.integracao.EnvolvidoRequestDto;
//import com.pessoaDeon.domain.model.dto.integracao.TipoParticipacaoNatureza;
//import com.pessoaDeon.domain.model.endereco.Endereco;
//import com.pessoaDeon.domain.model.endereco.Logradouro;
//import com.pessoaDeon.domain.model.envolvido.EnderecoEnvolvido;
//import com.pessoaDeon.domain.model.envolvido.Envolvido;
//import com.pessoaDeon.domain.model.envolvido.Envolvimento;
//import com.pessoaDeon.domain.model.natureza.NaturezaBo;
//import com.pessoaDeon.domain.model.natureza.NaturezaDeon;
//import com.pessoaDeon.domain.model.pessoa.Pessoa;
//import com.pessoaDeon.domain.repository.bo.EnderecoLocalFatoRepository;
//import com.pessoaDeon.domain.service.EnderecoService;
//import com.pessoaDeon.domain.service.envolvido.EnvolvimentoService;
//import com.pessoaDeon.util.CabecalhoRodape;
//
//@Service
//public class BoImpressaoService {
//
//	@Autowired
//	private EnvolvimentoService envolvimentoService;
//
//	@Autowired
//	private EnderecoLocalFatoRepository enderecoLocalFatoRepository;
//	
//	@Autowired
//	private DataService dataService;
//	
//	@Autowired
//	private EnderecoService enderecoService;
//
//	public ByteArrayInputStream gerarBoImpressao(BoDeon boDeon, String modoSistema) {
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		Document document = new Document(PageSize.A4, 80, 36, 135, 55);
//
//		Font fLabel = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
//		Font fInfo = new Font(Font.FontFamily.HELVETICA, 9);
//		Font fHeader = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
//		Font fCell = new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC);
//
//		try {
//			PdfWriter writer = PdfWriter.getInstance(document, out);
//			CabecalhoRodape cabecalho = new CabecalhoRodape(boDeon, modoSistema);
//			writer.setPageEvent(cabecalho);
//			document.open();
//
//			adicionaInfoBo(document, fLabel, fInfo, boDeon);
//			adicionaEnvolvidosBo(document, fLabel, fInfo, fHeader, fCell, boDeon);
//
//		} catch (Exception e) {
//
//		}
//		return null;
//	}
//
//	private void adicionaInfoBo(Document document, Font fLabel, Font fInfo, BoDeon bo) throws DocumentException {
//
//		PdfPTable informacoesBO = new PdfPTable(2);
//		informacoesBO.setWidthPercentage(100);
//		informacoesBO.setWidths(new float[] { 0.5f, 0.5f });
//
//		PdfPCell cell;
//		List<NaturezaBo> naturezas = bo.getListaNaturezas();
//		StringBuilder naturezaBuilder = new StringBuilder();
//		naturezas.forEach(n -> {
//			NaturezaDeon naturezaDeon = n.getNaturezaDeon();
//			String codigo = (naturezaDeon.getCodigo() != null && !naturezaDeon.getCodigo().isBlank())
//					? " - " + naturezaDeon.getCodigo()
//					: "";
//			String nomeNatureza = naturezaDeon.getNome() + codigo;
//			naturezaBuilder.append(nomeNatureza);
//			if (naturezas.size() > 1) {
//				naturezaBuilder.append(" | ");
//			}
//		});
//
//		String natureza = naturezaBuilder.toString();
//
//		cell = new PdfPCell(new Phrase("FATO COMUNICADO: " + natureza, fLabel));
//		cell.setBorder(0);
//		cell.setVerticalAlignment(Element.ALIGN_LEFT);
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		informacoesBO.addCell(cell);
//
//		String dataHoraFato = (bo.getDataFato().toString() + " " + bo.getHoraFato().toString());
//
//		// Inserção da data e hora do fato
//		cell = new PdfPCell(new Phrase("Data/hora do Fato: " + dataHoraFato, fInfo));
//		cell.setBorder(0);
//		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		informacoesBO.addCell(cell);
//		document.add(informacoesBO);
//		document.add(new Paragraph("LOCAL DO FATO", fLabel));
//
//		// Inicio das informações sobre o local do fato
//		PdfPTable informacoesLocalBO = new PdfPTable(2);
//		informacoesLocalBO.setWidthPercentage(100);
//		informacoesLocalBO.setWidths(new float[] { 0.7f, 0.3f });
//		EnderecoLocalFato endereco = enderecoLocalFatoRepository.findById(bo.getIdBo()).get();
//
//		if (endereco.getCidade() != null) {
//			cell = new PdfPCell(new Phrase("Município: " + endereco.getCidade().getDescricao(), fInfo));
//			cell.setBorder(0);
//			cell.setVerticalAlignment(Element.ALIGN_LEFT);
//			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			informacoesLocalBO.addCell(cell);
//		} else {
//			cell = new PdfPCell(new Phrase("Município: ", fInfo));
//			cell.setBorder(0);
//			cell.setVerticalAlignment(Element.ALIGN_LEFT);
//			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			informacoesLocalBO.addCell(cell);
//		}
//
//		if (endereco.getEstado() != null) {
//			cell = new PdfPCell(new Phrase("UF: " + endereco.getEstado().getUf(), fInfo));
//			cell.setBorder(0);
//			cell.setVerticalAlignment(Element.ALIGN_LEFT);
//			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			informacoesLocalBO.addCell(cell);
//			document.add(informacoesLocalBO);
//		} else {
//			cell = new PdfPCell(new Phrase("UF: ", fInfo));
//			cell.setBorder(0);
//			cell.setVerticalAlignment(Element.ALIGN_LEFT);
//			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			informacoesLocalBO.addCell(cell);
//			document.add(informacoesLocalBO);
//		}
//
//		PdfPTable informacoesLocalBO2 = new PdfPTable(3);
//		informacoesLocalBO2.setWidthPercentage(100);
//		informacoesLocalBO2.setWidths(new float[] { 0.6f, 0.2f, 0.2f });
//
//		cell = new PdfPCell(new Phrase("Logradouro: " + endereco.getLogradouro(), fInfo));
//		cell.setBorder(0);
//		cell.setVerticalAlignment(Element.ALIGN_LEFT);
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		informacoesLocalBO2.addCell(cell);
//
//		cell = new PdfPCell(new Phrase("Nº: " + endereco.getNumeroLocal(), fInfo));
//		cell.setBorder(0);
//		cell.setVerticalAlignment(Element.ALIGN_LEFT);
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		informacoesLocalBO2.addCell(cell);
//
//		cell = new PdfPCell(new Phrase("CEP: " + endereco.getCep(), fInfo));
//		cell.setBorder(0);
//		cell.setVerticalAlignment(Element.ALIGN_LEFT);
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		informacoesLocalBO2.addCell(cell);
//		document.add(informacoesLocalBO2);
//
//		PdfPTable informacoesLocalBO3 = new PdfPTable(2);
//		informacoesLocalBO3.setWidthPercentage(100);
//		informacoesLocalBO3.setWidths(new float[] { 0.5f, 0.5f });
//		informacoesLocalBO3.getDefaultCell().setFixedHeight(50);
//		informacoesLocalBO3.getDefaultCell().setBorder(Rectangle.BOTTOM);
//		informacoesLocalBO3.getDefaultCell().setBorderColor(BaseColor.BLACK);
//
//		if (endereco.getBairro() != null) {
//			cell = new PdfPCell(new Phrase("Bairro: " + endereco.getBairro().getDescricao(), fInfo));
//			cell.setBorder(0);
//			cell.setVerticalAlignment(Element.ALIGN_LEFT);
//			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			informacoesLocalBO3.addCell(cell);
//		} else {
//			cell = new PdfPCell(new Phrase("Bairro: ", fInfo));
//			cell.setBorder(0);
//			cell.setVerticalAlignment(Element.ALIGN_LEFT);
//			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			informacoesLocalBO3.addCell(cell);
//		}
//
//		if (endereco.getTipoLocal() != null) {
//			cell = new PdfPCell(new Phrase("Tipo de local: " + endereco.getTipoLocal().getNome(), fInfo));
//			cell.setBorder(0);
//			cell.setVerticalAlignment(Element.ALIGN_LEFT);
//			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			informacoesLocalBO3.addCell(cell);
//			document.add(informacoesLocalBO3);
//		} else {
//			cell = new PdfPCell(new Phrase("Tipo de local: ", fInfo));
//			cell.setBorder(0);
//			cell.setVerticalAlignment(Element.ALIGN_LEFT);
//			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//			informacoesLocalBO3.addCell(cell);
//			document.add(informacoesLocalBO3);
//		}
//
//		PdfPTable informacoesLocalBO4 = new PdfPTable(1);
//		informacoesLocalBO4.setWidthPercentage(100);
//		informacoesLocalBO4.setWidths(new float[] { 0.9f });
//		informacoesLocalBO4.getDefaultCell().setFixedHeight(50);
//		informacoesLocalBO4.getDefaultCell().setBorder(Rectangle.BOTTOM);
//		informacoesLocalBO4.getDefaultCell().setBorderColor(BaseColor.BLACK);
//
//		cell = new PdfPCell(new Phrase("Referência: " + endereco.getReferencia(), fInfo));
//		cell.setBorder(0);
//		cell.setVerticalAlignment(Element.ALIGN_LEFT);
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		informacoesLocalBO4.addCell(cell);
//		document.add(informacoesLocalBO4);
//
//		PdfPTable informacoesLocalBO5 = new PdfPTable(1);
//		informacoesLocalBO5.setWidthPercentage(100);
//		informacoesLocalBO5.setWidths(new float[] { 0.9f });
//		informacoesLocalBO5.getDefaultCell().setFixedHeight(50);
//		informacoesLocalBO5.getDefaultCell().setBorder(Rectangle.BOTTOM);
//		informacoesLocalBO5.getDefaultCell().setBorderColor(BaseColor.BLACK);
//
//		cell = new PdfPCell(new Phrase("Complemento: " + endereco.getComplemento(), fInfo));
//		cell.setVerticalAlignment(Element.ALIGN_LEFT);
//		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//		cell.setPaddingBottom(5);
//		cell.setBorder(Rectangle.BOTTOM);
//		cell.setBorderColor(BaseColor.BLACK);
//		informacoesLocalBO5.addCell(cell);
//		document.add(informacoesLocalBO5);
//	}
//
//	private void adicionaEnvolvidosBo(Document document, Font fLabel, Font fInfo, Font fHeader, Font fcell, BoDeon bo) throws DocumentException {
//		Paragraph p;
//
//		List<Pessoa> listaPessoasEnvolvidas = new ArrayList<>();
//		
//		var listaEnvolvimento = envolvimentoService.getListaEnvolvimentoBo(bo.getIdBo());
//		
//		List<EnvolvidoBoDto> listaEnvolvidosBo = new ArrayList<>();
//		listaEnvolvimento.forEach(e -> {
//			listaEnvolvidosBo.add(montaDtoTipoEnvolvido(e));
//		});
//
//		String envolvimento = "";
//		for (int i = 0; i < listaEnvolvidosBo.size(); i++) {
//			if (listaEnvolvidosBo.get(i).getIdEnvolvido() != null) {
//				envolvimento = listaEnvolvidosBo.get(i).getTipoParticipacao();
//				if (envolvimento.equals("II")) {
//					Pessoa pf = listaEnvolvidosBo.get(i).getIdEnvolvido();
//					p = new Paragraph("ENVOLVIMENTO(S): " + envolvimento, fLabel);
//					document.add(p);
//					p = new Paragraph(adicionaPessoaFisica(pf, envolvimento, bo.getDataRegistro()), fInfo);
//					p.setAlignment(Element.ALIGN_JUSTIFIED);
//					p.setLeading(12f);
//					document.add(p);
////							adicionaObjetosBo(document, fLabel, fInfo, fHeader, fcell, bo,  pf);
//
//				} else if (envolvimento.contains("VITIMA") && !envolvimento.contains("COMUNICANTE")) {
//					Pessoa pf = listaPessoasEnvolvidas.get(i);
//					p = new Paragraph("ENVOLVIMENTO(S): " + envolvimento, fLabel);
//					document.add(p);
//					p = new Paragraph(adicionaPessoaFisica(pf, envolvimento, bo.getDataRegistro()), fInfo);
//					p.setAlignment(Element.ALIGN_JUSTIFIED);
//					p.setLeading(12f);
//					document.add(p);
////							adicionaObjetosBo(document, fLabel, fInfo, fHeader, fcell, bo,  pf);
//
//				} else if (envolvimento.contains("AUTOR")) {
//					Pessoa pf = listaPessoasEnvolvidas.get(i);
//					p = new Paragraph("ENVOLVIMENTO(S): " + envolvimento, fLabel);
//					document.add(p);
//					p = new Paragraph(adicionaPessoaFisica(pf, envolvimento, bo.getDataRegistro()), fInfo);
//					p.setAlignment(Element.ALIGN_JUSTIFIED);
//					p.setLeading(12f);
//					document.add(p);
//				}
//			}
//		}
//	}
//	
//	private List<EnvolvidoRequestDto> listaEnvolvidos(List<Envolvimento> envolvimentos) {
//		List<EnvolvidoRequestDto> listaEnvolvidosDto = new ArrayList<>();
//		envolvimentos.forEach(env -> {
//			EnvolvidoRequestDto envolvidoDto = new EnvolvidoRequestDto();
//			Envolvido envolvido = env.getEnvolvido();
//			Pessoa pessoa = env.getEnvolvido().getPessoa() != null ? env.getEnvolvido().getPessoa() : null; 
//			if (envolvido.getPessoa() == null) {
//				envolvidoDto = envolvidoToDto(envolvido, env.getTipoParticipacao().getValor());
//			}
//			if (pessoa != null) {
//				envolvidoDto = pessoaToEnvolvidoDto(pessoa, env.getTipoParticipacao().getValor());
//			}
//			listaEnvolvidosDto.add(envolvidoDto);
//		});
//		return listaEnvolvidosDto;
//	}
//
//	private EnvolvidoRequestDto envolvidoToDto(Envolvido envolvido, String tipoParticipacao) {
//		EnvolvidoRequestDto envDto = new EnvolvidoRequestDto();
//		EnderecoEnvolvido enderecoEnvolvido = envolvido.getEnderecoEnvolvido() != null ? envolvido.getEnderecoEnvolvido() : null;
//		if (enderecoEnvolvido != null) {
//			envDto.setApelido(envolvido.getAlcunha());
//			envDto.setCidade(envolvido.getCidadeNaturalidade() != null ? envolvido.getCidadeNaturalidade().getIdCidade() : null);
//			envDto.setContato(contatoEnvolvidoToDto(envolvido));
//			envDto.setCpf(envolvido.getNumeroDocumento());
//			envDto.setDataNascimento(envolvido.getDataNascimento() != null ? localDateToDate(envolvido.getDataNascimento()) : null);
//			envDto.setDeficiencia(envolvido.getDeficiencia() != null ? envolvido.getDeficiencia().getValor() : null);
//			envDto.setDesconhecido(tipoParticipacao.equals("II") ? true : false);
//			envDto.setEndereco(enderecoEnvolvidoToDto(enderecoEnvolvido));
//			envDto.setEscolaridade(envolvido.getEscolaridade() != null ? envolvido.getEscolaridade().getValor() : null);
//			envDto.setEstado(envolvido.getEstadoNaturalidade() != null ? envolvido.getEstadoNaturalidade().getIdEstado() : null);
//			envDto.setEstadoCivil(envolvido.getEstadoCivil() != null ? envolvido.getEstadoCivil().getValor() : null);
//			envDto.setIdentidadeGenero(envolvido.getIdentidadeGenero() != null ? envolvido.getIdentidadeGenero().getValor() : null);
//			envDto.setNome(envolvido.getNome());
//			envDto.setNomeMae(envolvido.getNomeMae());
//			envDto.setNomePai(envolvido.getNomePai());
//			envDto.setNomeSocial(envolvido.getNomeSocial());
//			envDto.setOrientacaoSexual(envolvido.getOrientacaoSexual() != null ? envolvido.getOrientacaoSexual().getValor() : null);				
//			envDto.setPais(envolvido.getPais() != null ? envolvido.getPais().getIdPais() : null);
//			envDto.setProfissao(envolvido.getProfissao() != null ? envolvido.getProfissao().getIdProfissao() : null);
//			envDto.setRacaCor(envolvido.getCorPele() != null ? envolvido.getCorPele().getValor() : null);
//			envDto.setSexo(envolvido.getSexo() != null ? envolvido.getSexo().getValor() : null);
//			envDto.setTipoParticipacao(tipoParticipacao);				
//			envDto.setUsarNomeSocial(false);
//		}
//		return envDto;
//	}
//	
//	private EnvolvidoRequestDto pessoaToEnvolvidoDto(Pessoa pessoa, String tipoParticipacao) {
//		EnvolvidoRequestDto envDto = new EnvolvidoRequestDto();
//		Endereco endereco = enderecoService.getEnderecoByIdPessoa(pessoa.getId()).orElse(null);
//		envDto.setApelido(pessoa.getAlcunha());
//		envDto.setCidade(pessoa.getCidadeNaturalidade() != null ? pessoa.getCidadeNaturalidade().getIdCidade() : null);
//		envDto.setContato(contatoPessoaToDto(pessoa.getId()));
//		envDto.setCpf(pessoa.getNumeroDocumento());
//		envDto.setDataNascimento(localDateToDate(pessoa.getDataNascimento()));
//		envDto.setDeficiencia(pessoa.getDeficiencia() != null ? pessoa.getDeficiencia().getValor() : null);
//		envDto.setDesconhecido(tipoParticipacao.equals("II") ? true : false);
//		envDto.setEndereco(enderecoPessoaToDto(endereco));
//		envDto.setEscolaridade(pessoa.getEscolaridade() != null ? pessoa.getEscolaridade().getValor() : null);
//		envDto.setEstado(pessoa.getEstadoNaturalidade() != null ? pessoa.getEstadoNaturalidade().getIdEstado() : null);
//		envDto.setEstadoCivil(pessoa.getEstadoCivil() != null ? pessoa.getEstadoCivil().getValor() : null);
//		envDto.setIdentidadeGenero(pessoa.getIdentidadeGenero() != null ? pessoa.getIdentidadeGenero().getValor() : null);
//		envDto.setNome(pessoa.getNome());
//		envDto.setNomeMae(pessoa.getNomeMae());
//		envDto.setNomePai(pessoa.getNomePai());
//		envDto.setNomeSocial(pessoa.getNumeroDocumento());
//		envDto.setOrientacaoSexual(pessoa.getOrientacaoSexual() != null ? pessoa.getOrientacaoSexual().getValor() : null);				
//		envDto.setPais(pessoa.getPais() != null ? pessoa.getPais().getIdPais() : null);
//		envDto.setProfissao(pessoa.getProfissao() != null ? pessoa.getProfissao().getIdProfissao() : null);
//		envDto.setRacaCor(pessoa.getCorPele() != null ? pessoa.getCorPele().getValor() : null);
//		envDto.setSexo(pessoa.getSexo() != null ? pessoa.getSexo().getValor() : null);
//		envDto.setTipoParticipacao(tipoParticipacao);				
//		envDto.setUsarNomeSocial(false);
//		return envDto;
//	}
//	
//	private EnderecoRequestDto enderecoPessoaToDto(Endereco endereco) {
//		if (endereco != null) {
//			Logradouro logradouro = endereco.getLogradouro();
//			EnderecoRequestDto enderecoDto = new EnderecoRequestDto();
//			enderecoDto.setBairro(logradouro.getBairro() != null ? logradouro.getBairro().getDescricao() : null);
//			enderecoDto.setCep(logradouro.getCep());
//			enderecoDto.setCidadeEndereco(logradouro.getCidade() != null ? logradouro.getCidade().getDescricao() : null);
//			enderecoDto.setComplemento(endereco.getComplemento());
//			enderecoDto.setEstadoEndereco(logradouro.getEstado() != null ? logradouro.getEstado().getDescricao() : null);
//			enderecoDto.setIdBairro(logradouro.getBairro() != null ? logradouro.getBairro().getIdBairro() : null);
//			enderecoDto.setIdCidadeEndereco(logradouro.getCidade() != null ? logradouro.getCidade().getIdCidade() : null);
//			enderecoDto.setIdEstadoEndereco(logradouro.getEstado() != null ? logradouro.getEstado().getIdEstado() : null);
//			enderecoDto.setLogradouro(endereco.getLogradouro() != null ? endereco.getLogradouro().getLogradouro() : null);
//			enderecoDto.setNumero(endereco.getNumero());
//			enderecoDto.setPaisEndereco(null);
//			enderecoDto.setReferencia(endereco.getReferencia());
//			enderecoDto.setUfEndereco(logradouro.getEstado() != null ? logradouro.getEstado().getUf() : null);
//			return enderecoDto;
//		}
//		return null;
//	}
//
//	public String adicionaPessoaFisica(Pessoa pessoaFisica, String envolvimento, LocalDateTime dataRegistro) {
//		StringBuilder envolvido = new StringBuilder();
//		envolvido.append(pessoaFisica.getNome().toUpperCase());
//		if (pessoaFisica.getDataNascimento() != null) {
//			envolvido.append((pessoaFisica.getDataNascimento() != null || dataRegistro != null) ? "(" + dataService.getIdade(pessoaFisica.getDataNascimento(), dataRegistro) + ")" : "");
//			envolvido.append((pessoaFisica.getDataNascimento() != null) ? ", nascido(a) em " + pessoaFisica.getDataNascimento() : "");
//			envolvido.append((pessoaFisica.getDataNascimento() == null) ? "" : "");
//		}
//
//		envolvido.append((pessoaFisica.getSexo() != null) ? ", sexo " + pessoaFisica.getSexo().getDescricao().toUpperCase().trim() : "");
//		envolvido.append((pessoaFisica.getDeficiencia() != null) ? ", deficiência: " + pessoaFisica.getDeficiencia().getDescricao().toUpperCase().trim() : "");
//		envolvido.append((pessoaFisica.getEstadoCivil() != null) ? ", " + pessoaFisica.getEstadoCivil().getDescricao().toLowerCase() : "");
//		envolvido.append((pessoaFisica.getProfissao() != null) ? ", exercendo a profissão de " + pessoaFisica.getProfissao().getDescricao().toUpperCase().trim() : "");
//		envolvido.append((pessoaFisica.getNumeroDocumento() != null) ? ", documento ou (CPF) Nº " + pessoaFisica.getNumeroDocumento() : "");
//		if (pessoaFisica.getPais() != null) {
//			envolvido.append(", País: " + pessoaFisica.getPais().getDescricao());
//		}
//		envolvido.append(
//				((pessoaFisica.getCidadeNaturalidade() != null) && (pessoaFisica.getEstadoNaturalidade() != null))
//						? ", natural de "
//								+ StringUtils.capitalize(
//										pessoaFisica.getCidadeNaturalidade().getDescricao().toUpperCase().trim())
//								+ "-" + pessoaFisica.getEstadoNaturalidade().getUf().toUpperCase().trim() : "");
//
//		envolvido.append((pessoaFisica.getNomeMae() != null) ? ", filho(a) de " + StringUtils.capitalize(pessoaFisica.getNomeMae().toUpperCase().trim()) : "");
//		envolvido.append(((pessoaFisica.getNomeMae() == null) && (pessoaFisica.getNomePai() != null)) ? ", filho(a) de" : "");
//		envolvido.append((pessoaFisica.getNomePai() != null) ? " e " + StringUtils.capitalize(pessoaFisica.getNomePai().toUpperCase().trim()) : "");
//
//		if (pessoaFisica.getId() != null) {
//			Endereco ultimoEndereco = enderecoService.getEnderecoByIdPessoaAtual(pessoaFisica.getId()).orElse(null);
//			if (ultimoEndereco != null) {
//				envolvido.append((!ultimoEndereco.getLogradouro().getCep().isEmpty()) ? ", cep: " + ultimoEndereco.getLogradouro().getCep().toUpperCase().trim() + "" : "");
//				envolvido.append((!ultimoEndereco.getNumero().isEmpty()) ? ", Nº: " + ultimoEndereco.getNumero().toUpperCase().trim() : "");
//				envolvido.append((ultimoEndereco.getLogradouro().getBairro() != null ? ", bairro: " + ultimoEndereco.getLogradouro().getBairro().getDescricao().toUpperCase().trim() : ""));
//				envolvido.append(((ultimoEndereco.getLogradouro().getCidade() != null) && (ultimoEndereco.getLogradouro().getEstado() != null))
//						? ", " + ultimoEndereco.getLogradouro().getCidade().getDescricao().toUpperCase().trim() + "-" + ultimoEndereco.getLogradouro().getEstado().getUf().toUpperCase().trim() : "");
//				envolvido.append((!ultimoEndereco.getComplemento().isEmpty() && ultimoEndereco.getComplemento() != "") ? ", complemento: " + ultimoEndereco.getComplemento().toUpperCase().trim() : "");
//				envolvido.append((!ultimoEndereco.getReferencia().isEmpty() && ultimoEndereco.getReferencia() != "") ? ", referência: " + ultimoEndereco.getReferencia().toUpperCase().trim() : "");
//
//			}
//			if (!pessoaFisica.getTelefone().isEmpty()) {
//				envolvido.append((!pessoaFisica.getTelefone().isEmpty()) ? ", Telefone: " + pessoaFisica.getTelefone().get(0).getTelefone().toUpperCase().trim() : "");
//			}
//			if (!pessoaFisica.getEmail().isEmpty()) {
//				envolvido.append((!pessoaFisica.getEmail().isEmpty()) ? ", Email: " + pessoaFisica.getEmail().get(0).getEmail().trim() : "");
//			} else {
//				envolvido.append(".");
//			}
//		}
//		return envolvido.toString();
//	}
//
//}
