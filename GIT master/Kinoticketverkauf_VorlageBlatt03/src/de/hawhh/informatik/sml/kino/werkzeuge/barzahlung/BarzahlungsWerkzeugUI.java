package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BarzahlungsWerkzeugUI
{
	private Pane _pane;
	private Label _preisBeschriftung;
	private Label _preis;
	private Label _betragBeschriftung;
	private Label _rueckgeldBeschriftung;
	private Label _rueckgeld;
	private TextField _eingabeFeld;
	private Button _okButton;
	private Button _abbruchButton;
	private Scene _scene;
	private Stage _primaryStage;

	public BarzahlungsWerkzeugUI(int preis)
	{
		_pane = erstellePanel(preis);
		_scene = new Scene(_pane,500,500);
		_primaryStage = new Stage();
		_primaryStage.setScene(_scene);
	}

	private Pane erstellePanel(int preis)
	{
		Pane pane = new GridPane();

		
		_preisBeschriftung = new Label("Preis: ");
		_preis = new Label(Integer.toString(preis));
		_betragBeschriftung = new Label("Betrag: ");
		_rueckgeldBeschriftung = new Label("Rückgeld: ");
		_rueckgeld = new Label("XXXX");

		_okButton = new Button("OK");
		_abbruchButton = new Button("abbrechen");

		_eingabeFeld = new TextField();

		GridPane.setConstraints(_preisBeschriftung, 0, 0);
		GridPane.setConstraints(_preis, 1, 0);
		GridPane.setConstraints(_betragBeschriftung, 0, 1);
		GridPane.setConstraints(_eingabeFeld, 1, 1);
		GridPane.setConstraints(_rueckgeldBeschriftung, 0, 2);
		GridPane.setConstraints(_rueckgeld, 1, 2);
		GridPane.setConstraints(_okButton, 0, 5);
		GridPane.setConstraints(_abbruchButton, 2, 5);

		//_pane.setPadding(new Insets(10, 10, 10, 10));

		pane.getChildren().addAll(_preisBeschriftung, _preis,
				_betragBeschriftung, _rueckgeldBeschriftung, _rueckgeld,
				_okButton, _abbruchButton,_eingabeFeld);

		
		
		
		return pane;

	}
	
	public void zeigeFenster()
	{
		_primaryStage.show();
	}
	

}
