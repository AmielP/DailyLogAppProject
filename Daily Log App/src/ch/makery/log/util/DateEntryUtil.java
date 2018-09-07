//package ch.makery.log.util;
//
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//
//public class DateEntryUtil
//{
//	private static final String DATE_PATTERN_VERBOSE = "MM-dd-yyyy";
//	
//	private static final DateTimeFormatter DATE_FORMATTER_VERBOSE = DateTimeFormatter.ofPattern(DATE_PATTERN_VERBOSE);
//	
//	public static String format(ZonedDateTime localDateTime)
//	{
//		if(localDateTime == null)
//		{
//			return null;
//		}
//		return DATE_FORMATTER_VERBOSE.format(localDateTime);
//	}
//	
//	public static ZonedDateTime parse(String dateString)
//	{
//		try
//		{
//			return DATE_FORMATTER_VERBOSE.parse(dateString, ZonedDateTime::from);
//		}
//		catch(DateTimeParseException e)
//		{
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	public static boolean validDate(String dateString)
//	{
//		return DateVerboseUtil.parse(dateString) != null;
//	}
//}
