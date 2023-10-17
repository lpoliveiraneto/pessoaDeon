package com.pessoaDeon.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.pessoaDeon.domain.model.bo.BoDeon;
import com.pessoaDeon.domain.model.listas.UnidadeDestino;
import com.pessoaDeon.domain.service.impressao.DataService;

public class CabecalhoRodape extends PdfPageEventHelper {

	private PdfTemplate t;
	private Image total;
	
	@Autowired
	private BoDeon bo;

	@Autowired
	private UnidadeDestino unidade;
	
	@Autowired
	private DataService dataService;

	@Value("${spring.profiles.active}")
	private String modoSistema;

	public CabecalhoRodape(BoDeon bo, String modoSistema) {
		this.bo = bo;
		this.modoSistema = modoSistema;
	}

	public void onOpenDocument(PdfWriter writer, Document document) {
		t = writer.getDirectContent().createTemplate(36, 16);
		try {
			total = Image.getInstance(t);
			total.setRole(PdfName.ARTIFACT);
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		addHeader(writer);
		if (modoSistema != null && !modoSistema.equals("prod")) {
			Font fMarca = new Font(Font.FontFamily.HELVETICA, 70);
			fMarca.setColor(BaseColor.LIGHT_GRAY);
			Phrase phrase = new Phrase(" Sem validade  Sem validade ", fMarca);
			ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, phrase, 300, 400, 60);
		}
		addFooter(writer);
	}

