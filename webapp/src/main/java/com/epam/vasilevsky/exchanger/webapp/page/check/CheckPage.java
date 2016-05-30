package com.epam.vasilevsky.exchanger.webapp.page.check;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.inject.Inject;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.DownloadLink;
import com.epam.vasilevsky.exchanger.dataaccess.filters.ExchangeRateFilter;
import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;
import com.epam.vasilevsky.exchanger.datamodel.ExchangeRate;
import com.epam.vasilevsky.exchanger.datamodel.Operation;
import com.epam.vasilevsky.exchanger.datamodel.Transaction;
import com.epam.vasilevsky.exchanger.service.CurrencyService;
import com.epam.vasilevsky.exchanger.service.ExchangeRateService;
import com.epam.vasilevsky.exchanger.service.OperationService;
import com.epam.vasilevsky.exchanger.service.TransactionService;
import com.epam.vasilevsky.exchanger.service.UserService;
import com.epam.vasilevsky.exchanger.webapp.app.AuthorizedSession;
import com.epam.vasilevsky.exchanger.webapp.page.AbstractHomePage;
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

public class CheckPage extends AbstractHomePage {

	private Transaction transaction;
	private ExchangeRate exchangeRate;

	@Inject
	OperationService operationService;
	@Inject
	CurrencyService currencyService;
	@Inject
	ExchangeRateService exchangeRateService;
	@Inject
	TransactionService transactionService;
	@Inject
	UserService userService;

	public CheckPage(Transaction transaction, ExchangeRate exchangeRate) {
		super();
		this.transaction = transaction;
		this.exchangeRate = exchangeRate;
	}

	private ExchangeRateFilter exchangeRateFilter;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		setTransaction();
		transactionService.add(transaction, AuthorizedSession.get().getLoggedUser(), searchOperation(),
				searchExchangeRate());

		add(new Label("message", "The transaction has been successful"));
		add(new Label("number_check", "Number check " + transaction.getId()));
		add(new Label("payer", "Payer: " + userService.getProfile(transaction.getUser().getId()).getFirstName() + " "
				+ userService.getProfile(transaction.getUser().getId()).getLastName()));
		add(new Label("operation", "Operation: " + transaction.getOperation().getName()));
		add(new Label("sumin", "Sum in: " + transaction.getSumIn()));
		add(new Label("currency", "Currency from " + transaction.getExchangeRate().getCurrencyFrom().getName() + " to "
				+ transaction.getExchangeRate().getCurrencyTo().getName()));
		add(new Label("total", "Total: " + totalSum()));
		add(new DownloadLink("download", createPdf(), "download.pdf"));

	}

	public File createPdf() {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("src/pdf/temp.pdf"));
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		document.open();
		try {
			Paragraph p1 = new Paragraph("Claim check",FontFactory.getFont(FontFactory.COURIER, 25, Font.BOLD));
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
				image = Image.getInstance("src/pdf/stamp.png");
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
		File file = new File("src/pdf/temp.pdf");
		return file;
	}

	private Double totalSum() {
		return transaction.getSumIn() * transaction.getExchangeRate().getConversion();
	}

	private void setTransaction() {
		transaction.setOperation(searchOperation());
		transaction.setDateOperation(new Date());
		transaction.setExchangeRate(searchExchangeRate());
		transaction.setUser(AuthorizedSession.get().getLoggedUser());
	}

	private Operation searchOperation() {
		if (exchangeRate.getCurrencyFrom().getName().equals(CurrencyName.BRB))
			return operationService.get((long) 1);
		else if (exchangeRate.getCurrencyTo().getName().equals(CurrencyName.BRB))
			return operationService.get((long) 2);
		else
			return operationService.get((long) 3);
	}

	private ExchangeRate searchExchangeRate() {
		exchangeRateFilter = new ExchangeRateFilter();
		exchangeRateFilter.setCurrencyFrom(exchangeRate.getCurrencyFrom().getName());
		exchangeRateFilter.setCurrencyTo(exchangeRate.getCurrencyTo().getName());
		exchangeRateFilter.setDateCourse(new Date());
		exchangeRateFilter.setFetchCredentials(true);
		return exchangeRateService.find(exchangeRateFilter).get(0);
	}
}
