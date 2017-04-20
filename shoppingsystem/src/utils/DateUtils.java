 package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	//将字符串转换成日期类
	public static Date stringToDate(String format,String strDate) {
		DateFormat df = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	//将日期类转换成字符串
	public static String dateToString(String format,Date date) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

}
