package p2_ejercicio2;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio2 {
//    ENUNCIADO===================================
//Utiliza SAX para visualizar el contenido del fichero empleados.xml. Para visualizar el
//contenido será necesario crear objetos de tipo Empleado y mostrarlos ordenados por
//sueldo:
//José González, sueldo: 900€
//Daniel Martín, sueldo: 1.500€
//Maria Jimenez, sueldo: 2.100€
//    ============================================

    //    Variables globales
    public static List<Empleado> listaEmpleados = new ArrayList<>();
    public static String empleadosXML = "src/p2_ejercicio2/empleados.xml";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
//        Leer XML con SAX creando objetos y almacenandolos en la lista de empleados
        leerXmlSax();
//        Mostrar listaEmpleados
        mostrarListaEmpleados();
    }

    public static void leerXmlSax() throws ParserConfigurationException, SAXException, IOException {
//        Creamos instancia de SaxParserFactory
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
//        Creamos SaxParser y XMLReader
        SAXParser parser = parserFactory.newSAXParser();
        XMLReader procesadorXml = parser.getXMLReader();
//        Establecemos el content handler
        procesadorXml.setContentHandler(new EmpleadoReader());
//        Creamos un nuevo InputSource
        InputSource source = new InputSource(empleadosXML);
//        Utilizamos el procesador creado para procesar el xml
        procesadorXml.parse(source);
    }

    public static void mostrarListaEmpleados() {
        for (Empleado emp :
                listaEmpleados) {
            emp.mostrar();
        }
    }
}
