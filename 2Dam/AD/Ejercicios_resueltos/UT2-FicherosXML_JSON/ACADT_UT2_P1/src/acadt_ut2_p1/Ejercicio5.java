package acadt_ut2_p1;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Ejercicio5 {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// Creamos el DocumentBuilder para poder obtener el Document
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();

		// Leemos el Document desde el fichero
		Document registroContactos = builder.parse(new File("Contactos.xml"));

		// Normalizamos
		registroContactos.getDocumentElement().normalize();

		// Mostramos el nombre del elemento raíz
		System.out.println("El elemento raíz es: " + registroContactos.getDocumentElement().getNodeName());

		// Creamos una lista con los nodos de contacto
		NodeList contactos = registroContactos.getElementsByTagName("contacto");

		// Mostramos el numero de contactos que hemos encotrado
		System.out.println("Se han encontrado " + contactos.getLength() + " contactos.");

		// Recorremos la lista
		for (int i = 0; i < contactos.getLength(); i++) {
			// Obtenemos el primer nodo de la lista
			Node contact = contactos.item(i);

			// Si es un elemento leemos su info
			if (contact.getNodeType() == Node.ELEMENT_NODE) {
				// Leemos su informacion
				Element contacto = (Element) contact;
				System.out.println("Nombre: " + contacto.getElementsByTagName("nombre").item(0).getTextContent());
				System.out.println("Apellidos: " + contacto.getElementsByTagName("apellidos").item(0).getTextContent());
				System.out.println("Email: " + contacto.getElementsByTagName("email").item(0).getTextContent());
				System.out.println("Teléfono: " + contacto.getElementsByTagName("telefono").item(0).getTextContent());
			}
		}
	}

}
