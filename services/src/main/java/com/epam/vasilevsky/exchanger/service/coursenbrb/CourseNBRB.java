package com.epam.vasilevsky.exchanger.service.coursenbrb;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.epam.vasilevsky.exchanger.datamodel.CurrencyName;

public class CourseNBRB {
	private final String DATE_FORMAT = "MM/dd/yyyy";
	private final String TEXT_START = "<Rate>";
	private final String TEXT_END = "</Rate>";
	private final int NUMBER_DIGITS_COURSE = 5;
	private final String URL = "http://www.nbrb.by/Services/XmlExRatesDyn.aspx?curId=%s&fromDate=%s&toDate=%s";

	private String course;
	private String code;

	public String getCourse(String code) {
		this.code=code;
		try {
			InputStream in = getInputStringFromUrl();
			int i = -1;
			String buffer = "";
			try {
				while ((i = in.read()) != -1) {
					buffer = buffer + String.valueOf((char) i);
				}
			} catch (IOException e) {
				return null;
			}
			int indexStart = buffer.indexOf(TEXT_START) + 1 + NUMBER_DIGITS_COURSE;
			int indexEnd = buffer.indexOf(TEXT_END);
			course = buffer.substring(indexStart, indexEnd);

			return course;
			
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("No correct code currency");
			return null;
		}
	}

	public String getDate() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		String dateStr = String.valueOf(dateFormat.format(date));
		return dateStr;
	}

	private InputStream getInputStringFromUrl() {
		try {
			URL url = new URL(String.format(URL, code, getDate(), getDate()));
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			return in;
		} catch (MalformedURLException e) {
			System.out.println("No connection");
			return null;
		} catch (IOException e) {
			System.out.println("No connection!");
			return null;
		}

	}
}
