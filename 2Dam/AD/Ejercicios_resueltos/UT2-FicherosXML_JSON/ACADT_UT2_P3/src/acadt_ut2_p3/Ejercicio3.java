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
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Ejercicio3 {

	// Creo un objeto tipo ListaLibro, clase que funciona a modo wrapper
	public static ListaLibros listaLibros = new ListaLibros();

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// Creo el fichero .obj con 3 libros
		escribirFicheroLibros();
		// Leo el fichero de libros y lo almaceno en una instancia tipo ListaLibros
		leerFicheroLibros();
		// Creo el archivo XML a partir de la información almacenada el listaLibros
		crearXML();
		// Leo XML y muestro por pantalla la información
		leerXML();
	}

	public static void escribirFicheroLibros() throws IOException {
		System.out.println("Creando fichero libros.obj con información.");
		File fichero = new File("libros.obj");
		// Abro flujo
		ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(fichero));
		// Creo libros y los escribo
		Libro libro1 = new Libro("asdf4515", "Acceso a datos", "Walter", "Gareta");
		Libro libro2 = new Libro("asdf451asd5", "Acceso a datosss", "Walter Martin", "Gareta2");
		Libro libro3 = new Libro("asdf451assadasdd5", "PMYDM", "Walter", "Gareta23");
		dos.writeObject(libro1);
		dos.writeObject(libro2);
		dos.writeObject(libro3);
		// Cierro flujo
		dos.close();
		System.out.println("Fichero libros.obj creado con éxito.");
	}

	public static void leerFicheroLibros() throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("Leyendo fichero y almacenandolo en clase Wrapper.");
		// Abro flujos
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("libros.obj"));
		// Leo y almaceno en la lista de libros
		try {
			while (true) {
				Libro libro = (Libro) ois.readObject();
				listaLibros.add(libro);
			}
		} catch (EOFException e) {
		}
		// Cierro flujos
		ois.close();
		System.out.println("Lectura finalizada con éxito.");
	}

	public static void crearXML() throws FileNotFoundException {
		System.out.println("Creando fichero XML con la información almacenada.");

		// XStream xstream = new XStream(new DomDriver()
		XStream xstream = new XStream(new DomDriver("UTF-8"));

		// Cambio de nombre a las etiquetas XML
		xstream.alias("libro", Libro.class);
		xstream.alias("Libros", ListaLibros.class);

		// Quito la etiqueta lista (Que es el atributo de la clase ListaContacto
		xstream.addImplicitCollection(ListaLibros.class, "lista");

		// xstream.omitField(Libro.class, "isbn"); // Omito en el xml el atributo ISBN

		// CREO XML
		xstream.toXML(listaLibros, new FileOutputStream("Libros.xml"));
		System.out.println("Fichero XML creado con éxito.");
	}

	public static void leerXML() throws FileNotFoundException {
		System.out.println("Leyendo fichero XML.");
		// XStream xstream = new XStream(new DomDriver()
		XStream xstream = new XStream(new DomDriver("UTF-8"));

		// Vamos a disminuir la seguridad de la deserialización de Xstream
		xstream.addPermission(AnyTypePermission.ANY);

		// Cambio de nombre a las etiquetas XML
		xstream.alias("libro", Libro.class);
		xstream.alias("Libros", ListaLibros.class);

		// Quito la etiqueta lista (Que es el atributo de la clase ListaContacto
		xstream.addImplicitCollection(ListaLibros.class, "lista");

		// Recuperamos el objeto ListaContactos
		listaLibros = (ListaLibros) xstream.fromXML(new FileInputStream("Libros.xml"));

		// Muestro la lista de Contactos
		listaLibros.mostrar();
		System.out.println("Fichero XML leído con éxito.");
	}
}
