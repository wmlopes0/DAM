package acadt_ut2_p5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Ejercicio3 {

	public static ListaLibros libros;

	public static void main(String[] args) throws FileNotFoundException {
//		Crea un programa en java que a partir del XML libros-14.xml cree un archivo
//		libros.json. Utiliza la librería Xstream y Gson

		// Lectura de libros-14.xml con XSTREAM
		lecturaXML();
	}

	public static void lecturaXML() throws FileNotFoundException {
		// Creo XSTREAM
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		// Reduzco seguridad
		xstream.addPermission(AnyTypePermission.ANY);
		// Alias
		xstream.alias("libros", ListaLibros.class);
		xstream.alias("libro", Libro.class);
		xstream.alias("autor", String.class);
		// AddImplicitCollection
		xstream.addImplicitCollection(ListaLibros.class, "libros");

		// Recuperamos el objeto ListaLibros
		libros = (ListaLibros) xstream.fromXML(new FileInputStream("libros-14.xml"));

		// Mostramos
		libros.mostrar();

	}
}
