package acadt_ut2_p1;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Ejercicio2 {

	public static File fichero = new File("contactos.obj");
	public static List<Contacto> lContactos = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerException {
		ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(fichero));
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
		Contacto contacto;

		// Escribimos
		do {
			contacto = new Contacto();
			contacto.rellenarContacto();
			escribirFichero(dos, contacto);
		} while (seguir());

		// Cierro flujo escritura
		dos.close();

		// Leemos y escribimos en xml
		leerFichero(ois);
		// Cierro flujo de lectura
		ois.close();
		// Escribir en xml
		escribirXML();
	}

	public static void escribirFichero(ObjectOutputStream dos, Contacto contacto) throws IOException {
		dos.writeObject(contacto);
	}

	public static void leerFichero(ObjectInputStream ois) {
		Contacto c;
		try {
			while (true) {
				c = (Contacto) ois.readObject();
				lContactos.add(c);
			}
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
	}

	public static void escribirXML()
			throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		// Creamos un DocumentBuilder haciendo uso de la factoria
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();

		DOMImplementation implementacion = builder.getDOMImplementation();
		Document contactos = implementacion.createDocument(null, "contactos", null);

		// Asignamos la version de XML
		contactos.setXmlVersion("1.0");

		for (Contacto c : lContactos) {

			// Creamos un nodo empleado
			Element contacto = contactos.createElement("contacto");
			// Añadimos contacto a contactos
			contactos.getDocumentElement().appendChild(contacto);
			// Creamos resto de atributos
			Element nombre = contactos.createElement("nombre");
			Element apellidos = contactos.createElement("apellidos");
			Element email = contactos.createElement("email");
			Element telefono = contactos.createElement("telefono");

			contacto.appendChild(nombre);
			contacto.appendChild(apellidos);
			contacto.appendChild(email);
			contacto.appendChild(telefono);

			// Rellenamos
			Text texto = contactos.createTextNode(c.getNombre());
			nombre.appendChild(texto);
			texto = contactos.createTextNode(c.getApellidos());
			apellidos.appendChild(texto);
			texto = contactos.createTextNode(c.getEmail());
			email.appendChild(texto);
			texto = contactos.createTextNode(String.valueOf(c.getTelefono()));
			telefono.appendChild(texto);

		}

		Source origen = new DOMSource(contactos);
		Result resultado = new StreamResult(new File("Contactos.xml"));
		Transformer transformador = TransformerFactory.newInstance().newTransformer();
		transformador.transform(origen, resultado);
	}

	public static boolean seguir() {
		Scanner s = new Scanner(System.in);
		System.out.println("Desea seguir?");
		return s.nextLine().equalsIgnoreCase("si");
	}

	public static void mostrarContactos() {
		for (Contacto c : lContactos) {
			c.mostrarContacto();
		}
	}
}
