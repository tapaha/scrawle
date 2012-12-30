package org.tapaha.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateCalculator {
	 public static String getDate(String type) {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(type); 
		
		return sdf.format(dt).toString();
	}
	public static String getYesterdayDate(String date) {
		Calendar cal = new GregorianCalendar();
		String arr[] = date.split("-");
		
		cal.set(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]) -1, Integer.parseInt(arr[2]));
	    cal.add(Calendar.DATE, -1);
	    
	    return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
	}
	public static String getTomorrowDate(String date) {
		Calendar cal = new GregorianCalendar();
		String arr[] = date.split("-");
		
		cal.set(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]) -1, Integer.parseInt(arr[2]));
	    cal.add(Calendar.DATE, 1);
	    
	    return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
	}
}
