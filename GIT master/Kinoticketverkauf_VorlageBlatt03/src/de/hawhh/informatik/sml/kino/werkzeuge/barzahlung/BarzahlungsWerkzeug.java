package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import de.hawhh.informatik.sml.kino.fachwerte.Geldbetrag;
import javafx.scene.input.KeyCode;

public class BarzahlungsWerkzeug
{
	private BarzahlungsWerkzeugUI _ui;
	private Geldbetrag _preis;
	private boolean _wurdeVekauft;

	public BarzahlungsWerkzeug(Geldbetrag preis)
	{
		_wurdeVekauft = false;
		_preis = preis;
		_ui = new BarzahlungsWerkzeugUI(_preis);

		registriereUIAktionen();
		_ui.zeigeFenster();
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

					_ui.setRueckGeld(Geldbetrag.get(neuerWert)
							.minusGeldbetrag(_preis).toString());
					if (Geldbetrag.get(neuerWert).minusGeldbetrag(_preis)
							.istGroesserNull())
					{
						_ui.getOKButton().setDisable(false);
					}
					else
					{
						_ui.getOKButton().setDisable(true);
					}

				});

	}

	private boolean genugBargeld()
	{
		boolean result = false;
		Geldbetrag barGeld = Geldbetrag.get(_ui.getBargeld().getText())
				.minusGeldbetrag(_preis);

		if (barGeld.getEuroAnteil() >= 0 && barGeld.getCentAnteil() >= 0)
		{
			result = true;
		}
		else
		{
			result = false;
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

			_ui.schließeFenster();
		}

	}

	private void abbruchButtonWurdeGedrueckt()
	{

		_wurdeVekauft = false;

		_ui.schließeFenster();

	}
}
