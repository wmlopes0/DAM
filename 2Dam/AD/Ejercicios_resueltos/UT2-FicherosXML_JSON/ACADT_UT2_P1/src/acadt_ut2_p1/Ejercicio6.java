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

public class Ejercicio6 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// Creamos el DocumentBuilder para poder obtener el Document
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();

		// Leemos el document desde el fichero
		Document registroLibros = builder.parse(new File("Libros.xml"));

		// Normalizamos
		registroLibros.getDocumentElement().normalize();

		// Mostramos el elemento raíz
		System.out.println("El elemento raíz es: " + registroLibros.getDocumentElement().getNodeName());

		// Creamos lista con los nodos libros
		NodeList libros = registroLibros.getElementsByTagName("libro");

		// Mostramos el numero de libros que hemos encontrado
		System.out.println("Se han encontrado " + libros.getLength() + " libros.");

		// Recorremos la lista
		for (int i = 0; i < libros.getLength(); i++) {
			// Obtenemos el primer nodo de la lista 
			Node lib = libros.item(i);
			// Si el nodo es un elemento lo recorremos leyendo la info
			if (lib.getNodeType() == Node.ELEMENT_NODE) {
				Element libro = (Element) lib;
				System.out.println("ISBN: "+libro.getAttributeNode("ISBN").getTextContent());
				System.out.println("Titulo: "+libro.getElementsByTagName("titulo").item(0).getTextContent());
				System.out.println("Autor: "+libro.getElementsByTagName("autor").item(0).getTextContent());
				System.out.println("Editorial: "+libro.getElementsByTagName("editorial").item(0).getTextContent());
			}
		}
	}

}
