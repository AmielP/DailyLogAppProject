package ch.makery.log.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.makery.log.services.IMatchContent;

public class MatchLogContent implements IMatchContent
{
	@Override
	public String matchLogContent(byte[] buffer, String start)
	{
		Pattern pattern = Pattern.compile(start);
		Matcher matcher = pattern.matcher(new String(buffer));
		while(matcher.find())
		{
			String matchedString = ((new String(buffer)).substring(matcher.end()).trim());
			return matchedString;
		}
		return null;
	}
	
	@Override
	public String matchLogContent(byte[] buffer, String start, String end)
	{
		String regexString = Pattern.quote(start) + "(.*?)" + Pattern.quote(end);
		Pattern pattern = Pattern.compile(regexString, Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(new String(buffer));
		while(matcher.find())
		{
			return matcher.group(1);
		}
		return null;
	}
}