	private void addHeader(PdfWriter writer) {
		PdfPTable header = new PdfPTable(2);
		try {
			// formatação
			header.setWidths(new int[] { 3, 24 });
			header.setTotalWidth(480);
			header.setLockedWidth(true);
			header.getDefaultCell().setFixedHeight(50);
			header.getDefaultCell().setBorder(Rectangle.BOTTOM);
			header.getDefaultCell().setBorderColor(BaseColor.BLACK);

			// adiciona logotipo
			Image logo = Image.getInstance(CabecalhoRodape.class.getResource("/static/sigma/images/policiaCivil.png"));
			PdfPCell cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.addElement(logo);
			cell.setBorder(Rectangle.BOTTOM);
			cell.setPaddingTop(10);
			header.addCell(cell);

			// adiciona cabeçalho
			PdfPCell text = new PdfPCell();
			text.setPaddingBottom(5);
			text.setPaddingLeft(10);
			text.setBorder(Rectangle.BOTTOM);
			text.setBorderColor(BaseColor.BLACK);
			text.addElement(new Phrase("GOVERNO DO ESTADO DO MARANHÃO", new Font(Font.FontFamily.HELVETICA, 10)));
			text.addElement(new Phrase("SECRETARIA DE SEGURANÇA PÚBLICA", new Font(Font.FontFamily.HELVETICA, 8)));
			text.addElement(new Phrase("POLÍCIA CIVIL DO ESTADO DO MARANHÃO", new Font(Font.FontFamily.HELVETICA, 8)));

//			if (bo.getUnidade() != null) {
//				text.addElement(new Phrase(bo.getUnidade().getDescricao().toUpperCase(),
//						new Font(Font.FontFamily.HELVETICA, 8)));
//				String logradouro = bo.getUnidade() != null ? bo.getUnidade().getLogradouro().toUpperCase() : "";
//				String numeroLocal = bo.getUnidade() != null ? bo.getUnidade().getNumeroLocal() : "";
//				String bairro = bo.getUnidade() != null ? bo.getUnidade().getBairro().toUpperCase() : "";
//				String cidade = bo.getUnidade() != null ? bo.getUnidade().getCidade().toUpperCase() : "";
//				String telefone1 = bo.getUnidade() != null ? bo.getUnidade().getTelefone1() : "";
//				String telefone2 = bo.getUnidade() != null ? bo.getUnidade().getTelefone2() : "";
//
//				String telefones = "";
//				if (!telefone1.isEmpty() && !telefone2.isEmpty()) {
//					telefones = ", " + telefone1 + " / " + telefone2;
//				} else if (!telefone1.isEmpty() && telefone2.isEmpty()) {
//					telefones = ", " + telefone1;
//				} else if (telefone1.isEmpty() && !telefone2.isEmpty()) {
//					telefones = ", " + telefone1;
//				} else if (telefone1.isEmpty() && telefone2.isEmpty()) {
//					telefones = "";
//				}
//				if (!bairro.isEmpty()) {
//					bairro = ", " + bairro;
//				} else {
//					bairro = "";
//				}
//				if (!numeroLocal.isEmpty()) {
//					numeroLocal = ", Nº " + numeroLocal;
//				} else {
//					numeroLocal = "";
//				}
//				if (!logradouro.isEmpty()) {
//					logradouro = " " + logradouro;
//				} else {
//					logradouro = "";
//				}
//				if (!cidade.isEmpty()) {
//					cidade = ", " + cidade;
//				} else {
//					cidade = "";
//				}
//
//				String endereco = "ENDEREÇO: " + logradouro + numeroLocal + bairro + cidade + telefones;
//				text.addElement(new Phrase(endereco, new Font(Font.FontFamily.HELVETICA, 8)));
//				text.addElement(new Phrase("EMAIL: " + bo.getUnidade().getEmail().toUpperCase(),
//						new Font(Font.FontFamily.HELVETICA, 8)));
//			} else {
//				Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
//				font.setColor(BaseColor.RED);
//				text.addElement(new Phrase("UNIDADE AUSENTE! Por favor, entre em contato com o suporte.", font));
//				Font fMarca = new Font(Font.FontFamily.HELVETICA, 70);
//				fMarca.setColor(BaseColor.LIGHT_GRAY);
//				Phrase phrase = new Phrase(" Sem validade  Sem validade ", fMarca);
//				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, phrase, 300, 400, 60);
//			}

			// formata data do registro do bo para exibição
//			dataService = new DataService();
//			String registro = dataService.formatarDataHora(bo.getDataRegistro());
//			String anoBo = dataService.formatarAnoBo(bo.getAno());
//			text.addElement(
//					new Phrase("Ocorrência Nº: " + bo.getNumeroBo() + "/" + anoBo + " - Registrado em: " + registro,
//							new Font(Font.FontFamily.HELVETICA, 12)));
//			header.addCell(text);
//			header.writeSelectedRows(0, -1, 80, 810, writer.getDirectContent());

		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (MalformedURLException e) {
			throw new ExceptionConverter(e);
		} catch (IOException e) {
			throw new ExceptionConverter(e);
		}
	}

	private void addFooter(PdfWriter writer) {
		PdfPTable footer = new PdfPTable(3);
		try {
			// set defaults
			footer.setWidths(new int[] { 24, 3, 1 });
			footer.setTotalWidth(480);
			footer.setLockedWidth(true);
			footer.getDefaultCell().setFixedHeight(40);
			footer.getDefaultCell().setBorder(Rectangle.TOP);
			footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

			// Inserção da data da impressão do bo
			SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String result = out.format(new Date());
			footer.addCell(new Phrase("Projeto NOVO SIGMA - Impresso em: " + result,
					new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC)));

			// add current page count
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			footer.addCell(new Phrase(String.format("Página %d de ", writer.getPageNumber()),
					new Font(Font.FontFamily.HELVETICA, 8)));

			// add placeholder for total page count
			PdfPCell totalPageCount = new PdfPCell(total);
			totalPageCount.setBorder(Rectangle.TOP);
			totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
			footer.addCell(totalPageCount);

			// write page
			PdfContentByte canvas = writer.getDirectContent();
			canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
			footer.writeSelectedRows(0, -1, 80, 50, canvas);
			canvas.endMarkedContentSequence();
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	public void onCloseDocument(PdfWriter writer, Document document) {
		int totalLength = String.valueOf(writer.getPageNumber()).length();
		int totalWidth = totalLength * 5;
		ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
				new Phrase(String.valueOf(writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)), totalWidth,
				6, 0);
	}

}
