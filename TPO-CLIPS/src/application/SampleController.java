package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.sf.clipsrules.jni.*;

public class SampleController 
{
	private Environment clips;
	@FXML
	private TextArea diagnostico;
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
	@FXML
	public String runCLIPS()
	{
		String programaCLIPS = "./programa.clp";
		System.out.println(programaCLIPS);
		clips = new Environment();
		
		String output = "CLIPS version " + Environment.getCLIPSVersion()+"\n";
		output += "Loaded <"+programaCLIPS+">";
		
		clips.load(programaCLIPS);
		clips.reset();
		clips.run();
		
		clips.eval("(assert (response (var_a CLIPS_test)))");
				
		String evalstr= "(find-all-facts ((?J response)) TRUE)";
		MultifieldValue pv = (MultifieldValue)   clips.eval(evalstr);
		FactAddressValue fv = (FactAddressValue) pv.get(0);
		String s = null;
		try { 
			s = fv.getFactSlot("var_a").toString();
		} catch (Exception e1) { 
			e1.printStackTrace();
		}
		String ResultadoFinal = s;

		 
		diagnostico.setText(output+"\n"+s);
		return output;

	}
}
