package de.hawhh.informatik.sml.kino.fachwerte;

import de.hawhh.informatik.sml.kino.werkzeuge.barzahlung.Numeric;

/**
 * Ein Geldbetrag in Euro, gespeichert als ganze Euro- und ganze Cent-Beträge.
 * 
 */
public final class Geldbetrag
{

	private final int _euroAnteil;
	private final int _centAnteil;

	/**
	 * Wählt einen Geldbetrag aus.
	 * 
	 * @param eurocent
	 *            Der Betrag in ganzen Euro-Cent
	 * 
	 * @require eurocent >= 0;
	 */
	public static Geldbetrag get(int eurocent)
	{
		assert eurocent >= 0 : "Vorbedingung verletzt: eurocent >= 0";
		return new Geldbetrag(eurocent);
	}

	/**
	 * Wählt einen Geldbetrag aus.
	 * 
	 * @param betrag
	 *            Der Betrag im Format EE,CC
	 * 
	 * @require betrag != null;
	 */
	public static Geldbetrag get(String betrag)
	{
		assert betrag != null : "Vorbedingung verletzt: betrag!=null";

		int eurocent = 0;

		String cent = "";
		String euro = "";

		if (betrag.matches("[\\d]*[,][\\d]*"))
		{
			int kommaPlatz = 0;
			for (int i = 0; i < betrag.length(); ++i)
			{
				if (betrag.charAt(i) == ',')
				{
					kommaPlatz = i;
					break;
				}
			}
			if (kommaPlatz > 0)
				euro = betrag.substring(0, kommaPlatz);

			switch (betrag.length() - kommaPlatz - 1)
			{
			case (-1):
			case (0):
				break;
			case (1):
				cent = betrag.substring(kommaPlatz + 1) + "0";
				break;
			default:
				cent = betrag.substring(kommaPlatz + 1, kommaPlatz + 3);
			}
		}
		else if (betrag.matches("[\\d]*"))
		{
			euro = betrag;
		}

		if (Numeric.isNumeric(euro))
			if (Integer.parseInt(euro) >= 0)
				eurocent = Integer.parseInt(euro) * 100;

		if (Numeric.isNumeric(cent))
			if (Integer.parseInt(cent) >= 0)
				eurocent += Integer.parseInt(cent);

		return new Geldbetrag(eurocent);
	}

	private Geldbetrag(int eurocent)
	{

		_euroAnteil = eurocent / 100;
		_centAnteil = eurocent % 100;
	}

	/**
	 * Gibt den Eurobetrag ohne Cent zurück.
	 * 
	 * @return Den Eurobetrag ohne Cent.
	 */
	public int getEuroAnteil()
	{
		return _euroAnteil;
	}

	/**
	 * Gibt zurück ob der Geldbetrag größer 0 ist.
	 * 
	 */
	public boolean istGroesserNull()
	{
		int eurocent = _euroAnteil * 100 + _centAnteil;

		if (eurocent < 0)
			return false;

		return true;
	}

	/**
	 * Gibt den Centbetrag ohne Eurobetrag zurück.
	 */
	public int getCentAnteil()
	{
		return _centAnteil;
	}

	/**
	 * Liefert einen formatierten String des Geldbetrags in der Form "10,23"
	 * zurück.
	 * 
	 * @return eine String-Repräsentation.
	 */
	public String getFormatiertenString()
	{
		String result = "";
		if (!(_euroAnteil < 0))
		{
			result += _euroAnteil;
			if (!(_centAnteil < 0))
				result += "," + getFormatiertenCentAnteil();
		}
		return result;
	}

	/**
	 * Liefert einen zweistelligen Centbetrag zurück.
	 * 
	 * @return eine String-Repräsentation des Cent-Anteils.
	 */
	private String getFormatiertenCentAnteil()
	{
		String result = "";
		if (_centAnteil < 10)
		{
			result += "0";
		}
		result += _centAnteil;
		return result;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime + _centAnteil;
		result = prime * result + _euroAnteil;
		return result;
	}

	/**
	 * 
	 * Gibt einen neuen Geldbetrag wider der die Differenz ist.
	 * 
	 * @param g
	 *            = der abzuziehende Geldbetrag
	 * 
	 * @return der differnzierte Geldbetrag.
	 */
	public Geldbetrag minusGeldbetrag(Geldbetrag g)
	{
		int euro = 0;
		int cent = 0;

		euro = _euroAnteil - g.getEuroAnteil();
		cent = _centAnteil - g.getCentAnteil();
		int result = euro * 100 + cent;

		return new Geldbetrag(result);
	}

	/**
	 * 
	 * Addiert 2 Geldbeträge
	 * 
	 * @param g
	 *            der zu addierende Geldbettrag.
	 * @return der addierte Geldbetrag
	 * 
	 */
	public Geldbetrag plusGeldbetrag(Geldbetrag g)
	{
		int euro = 0;
		int cent = 0;

		euro = _euroAnteil + g.getEuroAnteil();
		cent = _centAnteil + g.getCentAnteil();
		int result = euro * 100 + cent;

		return new Geldbetrag(result);
	}

	/**
	 * Multipliziert einen Geldbetrag mit einem Faktor
	 * 
	 * @param faktor
	 *            der Faktor
	 * @return den multiplizierten Gelbetrag
	 * 
	 */
	public Geldbetrag multipliziereGeldbetrag(int faktor)
	{
		int euro = 0;
		int cent = 0;

		euro = _euroAnteil * faktor;
		cent = _centAnteil * faktor;
		int result = euro * 100 + cent;

		return new Geldbetrag(result);
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean result = false;
		if (obj instanceof Geldbetrag)
		{
			Geldbetrag other = (Geldbetrag) obj;
			result = (_centAnteil == other._centAnteil)
					&& (_euroAnteil == other._euroAnteil);
		}
		return result;
	}

	/**
	 * Gibt diesen Geldbetrag in der Form "10,21" zurück.
	 */
	@Override
	public String toString()
	{
		return getFormatiertenString();
	}
}
