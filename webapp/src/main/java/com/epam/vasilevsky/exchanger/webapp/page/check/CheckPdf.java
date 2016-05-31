package com.epam.vasilevsky.exchanger.webapp.page.check;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.webapp.app.AuthorizedSession;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CheckPdf {
	
	UserService userService;
	
	private Transaction transaction;
	
	public CheckPdf(Transaction transaction, UserService userService){
		this.transaction=transaction;
		this.userService=userService;
	}
	
	private String createNamePdf() {
		return AuthorizedSession.get().getLoggedUser().getLogin() + ".pdf";
	}
	
	public File createPdf() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("src/main/pdf/checks/" + createNamePdf()));
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		document.open();
		try {
			Paragraph p1 = new Paragraph("Claim check", FontFactory.getFont(FontFactory.COURIER, 25, Font.BOLD));
			p1.setAlignment(Element.ALIGN_CENTER);

			document.add(p1);
			Paragraph p2 = new Paragraph("Number check: " + transaction.getId());
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			document.add(new Paragraph(
					"Payed: " + userService.getProfile(transaction.getUser().getId()).getFirstName() + " "
							+ userService.getProfile(transaction.getUser().getId()).getLastName(),
					FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255, 17))));
			PdfPTable t = new PdfPTable(5);
			t.setSpacingBefore(25);
			t.setSpacingAfter(25);
			PdfPCell c1 = new PdfPCell(new Phrase("Operation"));
			t.addCell(c1);
			PdfPCell c2 = new PdfPCell(new Phrase("Sum in"));
			t.addCell(c2);
			PdfPCell c3 = new PdfPCell(new Phrase("From"));
			t.addCell(c3);
			PdfPCell c4 = new PdfPCell(new Phrase("To"));
			t.addCell(c4);
			PdfPCell c5 = new PdfPCell(new Phrase("Total"));
			t.addCell(c5);
			t.addCell(transaction.getOperation().getName());
			t.addCell(transaction.getSumIn().toString());
			t.addCell(transaction.getExchangeRate().getCurrencyFrom().getName().name());
			t.addCell(transaction.getExchangeRate().getCurrencyTo().getName().name());
			t.addCell(totalSum().toString());
			document.add(t);
			document.add(new Paragraph(transaction.getDateOperation().toString()));
			Image image;
			try {
				image = Image.getInstance("src/main/pdf/images/stamp.png");
				image.scaleAbsolute(120f, 120f);
				image.setAlignment(Element.ALIGN_CENTER);
				document.add(image);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.close();
		File file = new File("src/main/pdf/checks/" + createNamePdf());
		return file;
	}
	
	private Double totalSum() {
		return transaction.getSumIn() * transaction.getExchangeRate().getConversion();
	}
}
