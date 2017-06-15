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
	private ChoiceBox<String> cbox_paciente_estado;
	//Variables Garganta
	@FXML
	private ChoiceBox<String> cbox_garganta_estado;
	@FXML
	private ChoiceBox<String> cbox_garganta_dolor;
	@FXML
	private ChoiceBox<String> cbox_garganta_amigdalas;
	@FXML
	private ChoiceBox<String> cbox_garganta_estudioMedico;
	@FXML
	private ChoiceBox<String> cbox_garganta_estreptococo;
	@FXML
	private ChoiceBox<String> cbox_garganta_cultivoFaringeo;
	@FXML
	private ChoiceBox<String> cbox_garganta_examinacion_adicional;
	@FXML
	private ChoiceBox<String> cbox_garganta_observaciones;
	@FXML
	//Variables Oido
	private ChoiceBox<String> cbox_oido_examinacion;
	@FXML
	private ChoiceBox<String> cbox_oido_estado;
	//Variables Nariz
	@FXML
	private ChoiceBox<String> cbox_nariz_examinacion;
	@FXML
	private ChoiceBox<String> cbox_nariz_estado;
	@FXML
	private Tab tab_2;
	@FXML
	private TabPane tabPane;
	@FXML
	
	public void initialize(){
		
		cbox_paciente_estado.getItems().removeAll(cbox_paciente_estado.getItems());
		cbox_paciente_estado.getItems().addAll("Aliviado", "Adolorido", "Curado");
		cbox_paciente_estado.getSelectionModel().select("No Irritada");
		
		initializeGarganta();
		initializeNariz();
		initializeOido();
	}
	public void initializeGarganta() 
	{ 
		//Estado
		cbox_garganta_estado.getItems().removeAll(cbox_garganta_estado.getItems());
		cbox_garganta_estado.getItems().addAll("Irritada", "No Irritada");
		cbox_garganta_estado.getSelectionModel().select("No Irritada");
	    
	    //Dolor
		cbox_garganta_dolor.getItems().removeAll(cbox_garganta_dolor.getItems());
		cbox_garganta_dolor.getItems().addAll("Leve", "Fuerte", "Ninguno");
		cbox_garganta_dolor.getSelectionModel().select("Leve");
		
	    //Amigdalas
		cbox_garganta_amigdalas.getItems().removeAll(cbox_garganta_amigdalas.getItems());
		cbox_garganta_amigdalas.getItems().addAll("Rojiza", "Inflamada", "Normal");
		cbox_garganta_amigdalas.getSelectionModel().select("Normal");
		
	    //Estudio Medico
		cbox_garganta_estudioMedico.getItems().removeAll(cbox_garganta_estudioMedico.getItems());
		cbox_garganta_estudioMedico.getItems().addAll("Prueba Estreptococica", "Cultivo Faringeo");
		cbox_garganta_estudioMedico.getSelectionModel().select("Cultivo Faringeo");
		
		//Estreptococo
		cbox_garganta_estreptococo.getItems().removeAll(cbox_garganta_estreptococo.getItems());
		cbox_garganta_estreptococo.getItems().addAll("Positivo", "Negativo");
		cbox_garganta_estreptococo.getSelectionModel().select("Negativo");
		
	    //Cultivo Faringeo
		cbox_garganta_cultivoFaringeo.getItems().removeAll(cbox_garganta_cultivoFaringeo.getItems());
		cbox_garganta_cultivoFaringeo.getItems().addAll("Positivo", "Negativo");
		cbox_garganta_cultivoFaringeo.getSelectionModel().select("Negativo");
		
	    //Examen Adicional 
		cbox_garganta_examinacion_adicional.getItems().removeAll(cbox_garganta_examinacion_adicional.getItems());
		cbox_garganta_examinacion_adicional.getItems().addAll("Oido", "Nariz");
		cbox_garganta_examinacion_adicional.getSelectionModel().select("Nariz");
	
		//Observaciones
		//cbox_garganta_observaciones.getItems().removeAll(cbox_garganta_observaciones.getItems());
		//cbox_garganta_observaciones.getItems().addAll("Leve", "Fuerte", "Ninguno");
		//cbox_garganta_observaciones.getSelectionModel().select("Leve");
	}
	
	public void initializeOido() 
	{ 
		//Examinacion
		cbox_oido_examinacion.getItems().removeAll(cbox_oido_examinacion.getItems());
		cbox_oido_examinacion.getItems().addAll("Positivo", "Negativo");
		cbox_oido_examinacion.getSelectionModel().select("Positivo");
		
		//Estado
		cbox_oido_estado.getItems().removeAll(cbox_oido_estado.getItems());
		cbox_oido_estado.getItems().addAll("Infeccion", "Sin Infeccion");
		cbox_oido_estado.getSelectionModel().select("Sin Infeccion");
	}
	
	public void initializeNariz() 
	{ 
		//Examinacion
		cbox_nariz_examinacion.getItems().removeAll(cbox_nariz_examinacion	.getItems());
		cbox_nariz_examinacion.getItems().addAll("Positivo", "Negativo");
		cbox_nariz_examinacion.getSelectionModel().select("Positivo");
		
		//Estado
		cbox_nariz_estado.getItems().removeAll(cbox_nariz_estado.getItems());
		cbox_nariz_estado.getItems().addAll("Infeccion", "Sin Infeccion");
		cbox_nariz_estado.getSelectionModel().select("Sin Infeccion");
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

		String valor1 = null;
		String valor2 = null;
		String evalString = "(assert(hecho(slot1 " +valor1+ ") (slot2 " + valor2 + ")))";
		//clips.eval("(assert (response (var_a CLIPS_test)))");
		clips.eval(evalString);
		clips.run();
		
		//Busca todos los hechos "diagnostico"
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
