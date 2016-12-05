package ie.cit.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;


public class CalendarFormatter  implements Formatter<Calendar> 
{
	final String defaultDateFormat = "dd.MM.yyyy";

	@Override
	public String print(Calendar object, Locale locale) {
	    return new SimpleDateFormat(defaultDateFormat).format(object.getTime());
	}

	@Override
	public Calendar parse(String text, Locale arg1) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDateFormat);
        Date date = sdf.parse(text);
        return sdf.getCalendar();
	}
	
	
}
