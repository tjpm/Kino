package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

public class BarzahlungsWerkzeug
{
	private BarzahlungsWerkzeugUI _ui;
	
	public BarzahlungsWerkzeug(int preis)
	{
		_ui = new BarzahlungsWerkzeugUI(preis);
		_ui.zeigeFenster();
	}
	
	
}
