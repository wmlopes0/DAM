package ejercicio4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Ejercicio4 {

	public static void main(String[] args) throws FileNotFoundException {
		// Crea una estructura de clases acorde al fichero XML, y transfórmalo a objetos
		// Java utilizando XStream.
		File fichero = new File("esdla.xml");
		// Leemos y mostramos
		leerXML_Eslda(fichero);
	}

	public static Esdla leerXML_Eslda(File fichero) throws FileNotFoundException {
		// Creo XStream
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		// Disminuimos seguridad
		xstream.addPermission(AnyTypePermission.ANY);
		// Alias
		xstream.alias("esdla", Esdla.class);
		xstream.alias("ciudad", Ciudad.class);
		xstream.alias("personajes", ListaPersonajes.class);
		xstream.alias("personaje", String.class);
		// Creamos objeto y lo recuperamos
		Esdla esdla = (Esdla) xstream.fromXML(new FileInputStream(fichero));
		// Mostramos el objeto
		esdla.mostrar();
		// Retornamos el objeto
		return esdla;
	}

}
