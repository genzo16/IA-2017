package controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.FactAddressValue;
import net.sf.clipsrules.jni.MultifieldValue;

public class Core {
	
	
	//
	public static List<String> diagnosticar(String dni, String nombre, String apellido, String fechaTratamiento, String sintomas, String descripcionMalestar,
			String tamanio, String color, String placasBlancas, String resultado1, String zona1, String resultado2, String zona2, String resultado3 ,String zona3, String estado){
		
		List<String> resultados = new ArrayList<String>();
		//System.loadLibrary("CLIPSJNI");
		//System.load("/Users/FedeAndrada/Documents/workspace/consultorio/libCLIPSJNI.jnilib");
		String programaCLIPS = "./medico.clp";
		System.out.println("Loaded <"+programaCLIPS+">");
		Environment e = null;
		e = new Environment();
		e.load(programaCLIPS);
		System.out.println("VERSION: "+e.getCLIPSVersion());
		
		e.reset();
		
		e.eval("(assert(Paciente (dni \"" + dni + "\")(nombre \"" + nombre + "\")(apellido \"" + apellido + "\"))\r\n"
				+ "(Historial-Medico(idPaciente \"" + dni + "\")(fecha-tratamiento \""+fechaTratamiento+"\"))\r\n"
				+ "(Sintomas-Paciente (idPaciente \"" + dni + "\")(sintomas \"" + sintomas + "\")(descripcionMalestar \"" + descripcionMalestar + "\"))\r\n"
				+ "(Amigdalas (idPaciente \"" + dni +  "\")(tamanio \"" + tamanio + "\")(color \"" + color + "\")(placas-blancas \"" + placasBlancas + "\"))\r\n"
				+ "(Oido(idPaciente \"" + dni + "\"))\r\n"
				+ "(Cultivo-Faringeo (idPaciente \"" + dni +"\")(resultado \"" + resultado1 + "\")(zona \"" + zona1 + "\"))\r\n"
				+ "(Nariz(idPaciente \"" + dni + "\"))\r\n"
				+ "(Cultivo-Faringeo(idPaciente \"" + dni + "\")(resultado \"" + resultado2 + "\")(zona \"" + zona2  + "\"))"
				+ "(Cultivo-Faringeo(idPaciente \"" + dni + "\")(resultado \"" + resultado3 + "\")(zona \"" + zona3  + "\"))"
				+ "(Estreptococo(idPaciente \"" + dni + "\")(estado \"" + estado + "\")))");
		
		    		
		
		e.run();
		System.out.println("\n");
		
		String evaluardiagnostico = "(find-all-facts ((?x Diagnostico)) TRUE )";
		MultifieldValue value1 = (MultifieldValue) e.eval(evaluardiagnostico);
		String evaluartratamiento = "(find-all-facts ((?x Tratamiento)) TRUE )";
		MultifieldValue value2 = (MultifieldValue) e.eval(evaluartratamiento);
		String evaluarreceta = "(find-all-facts ((?x Receta)) TRUE )";
		MultifieldValue value3 = (MultifieldValue) e.eval(evaluarreceta);
		
		try{
			
			//Diagnostico
			FactAddressValue fv = (FactAddressValue) value1.get(0);
			resultados.add("Enfermedad: " + fv.getFactSlot("enfermedad").toString().replaceAll("\"", ""));
			resultados.add("Zona a Tratar: " + fv.getFactSlot("zonaTratar").toString().replaceAll("\"", ""));
			
			//Tratamiento
			FactAddressValue fv2 = (FactAddressValue) value2.get(0);
			resultados.add("Tiempo: " + fv2.getFactSlot("tiempo").toString().replaceAll("\"", ""));
			resultados.add("Estado: " + fv2.getFactSlot("estado").toString().replaceAll("\"", ""));
			resultados.add("Recomendaciones: " + fv2.getFactSlot("recomendaciones").toString().replaceAll("\"", ""));
			resultados.add("Visitas: " + fv2.getFactSlot("visitas").toString().replaceAll("\"", ""));
			resultados.add("Observaciones: " + fv2.getFactSlot("observaciones").toString().replaceAll("\"", ""));
			resultados.add("Derivaciones: " + fv2.getFactSlot("derivaciones").toString().replaceAll("\"", ""));

			
			//Receta
			FactAddressValue fv3 = (FactAddressValue) value3.get(0);
			resultados.add("Antibiotico: " + fv3.getFactSlot("antibiotico").toString().replaceAll("\"", ""));
			resultados.add("Analgesico: " + fv3.getFactSlot("analgesico").toString().replaceAll("\"", ""));
			resultados.add("Liquidos: " + fv3.getFactSlot("liquidos").toString().replaceAll("\"", ""));
			resultados.add("Solidos: " + fv3.getFactSlot("solidos").toString().replaceAll("\"", ""));

			
		}catch(Exception err){
			err.printStackTrace();
		}
		
		

		
		return resultados;
	}


}
