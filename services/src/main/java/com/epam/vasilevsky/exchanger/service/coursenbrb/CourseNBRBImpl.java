package com.epam.vasilevsky.exchanger.service.coursenbrb;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.vasilevsky.exchanger.service.CourseNBRB;

public class CourseNBRBImpl implements CourseNBRB {
	private static Logger LOGGER = LoggerFactory.getLogger(CourseNBRBImpl.class);

	private final String DATE_FORMAT = "MM/dd/yyyy";
	private final String TEXT_START = "<Rate>";
	private final String TEXT_END = "</Rate>";
	private final int NUMBER_DIGITS_COURSE = 5;
	private final String URL = "http://www.nbrb.by/Services/XmlExRatesDyn.aspx?curId=%s&fromDate=%s&toDate=%s";

	private String course;
	private String code;

	public String getCourse(String code) {
		this.code = code;
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
			LOGGER.info("Currency get ok!");
			return course;

		} catch (StringIndexOutOfBoundsException e) {
			LOGGER.warn("No correct code currency!");
			return null;
		} catch (Exception e) {
			LOGGER.warn("No course!");
			return null;
		}
	}

	public String getDate() {
		Date date = new Date();
		date.setHours(date.getHours()+1);
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		String dateStr = String.valueOf(dateFormat.format(date));
		LOGGER.info("Date get ok!");
		return dateStr;
	}

	private InputStream getInputStringFromUrl() {
		try {
			URL url = new URL(String.format(URL, code, getDate(), getDate()));
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			LOGGER.info("Get InputStream ok!");
			return in;
		} catch (MalformedURLException e) {
			LOGGER.warn("No connection");
			return null;
		} catch (IOException e) {
			LOGGER.warn("No connection!");
			return null;
		}
	}
}
