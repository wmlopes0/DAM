package acadt_ut2_p2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class Ejercicio2 {

	public static List<String> info = new ArrayList<>();
	public static Set<Empleado> empleados = new TreeSet();

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		// LEO EL XML
		SAXParserFactory parseFactory = SAXParserFactory.newInstance();
		SAXParser parser = parseFactory.newSAXParser();
		XMLReader procesadorxml = parser.getXMLReader();

		procesadorxml.setContentHandler(new EmpleadosReader());
		InputSource xmlFile = new InputSource("src/empleados.xml");
		procesadorxml.parse(xmlFile);

		// GENERO LOS OBJETOS Y LOS INTRODUZCOS EN UN SET
		generarEmpleados();
		// MOSTRAR
		 mostrarEmpleados();
	}

	public static void rellenarInfo(String a) {
		info.add(a);
	}

	public static void generarEmpleados() {
		List<String> aux = new ArrayList<>();
		Empleado empleado;
		int contador = 1;
		for (int i = 0; i < info.size(); i++) {
			aux.add(info.get(i));
			if (contador == 4) {
				empleado = new Empleado(Integer.parseInt(aux.get(0)), aux.get(1), aux.get(2),
						Integer.parseInt(aux.get(3)));
				empleados.add(empleado);
				aux.clear();
				contador = 0;
			}

			contador++;
		}

	}

	public static void mostrarEmpleados() {
		for (Empleado empleado : empleados) {
			empleado.mostrarEmpleado();
		}
	}
}
