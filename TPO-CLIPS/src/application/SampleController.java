package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SampleController 
{
	@FXML
	private ChoiceBox<String> cbox_a;
	@FXML
	private Tab tab_2;
	@FXML
	private TabPane tabPane;
	@FXML
	public void initialize() 
	{ 
	    cbox_a.getItems().removeAll(cbox_a.getItems());
	    cbox_a.getItems().addAll("Opcion A", "Opcion B", "Opcion C");
	    cbox_a.getSelectionModel().select("Opcion A");
	}
	@FXML
	public void onSiguienteClicked() 
	{ 
	    tabPane.getSelectionModel().select(tab_2);
	}
}
