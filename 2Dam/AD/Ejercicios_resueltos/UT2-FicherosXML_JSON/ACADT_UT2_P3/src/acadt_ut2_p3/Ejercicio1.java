package acadt_ut2_p3;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Ejercicio1 {

	public static File fichero = new File("contactos.obj");
	public static ListaContactos listaContactos = new ListaContactos();

	public static void main(String[] args) {
		// Escribo el fichero de objetos
		try {
			escribirFicheroObjetos();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Leo fichero de objetos
		try {
			leerFicheroObjetos();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		// CREO XML
		try {
			crearFicheroXML();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void escribirFicheroObjetos() throws IOException, ClassNotFoundException {
		File fichero = new File("contactos.obj");
		FileOutputStream fos = new FileOutputStream(fichero);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Contacto c1 = new Contacto("pablo", "lucas", "plucas517@gmail.com", 638888163);
		oos.writeObject(c1);
		Contacto c2 = new Contacto("jorge", "perez", "plucas517@gmail.com", 638888163);
		oos.writeObject(c2);
		Contacto c3 = new Contacto("david", "gonzalez", "plucas517@gmail.com", 638888163);
		oos.writeObject(c3);

	}

	public static void leerFicheroObjetos() throws IOException, ClassNotFoundException {
		// Abro flujo
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
		System.out.println("Comienza el proceso de creación del fichero a XML ...");
		try {
			while (true) {
				Contacto contacto = (Contacto) ois.readObject();// Leo el contacto
				listaContactos.add(contacto);// Añado el contacto
			}
		} catch (EOFException e) {
			System.out.println("FIN DEL FICHERO");
		}

		// Cierro flujo
		ois.close();
	}

	public static void crearFicheroXML() throws FileNotFoundException {
		// XStream xstream = new XStream(new DomDriver()
		XStream xstream = new XStream(new DomDriver("UTF-8"));

		// Cambio de nombre a las etiquetas XML
		xstream.alias("DatosContacto", Contacto.class);
		xstream.alias("Contactos", ListaContactos.class);

		// Quito la etiqueta lista (Que es el atributo de la clase ListaContacto
		xstream.addImplicitCollection(ListaContactos.class, "lista");

		xstream.omitField(Contacto.class, "telefono"); // Omito en el xml el atributo telefono

		// CREO XML
		xstream.toXML(listaContactos, new FileOutputStream("Contactos.xml"));
		System.out.println("CREADO EL FICHERO XML.....");

	}
}
