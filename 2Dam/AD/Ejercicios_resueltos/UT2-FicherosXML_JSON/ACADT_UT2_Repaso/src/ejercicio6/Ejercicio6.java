package ejercicio6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;

import ejercicio4.*;

public class Ejercicio6 {

	public static void main(String[] args) throws IOException {
		// Recuperamos el objeto esdla que queremos escribir en el fichero JSON
		File fichero = new File("esdla.xml");
		Esdla esdla = Ejercicio4.leerXML_Eslda(fichero);
		// Realizamos modificaciones
		Ciudad ciudadNueva = new Ciudad("Osgiliath", "Gondor");
		esdla.getCiudades().add(ciudadNueva);
		esdla.getPersonajes().getHobbits().remove(esdla.getPersonajes().getHobbits().size() - 1);
		esdla.getPersonajes().getElfos().remove(esdla.getPersonajes().getElfos().size() - 1);
		esdla.getPersonajes().getEnanos().remove(esdla.getPersonajes().getEnanos().size() - 1);
		esdla.getPersonajes().getHombres().remove(esdla.getPersonajes().getHombres().size() - 1);
		esdla.getPersonajes().getMagos().remove(esdla.getPersonajes().getMagos().size() - 1);
		esdla.getPersonajes().getVillanos().remove(esdla.getPersonajes().getVillanos().size() - 1);

		// Escribir documento JSON
		escribirJson(esdla);

	}

	public static void escribirJson(Esdla esdla) throws IOException {
		// Creamos file del fichero que queremos crear
		File ficheroJson = new File("esdla.json");
		// Creamos un JsonWriter
		JsonWriter writer = new JsonWriter(new FileWriter(ficheroJson));
		// ================== E S C R I B I M O S ==================
		writer.beginObject();
		writer.name("esdla");
		writer.beginObject();
		// ************************************************************
		// AUTOR
		writer.name("autor").value(esdla.getAutor());
		// CIUDADES
		writer.name("ciudades");
		writer.beginObject();
		writer.name("ciudad").beginArray();
		for (Ciudad ciudad : esdla.getCiudades()) {
			writer.beginObject();
			writer.name("nombre").value(ciudad.getNombre());
			writer.name("faccion").value(ciudad.getFaccion());
			writer.endObject();
		}
		writer.endArray();
		writer.endObject();
		// PERSONAJES
		writer.name("personajes").beginObject();
		// hobbits-----------------------------------
		writer.name("hobbits").beginObject();
		writer.name("personaje").beginArray();
		for (String s : esdla.getPersonajes().getHobbits()) {
			writer.value(s);
		}
		writer.endArray();
		writer.endObject();
		// elfos-----------------------------------
		writer.name("elfos").beginObject();
		writer.name("personaje").beginArray();
		for (String s : esdla.getPersonajes().getElfos()) {
			writer.value(s);
		}
		writer.endArray();
		writer.endObject();
		// enanos-----------------------------------
		writer.name("enanos").beginObject();
		writer.name("personaje").beginArray();
		for (String s : esdla.getPersonajes().getEnanos()) {
			writer.value(s);
		}
		writer.endArray();
		writer.endObject();
		// hombres-----------------------------------
		writer.name("hombres").beginObject();
		writer.name("personaje").beginArray();
		for (String s : esdla.getPersonajes().getHombres()) {
			writer.value(s);
		}
		writer.endArray();
		writer.endObject();
		// magos-----------------------------------
		writer.name("magos").beginObject();
		writer.name("personaje").beginArray();
		for (String s : esdla.getPersonajes().getMagos()) {
			writer.value(s);
		}
		writer.endArray();
		writer.endObject();
		// villanos-----------------------------------
		writer.name("villanos").beginObject();
		writer.name("personaje").beginArray();
		for (String s : esdla.getPersonajes().getVillanos()) {
			writer.value(s);
		}
		writer.endArray();
		writer.endObject();
		writer.endObject();// Fin personajes
		// **********************************************************
		writer.endObject();
		writer.endObject();
		// CERRAMOS EL WRITER
		writer.close();
	}

}
