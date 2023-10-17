package com.pessoaDeon.domain.service.impressao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.pessoaDeon.domain.model.bo.BoDeon;

public class BoImpressaoService {

	public ByteArrayInputStream gerarBoImpressao(BoDeon boDeon, String modoSistema) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4, 80, 36, 135, 55);
		
		Font fLabel = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		Font fInfo = new Font(Font.FontFamily.HELVETICA, 9);
		Font fHeader = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
		Font fCell = new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC);
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, out);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}

}
