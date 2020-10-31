package com.toyrentalproject.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	public static Date calculateDate(int week) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date sDate = new java.util.Date();	
		int nOfDays = 7*week;
		Calendar c = Calendar.getInstance();
		c.setTime(sDate);
		c.add(Calendar.DAY_OF_WEEK, nOfDays);
		String resultDate = sdf.format(c.getTime());
		return new Date(c.getTimeInMillis());
	}
}
