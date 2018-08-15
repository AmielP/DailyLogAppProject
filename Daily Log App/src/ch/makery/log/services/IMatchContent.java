package ch.makery.log.services;

public interface IMatchContent 
{
	String matchLogContent(byte[] buffer, String content);
	String matchLogContent(byte[] buffer, String firstContent, String secondContent);
}
