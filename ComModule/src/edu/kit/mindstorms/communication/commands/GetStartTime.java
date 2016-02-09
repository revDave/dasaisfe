package edu.kit.mindstorms.communication.commands;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetStartTime extends AbstractCommand<Calendar> {

	private static final String TIME_FORMAT = "HH:mm:ss";
	private static final SimpleDateFormat DATA_FORMAT = new SimpleDateFormat(TIME_FORMAT);

	public GetStartTime(URL commandUrl) {
		super(commandUrl);
	}

	@Override
	public Calendar execute() throws IOException {
		String response = doGet();

		try {
			Date date = DATA_FORMAT.parse(response);
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			throw new IOException("Response '" + response + "' did not match " + TIME_FORMAT);
		}
	}

}
