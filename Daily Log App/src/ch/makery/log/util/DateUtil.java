package ch.makery.log.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import ch.makery.log.model.Log;
import ch.makery.log.services.ILogTemplate;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class DateUtil
{
	private static final String DATE_PATTERN = "EEE, MM-dd-yyyy h:mm:ss a zzz";
	
	private static final String DATE_PATTERN_VERBOSE = "EEE, MMM dd, HH:mm:ss yyyy - zzzz";
	
	private static final String DATE_PATTERN_ENTRY = "MM-dd-yyyy";
	
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(getDatePattern());
	
	private static final DateTimeFormatter DATE_FORMATTER_VERBOSE = DateTimeFormatter.ofPattern(getDatePatternVerbose());
	
	private static final DateTimeFormatter DATE_FORMATTER_ENTRY = DateTimeFormatter.ofPattern(getDatePatternEntry());
	
	private static Date update;
	
	private static Timeline timeline;
	
	private static DateFormat dateFormat;
	
	private static ZonedDateTime zdt;
	
	//Delete this later
//	public static void updateTime(Label dateLabel)
//	{
//		updateTime(dateLabel, DATE_PATTERN_VERBOSE, DATE_FORMATTER_VERBOSE);
//	}
	
	public static void updateTime(Label dateLabel, String datePatternType, DateTimeFormatter dateTimeFormatter)
	{
//		dateLabel.setText("Today is " + DateUtil.format(log.getDate()));
//		DateTimeFormatter dateFormat = DateUtil.getDateFormatter();
		dateFormat = new SimpleDateFormat(datePatternType);
		
		timeline = new Timeline(new KeyFrame(Duration.seconds(0), new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent actionEvent)
					{
						update = new Date();
						zdt = ZonedDateTime.ofInstant(update.toInstant(), ZoneId.systemDefault());
						dateLabel.setText("Today is " + format(zdt, dateTimeFormatter));
					}
				}
		), new KeyFrame(Duration.seconds(1)));
	}
	
	public static void displayTimer()
	{
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	public static String format(ZonedDateTime localDateTime, DateTimeFormatter dateTimeFormatter)
	{
		if(localDateTime == null)
		{
			return null;
		}
		return dateTimeFormatter.format(localDateTime);
	}
	
	public static ZonedDateTime parse(String dateString, DateTimeFormatter dateTimeFormatter)
	{
		try
		{
			return dateTimeFormatter.parse(dateString, ZonedDateTime::from);
		}
		catch(DateTimeParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean validDate(String dateString, DateTimeFormatter dateTimeFormatter)
	{
		return DateUtil.parse(dateString, dateTimeFormatter) != null;
	}

	public static DateTimeFormatter getDateFormatter() 
	{
		return DATE_FORMATTER;
	}
	
	public static DateTimeFormatter getDateFormatterVerbose()
	{
		return DATE_FORMATTER_VERBOSE;
	}
	
	public static DateTimeFormatter getDateFormatterEntry() 
	{
		return DATE_FORMATTER_ENTRY;
	}

	public static String getDatePattern() 
	{
		return DATE_PATTERN;
	}
	
	public static String getDatePatternVerbose()
	{
		return DATE_PATTERN_VERBOSE;
	}
	
	public static String getDatePatternEntry()
	{
		return DATE_PATTERN_ENTRY;
	}
	
	public static DateFormat getDateFormat()
	{
		return dateFormat;
	}
	
	public static ZonedDateTime getZonedDateTime()
	{
		return zdt;
	}
}
