package be.lindacare.market.aphonso.config;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {

	private static final String DATE_TIME_FORMAT = "dd-MMM-yy HH:mm:ss";
	
	public static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern(DATE_TIME_FORMAT).toFormatter(Locale.ENGLISH);
}
