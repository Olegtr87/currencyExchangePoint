package com.epam.vasilevsky.exchanger.webapp.page.invoice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.webapp.app.AuthorizedSession;
import com.epam.vasilevsky.exchanger.webapp.app.WicketApplication;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class InvoicePdf {

	UserService userService;

	Integer sumIn;

	String curFrom;

	String nameOperation;

	private Transaction transaction;

	public InvoicePdf(Transaction transaction, UserService userService) {
		this.transaction = transaction;
		this.userService = userService;
	}

	public InvoicePdf(Transaction transaction, UserService userService, String operation, Integer sumIn,
			String curFrom) {
		this.transaction = transaction;
		this.userService = userService;
		this.nameOperation = operation;
		this.sumIn = sumIn;
		this.curFrom = curFrom;
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
			Paragraph p1 = new Paragraph(getStr("check.label.page"), getFont());
			p1.setAlignment(Element.ALIGN_CENTER);

			document.add(p1);
			Paragraph p2 = new Paragraph(getStr("check.label.numbercheck")+": " + transaction.getId(), getFont());
			p2.setAlignment(Element.ALIGN_CENTER);
			document.add(p2);
			document.add(new Paragraph(
					getStr("check.label.payer")+": " + userService.getProfile(transaction.getUser().getId()).getFirstName() + " "
							+ userService.getProfile(transaction.getUser().getId()).getLastName(),
					getFont()));
			PdfPTable t = new PdfPTable(5);
			t.setSpacingBefore(25);
			t.setSpacingAfter(25);

			PdfPCell c1 = new PdfPCell(new Phrase(getStr("operations.label.operation"), getFont()));
			t.addCell(c1);
			PdfPCell c2 = new PdfPCell(new Phrase(getStr("converter.sumin"), getFont()));
			t.addCell(c2);
			PdfPCell c3 = new PdfPCell(new Phrase(getStr("editcourse.label.currency.from"), getFont()));
			t.addCell(c3);
			PdfPCell c4 = new PdfPCell(new Phrase(getStr("editcourse.label.currency.to"), getFont()));
			t.addCell(c4);
			PdfPCell c5 = new PdfPCell(new Phrase(getStr("transacions.label.total"), getFont()));
			t.addCell(c5);

			if (nameOperation == null) {
				t.addCell(transaction.getOperation().getName());
				t.addCell(transaction.getSumIn().toString());
				t.addCell(transaction.getExchangeRate().getCurrencyFrom().getName().name());
			} else {
				t.addCell("convertation");
				t.addCell(sumIn.toString());
				t.addCell(curFrom);
			}

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

	private Integer totalSum() {
		return transaction.getTotalSum();
	}

	private Font getFont() {
		BaseFont helvetica;
		try {
			helvetica = BaseFont.createFont("ARIAL.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		} catch (DocumentException | IOException e) {
			helvetica = null;
			e.printStackTrace();
		}
		Font font = new Font(helvetica);
		return font;
	}

	private String getStr(String string) {
		String operationName = WicketApplication.get().getResourceSettings().getLocalizer().getString(string, null);
		return operationName;
	}
}
