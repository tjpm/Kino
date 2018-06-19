package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

public class Numeric
{

	public static boolean isNumeric(String s)
	{
		try
		{
			Integer.parseInt(s);
			
		} catch (Exception e)
		{
			return false;
		}
		
		
		return true;
	}
	public static boolean isNumericDouble(String s)
	{
		try
		{
			Double.parseDouble(s);
			
		} catch (Exception e)
		{
			return false;
		}
		
		
		return true;
	}
}
