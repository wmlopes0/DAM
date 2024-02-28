package acadt_ut2_p1;

import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.*;
import java.io.*;

public class Ejercicio1 {

	public static void main(String[] args)
			throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		// Creamos un DocumentBuilder haciendo uso de la factoria
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();

		// Creamos un documento vacio
		// Nombre -->RegistroEmpleados
		// Nodo Raíz --> Empleados

		DOMImplementation implementacion = builder.getDOMImplementation();
		Document registroEmpleados = implementacion.createDocument(null, "empleados", null);

		// Asignamos la version de XML
		registroEmpleados.setXmlVersion("1.0");

		// Creamos un nodo empleado
		Element empleado = registroEmpleados.createElement("empleado");
		// Lo añadimos como hijo de empleados
		registroEmpleados.getDocumentElement().appendChild(empleado);
		// Creamos el nodo ID
		Element id = registroEmpleados.createElement("id");
		// Creamosel nodo tecto con el valor de la id
		Text texto = registroEmpleados.createTextNode("01");
		id.appendChild(texto);
		// Añadimos el nodo id a empleado
		empleado.appendChild(id);
		Element nombre = registroEmpleados.createElement("nombre");
		texto = registroEmpleados.createTextNode("Antonio");
		nombre.appendChild(texto);
		empleado.appendChild(nombre);
		Element apellidos = registroEmpleados.createElement("apellido");
		texto = registroEmpleados.createTextNode("Morales");
		apellidos.appendChild(texto);
		empleado.appendChild(apellidos);

		// Finalmente, para guardar el documento en disco debemos:
		// 1. Crear el origen de datos (Nuestro Document)
		// 2. Crear el resultado (El fichero de destino)
		// 3.Crear un TrasnformerFactory
		// 4. Realizarla trasnformacion

		Source origen = new DOMSource(registroEmpleados);
		Result resultado = new StreamResult(new File("Empleados.xml"));
		Transformer transformador = TransformerFactory.newInstance().newTransformer();
		transformador.transform(origen, resultado);
	}

}
