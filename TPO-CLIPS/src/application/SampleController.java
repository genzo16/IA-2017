package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import controller.Core;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.sf.clipsrules.jni.*;

public class SampleController 
{
	private Environment clips;
	@FXML
	private TextArea diagnostico;
	@FXML
	private TextField paciente_dni;
	@FXML
	private TextField paciente_nombre;
	@FXML
	private TextField paciente_apellido;
	@FXML
	private ChoiceBox<String> cbox_paciente_malestar;
	@FXML
	
	private TextArea ta_diagnostico;
	@FXML
	
	private ChoiceBox<String> cbox_sintoma;
	@FXML
	
	//Variables Garganta
	private ChoiceBox<String> cbox_garganta_dolor, 
	cbox_amigdalas_tamanio,
	cbox_amigdalas_color,
	cbox_amigdalas_placas,
	cbox_garganta_estreptococo,
	cbox_garganta_cultivoFaringeo;
	@FXML

	//Variables Oido
	private ChoiceBox<String> cbox_oido_examinacion,
	cbox_oido_cultivoFarigeo;
	@FXML
	//Variables Nariz
	private ChoiceBox<String> cbox_nariz_examinacion,
	cbox_nariz_cultivoFaringeo;
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
		
		initializeSintomas();
		initializeNariz();
		initializeOido();
		initializeCultivoFaringeo();
		//initializeDiagnosticoFalso();
	}
	public void initializeCultivoFaringeo() {
	    //Garganta
		cbox_garganta_cultivoFaringeo.getItems().removeAll(cbox_garganta_cultivoFaringeo.getItems());
		cbox_garganta_cultivoFaringeo.getItems().addAll("Positivo", "Negativo");
		cbox_garganta_cultivoFaringeo.getSelectionModel().select("Negativo");
		
	    //Nariz
		cbox_nariz_cultivoFaringeo.getItems().removeAll(cbox_nariz_cultivoFaringeo.getItems());
		cbox_nariz_cultivoFaringeo.getItems().addAll("Positivo", "Negativo");
		cbox_nariz_cultivoFaringeo.getSelectionModel().select("Negativo");
		
	    //Cultivo Faringeo
		cbox_oido_cultivoFarigeo.getItems().removeAll(cbox_oido_cultivoFarigeo.getItems());
		cbox_oido_cultivoFarigeo.getItems().addAll("Positivo", "Negativo");
		cbox_oido_cultivoFarigeo.getSelectionModel().select("Negativo");
		
	}
	public void initializeSintomas() 
	{ 
		cbox_sintoma.getItems().removeAll(cbox_sintoma.getItems());
		cbox_sintoma.getItems().addAll("Dolor de garganta", "Fiebre", "Dolor de cabeza", "Ninguno");
		cbox_sintoma.getSelectionModel().select("Dolor de garganta");
		
		cbox_paciente_malestar.getItems().removeAll(cbox_paciente_malestar.getItems());
		cbox_paciente_malestar.getItems().addAll("Garganta", "Ninguno");
		cbox_paciente_malestar.getSelectionModel().select("Garganta");
	    
		//Dolor
		/*cbox_garganta_dolor.getItems().removeAll(cbox_garganta_dolor.getItems());
		cbox_garganta_dolor.getItems().addAll("Leve", "Fuerte", "Ninguno");
		cbox_garganta_dolor.getSelectionModel().select("Leve");*/
		
	    //Amigdalas Color
		cbox_amigdalas_color.getItems().removeAll(cbox_amigdalas_color.getItems());
		cbox_amigdalas_color.getItems().addAll("Rojiza", "Normal");
		cbox_amigdalas_color.getSelectionModel().select("Rojiza");
		
		//Amigdalas Placas
		cbox_amigdalas_placas.getItems().removeAll(cbox_amigdalas_placas.getItems());
		cbox_amigdalas_placas.getItems().addAll("Presentes", "No hay");
		cbox_amigdalas_placas.getSelectionModel().select("No hay");
		
		//Amigdalas Tamaño
		cbox_amigdalas_tamanio.getItems().removeAll(cbox_amigdalas_tamanio.getItems());
		cbox_amigdalas_tamanio.getItems().addAll("Normal", "Grandes");
		cbox_amigdalas_tamanio.getSelectionModel().select("Grandes");
		
		//Estreptococo
		cbox_garganta_estreptococo.getItems().removeAll(cbox_garganta_estreptococo.getItems());
		cbox_garganta_estreptococo.getItems().addAll("Positivo", "Negativo");
		cbox_garganta_estreptococo.getSelectionModel().select("Negativo");
		
	}
	
	public void initializeOido() 
	{ 
		//Examinacion
		cbox_oido_examinacion.getItems().removeAll(cbox_oido_examinacion.getItems());
		cbox_oido_examinacion.getItems().addAll("Positivo", "Negativo");
		cbox_oido_examinacion.getSelectionModel().select("Negativo");
		
		//Estado ES OUTPUT

	}
	
	public void initializeNariz() 
	{ 
		//Examinacion
		cbox_nariz_examinacion.getItems().removeAll(cbox_nariz_examinacion	.getItems());
		cbox_nariz_examinacion.getItems().addAll("Positivo", "Negativo");
		cbox_nariz_examinacion.getSelectionModel().select("Negativo");
		
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
	public void runCLIPS()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();

		List<String> resultados = Core.diagnosticar(paciente_dni.getText(), paciente_nombre.getText(), paciente_apellido.getText(), now.toString(), cbox_sintoma.getValue() ,cbox_paciente_malestar.getValue(),
				cbox_amigdalas_tamanio.getValue(), cbox_amigdalas_color.getValue(), cbox_amigdalas_placas.getValue(), cbox_oido_cultivoFarigeo.getValue(), "Oido", cbox_nariz_cultivoFaringeo.getValue(), "Nariz" , cbox_garganta_cultivoFaringeo.getValue() , "Garganta" , "Negativo");

		/*List<String> resultados = Core.diagnosticar("34318122", "pepe", "rodriguez", "26/06/2017", "Dolor de garganta" ,"Garganta",
				"Grandes", "Rojiza", "No hay", "Negativo", "Oido", "Negativo", "Nariz" , "Negativo" , "Garganta" , "Negativo");
		*/
		//Para verificar resultados
		String resul = "";
		String aux = "";
		for(String s: resultados){
			
			aux = s;
			aux = s.replace("(", "");
			aux = aux.replace(")", "");
			resul += aux +"\n"; 
			System.out.println(s + "\n");
		}
		 ta_diagnostico.setText(resul);
		//diagnostico.setText(output+"\n"+s);
		//return output;

	}
}
