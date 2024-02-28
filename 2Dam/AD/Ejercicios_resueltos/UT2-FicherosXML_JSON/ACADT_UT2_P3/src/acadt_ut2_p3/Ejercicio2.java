package acadt_ut2_p3;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ejercicio2 {

	public static ListaContactos listaContactos;

	public static void main(String[] args) throws FileNotFoundException {
		leerXML();
	}

	public static void leerXML() throws FileNotFoundException {
		// XStream xstream = new XStream(new DomDriver()
		XStream xstream = new XStream(new DomDriver("UTF-8"));

		// Vamos a disminuir la seguridad de la deserialización de Xstream
		xstream.addPermission(AnyTypePermission.ANY);

		// Cambio de nombre a las etiquetas XML
		xstream.alias("DatosContacto", Contacto.class);
		xstream.alias("Contactos", ListaContactos.class);

		// Quito la etiqueta lista (Que es el atributo de la clase ListaContacto
		xstream.addImplicitCollection(ListaContactos.class, "lista");

		// Recuperamos el objeto ListaContactos
		listaContactos = (ListaContactos) xstream.fromXML(new FileInputStream("Contactos.xml"));

		// Muestro la lista de Contactos
		listaContactos.mostrar();
	}

}
