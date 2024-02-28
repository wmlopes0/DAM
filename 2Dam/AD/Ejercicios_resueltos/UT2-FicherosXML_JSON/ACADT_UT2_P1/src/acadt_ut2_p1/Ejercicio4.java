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

public class Ejercicio4 {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
		// Creamos el DocumentBuilder para poder obtener el Document
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();

		// Leemos el Document desde el fichero
		Document registroEmpleados = builder.parse(new File("Empleados.xml"));

		// Normalizamos el documento para evitar errores de lectura
		registroEmpleados.getDocumentElement().normalize();

		// Mostramos el nombre del elemento raiz
		System.out.println("El elemento raiz es " + registroEmpleados.getDocumentElement().getNodeName());

		// Creamos una lista de todos los nodos empleado
		NodeList empleados = registroEmpleados.getElementsByTagName("empleado");

		// Mostramos el nº de elementos empleado que hemos encontrado
		System.out.println("Se han encontrado " + empleados.getLength() + " empleados.");

		// Recorremos la lista
		for (int i = 0; i < empleados.getLength(); i++) {
			// Obtenemos el primer nodo de la lista
			Node emple = empleados.item(i);
			// En caso de que el nodo sea un elemento
			if (emple.getNodeType() == Node.ELEMENT_NODE) {
				// Creamos el elemento empleado y leemos su informacion
				Element empleado = (Element) emple;
				System.out.println("ID: " + empleado.getElementsByTagName("id").item(0).getTextContent());
				System.out.println("Nombre: " + empleado.getElementsByTagName("nombre").item(0).getTextContent());
				System.out.println("Apellido: " + empleado.getElementsByTagName("apellido").item(0).getTextContent());
			}
		}
	}

}
