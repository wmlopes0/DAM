package acadt_ut2_p3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Ejercicio4 {

	public static void main(String[] args) throws FileNotFoundException {
		// LEO Y MUESTRO POR PANTALLA LA INFORMACIÓN
		leerXML();
	}

	public static void leerXML() throws FileNotFoundException {
		System.out.println("Leyendo fichero XML.");
		// XStream xstream = new XStream(new DomDriver()
		XStream xstream = new XStream(new DomDriver("UTF-8"));

		// Vamos a disminuir la seguridad de la deserialización de Xstream
		xstream.addPermission(AnyTypePermission.ANY);

		// Cambio de nombre a las etiquetas XML
		xstream.alias("blog", Blog.class);
		xstream.alias("entrada", Entrada.class);

		// elimino la etiqueta entradas
		xstream.addImplicitCollection(Blog.class, "entradas");

		// Recuperamos el objeto Blog
		Blog blog = (Blog) xstream.fromXML(new FileInputStream("blog.xml"));

		// Muestro el blog
		blog.mostrar();
		System.out.println("Fichero XML leído con éxito.");
	}
}
