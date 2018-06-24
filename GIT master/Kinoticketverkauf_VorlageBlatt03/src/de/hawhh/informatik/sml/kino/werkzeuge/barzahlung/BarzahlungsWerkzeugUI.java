package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import de.hawhh.informatik.sml.kino.fachwerte.Geldbetrag;
import de.hawhh.informatik.sml.kino.werkzeuge.ObservableSubwerkzeug;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
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

	public BarzahlungsWerkzeugUI(Geldbetrag preis)// TODO Geldbetrag
	{
		_pane = erstellePanel(preis);
		_scene = new Scene(_pane,200,200);
		stageErstellen();
	}

	private Pane erstellePanel(Geldbetrag preis)// TODO Geldbetrag
	{
		GridPane pane = new GridPane();

		
		_preisBeschriftung = new Label("Preis: ");
		_preis = new Label(preis.toString());
		_betragBeschriftung = new Label("Betrag: ");
		_rueckgeldBeschriftung = new Label("Rückgeld: ");
		_rueckgeld = new Label("XXXX");
		
		_okButton = new Button("OK");
		_okButton.setDisable(true);
		_abbruchButton = new Button("abbrechen");

		_eingabeFeld = new TextField();
		_eingabeFeld.setMaxWidth(75);
		
		

		
		GridPane.setConstraints(_preisBeschriftung, 0, 0);
		GridPane.setConstraints(_preis, 1, 0);
		GridPane.setConstraints(_betragBeschriftung, 0, 1);
		GridPane.setConstraints(_eingabeFeld, 1, 1);
		GridPane.setConstraints(_rueckgeldBeschriftung, 0, 2);
		GridPane.setConstraints(_rueckgeld, 1, 2);
		GridPane.setConstraints(_okButton, 0, 5);
		GridPane.setConstraints(_abbruchButton, 1, 5);

		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.setHgap(8);
		pane.setVgap(10);
		
		pane.getChildren().addAll(_eingabeFeld, _preisBeschriftung, _preis,
				_betragBeschriftung, _rueckgeldBeschriftung, _rueckgeld,
				_okButton, _abbruchButton);

		
		
		
		return pane;

	}
	
	private void stageErstellen()
	{
		_primaryStage = new Stage();
		_primaryStage.setScene(_scene);
		_primaryStage.initModality(Modality.APPLICATION_MODAL);
	}
	
	public TextField getBargeld()
	{
		return _eingabeFeld;
	}
	
	public Button getOKButton()
	{
		return _okButton;
	}
	
	public Button getAbbruchButton()
	{
		return _abbruchButton;
	}
	
	public void setRueckGeld(String rueckGeld)
	{
		_rueckgeld.setText(rueckGeld);
	}
	
	public void schließeFenster()
	{
		_primaryStage.close();;
	}
	
	public void zeigeFenster()
	{
		_primaryStage.showAndWait();
	}
	

}
