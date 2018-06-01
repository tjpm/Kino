package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import de.hawhh.informatik.sml.kino.werkzeuge.ObservableSubwerkzeug;
import de.hawhh.informatik.sml.kino.werkzeuge.SubwerkzeugObserver;
import javafx.scene.input.KeyCode;

public class BarzahlungsWerkzeug extends ObservableSubwerkzeug
{
	private BarzahlungsWerkzeugUI _ui;
	private int _preis;

	public BarzahlungsWerkzeug(int preis, SubwerkzeugObserver beobachter)
	{
		_preis = preis;
		_ui = new BarzahlungsWerkzeugUI(_preis);
		_ui.zeigeFenster();
		registriereBeobachter(beobachter);
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
			_ui.schließeFenster();
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

	private void okButtonWurdeGedrueckt()
	{
		if (genugBargeld())
		{
			informiereUeberAenderung();
			_ui.schließeFenster();
		}
		else
		{

		}

	}

}
