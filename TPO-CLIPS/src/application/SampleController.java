package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import net.sf.clipsrules.jni.*;

public class SampleController 
{
	private Environment clips;
	@FXML
	private TextArea diagnostico;
	@FXML
	private ChoiceBox<String> cbox_paciente_estado;
	@FXML
	
	//Variables Garganta
	private ChoiceBox<String> cbox_garganta_dolor, 
	cbox_garganta_amigdalas,
	cbox_garganta_estreptococo,
	cbox_garganta_cultivoFaringeo;
	@FXML

	//Variables Oido
	private ChoiceBox<String> cbox_oido_examinacion;
	@FXML
	//Variables Nariz
	private ChoiceBox<String> cbox_nariz_examinacion;
	@FXML	
	
	//Variables del Diagnostico
	private TextField tf_garganta_estado, 
	tf_estudioMedico, 
	tf_observaciones, 
	tf_examenAdicional, 
	tf_oido_estado, 
	tf_estado_nariz,
	tf_enfermedad,
	tf_fecha,
	tf_zonaAfectada;
	@FXML
	
	//Variables del Tratamiento
	private TextField tf_tratamiento_tiempo, 
	tf_tratamiento_estado, 
	tf_tratamiento_recomendaciones, 
	tf_tratamiento_visitas, 
	tf_tratamiento_observaciones, 
	tf_tratamiento_derivaciones;
	@FXML

	//Variables de la Receta
	private TextField tf_receta_antibiotico,
	tf_receta_liquido,
	tf_receta_analgesico;
	
	@SuppressWarnings("unused")
	private Tab tab_paciente,
	tab_garganta,
	tab_oido,
	tab_nariz,
	tab_diagnostico,
	tab_receta;
	@FXML
	
	private TabPane tabPane;
	@FXML
	
	public void initialize(){
		
		initializeGarganta();
		initializeNariz();
		initializeOido();
		initializeDiagnosticoFalso();
	}
	
	public void initializeGarganta() 
	{ 
		//Estado es OUPUT
	    
	    //Dolor
		cbox_garganta_dolor.getItems().removeAll(cbox_garganta_dolor.getItems());
		cbox_garganta_dolor.getItems().addAll("Leve", "Fuerte", "Ninguno");
		cbox_garganta_dolor.getSelectionModel().select("Leve");
		
	    //Amigdalas
		cbox_garganta_amigdalas.getItems().removeAll(cbox_garganta_amigdalas.getItems());
		cbox_garganta_amigdalas.getItems().addAll("Rojiza", "Inflamada", "Normal");
		cbox_garganta_amigdalas.getSelectionModel().select("Normal");
		
	    //Estudio Medico es OUTPUT

		
		//Estreptococo
		cbox_garganta_estreptococo.getItems().removeAll(cbox_garganta_estreptococo.getItems());
		cbox_garganta_estreptococo.getItems().addAll("Positivo", "Negativo");
		cbox_garganta_estreptococo.getSelectionModel().select("Negativo");
		
	    //Cultivo Faringeo
		cbox_garganta_cultivoFaringeo.getItems().removeAll(cbox_garganta_cultivoFaringeo.getItems());
		cbox_garganta_cultivoFaringeo.getItems().addAll("Positivo", "Negativo");
		cbox_garganta_cultivoFaringeo.getSelectionModel().select("Negativo");
		
	    //Examen Adicional es OUTPUT

		//Observaciones es OUTPUT

	}
	
	public void initializeOido() 
	{ 
		//Examinacion
		cbox_oido_examinacion.getItems().removeAll(cbox_oido_examinacion.getItems());
		cbox_oido_examinacion.getItems().addAll("Positivo", "Negativo");
		cbox_oido_examinacion.getSelectionModel().select("Positivo");
		
		//Estado ES OUTPUT

	}
	
	public void initializeNariz() 
	{ 
		//Examinacion
		cbox_nariz_examinacion.getItems().removeAll(cbox_nariz_examinacion	.getItems());
		cbox_nariz_examinacion.getItems().addAll("Positivo", "Negativo");
		cbox_nariz_examinacion.getSelectionModel().select("Positivo");
		
		//Estado ES OUTPUT

	}
	public void initializeDiagnosticoFalso(){
		tf_garganta_estado.setText("Irritada");
		tf_estudioMedico.setText("Cultivo Faringeo");
		tf_observaciones.setText("Volver en 4 dias");
		tf_examenAdicional.setText("Oido");
		tf_oido_estado.setText("Sin Infeccion");
		tf_estado_nariz.setText("Sin Infeccion");
		tf_enfermedad.setText("Amigadlitis");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		tf_fecha.setText(dtf.format(now));
	}


	@FXML
	public void onSiguienteClickedPaciente() 
	{ 
	    tabPane.getSelectionModel().select(tab_garganta);
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

		 
		//diagnostico.setText(output+"\n"+s);
		diagnostico.setText(output+"El Diagnostico es Amigdalitis\n"+s);

		return output;

	}
}
