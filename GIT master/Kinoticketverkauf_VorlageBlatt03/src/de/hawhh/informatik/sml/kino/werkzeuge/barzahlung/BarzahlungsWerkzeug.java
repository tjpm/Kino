package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;


import javafx.scene.input.KeyCode;

public class BarzahlungsWerkzeug 
{
	private BarzahlungsWerkzeugUI _ui;
	private int _preis;
	private boolean _wurdeVekauft;


	public BarzahlungsWerkzeug(int preis)
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

					if (Numeric.isNumeric(neuerWert))
					{
						_ui.setRueckGeld(Integer
								.toString(Integer.parseInt(neuerWert) - _preis));
						if((Integer.parseInt(neuerWert) - _preis) < 0)
						{
							_ui.getOKButton().setDisable(true);
						}
						else
						{
							_ui.getOKButton().setDisable(false);
						}
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

			_ui.schließeFenster();
		}

	}

	private void abbruchButtonWurdeGedrueckt()
	{
	
		_wurdeVekauft = false;

		_ui.schließeFenster();
		

	}
}
