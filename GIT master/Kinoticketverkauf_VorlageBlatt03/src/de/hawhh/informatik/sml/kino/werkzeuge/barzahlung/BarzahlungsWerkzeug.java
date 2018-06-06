package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import de.hawhh.informatik.sml.kino.werkzeuge.ObservableSubwerkzeug;
import de.hawhh.informatik.sml.kino.werkzeuge.SubwerkzeugObserver;
import javafx.scene.input.KeyCode;

public class BarzahlungsWerkzeug extends ObservableSubwerkzeug
{
	private BarzahlungsWerkzeugUI _ui;
	private int _preis;
	private boolean _wurdeVekauft;


	public BarzahlungsWerkzeug(int preis)
	{
		_wurdeVekauft = false;
		_preis = preis;
		_ui = new BarzahlungsWerkzeugUI(_preis);
		_ui.zeigeFenster();
		registriereUIAktionen();
	}

	private void registriereUIAktionen()
	{
		_ui.getOKButton().setOnAction(e ->
		{
			
			okButtonWurdeGedrueckt();

		});

		_ui.getAbbruchButton().setOnAction(e ->
		{
			abbruchButtonWurdeGedrueckt();
		});

		_ui.getBargeld().setOnKeyPressed(key ->
		{
			if (key.getCode() == KeyCode.ENTER)
			{
				_ui.getOKButton().fire();
			}
		});

		_ui.getBargeld().textProperty()
				.addListener((observable, alterWert, neuerWert) ->
				{

					if (Numeric.isNumeric(neuerWert))
					{
						_ui.setRueckGeld(Integer
								.toString(Integer.parseInt(neuerWert) - _preis));
					}
					else
					{
						_ui.setRueckGeld("Kein richtiger Betrag");
					}

				});

	}

	private boolean genugBargeld()
	{
		boolean result = false;
		if (Numeric.isNumeric(_ui.getBargeld().getText()))
		{
			int barGeld = Integer.parseInt(_ui.getBargeld().getText());
			if (_preis <= barGeld)
			{
				result = true;
			}
			else
			{
				result = false;
			}

		}

		return result;

	}

	public boolean getWurdeVerkauft()
	{
		return _wurdeVekauft;
	}
	
	private void okButtonWurdeGedrueckt()
	{
		if (genugBargeld())
		{
			_wurdeVekauft = true;
			informiereUeberAenderung();
			_ui.schließeFenster();
		}

	}

	private void abbruchButtonWurdeGedrueckt()
	{
	
		_wurdeVekauft = false;
		informiereUeberAenderung();
		_ui.schließeFenster();
		

	}
}
