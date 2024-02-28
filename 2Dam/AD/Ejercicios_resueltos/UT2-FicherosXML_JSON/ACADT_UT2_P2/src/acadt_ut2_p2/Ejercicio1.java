package acadt_ut2_p2;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class Ejercicio1 {

	public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
		SAXParserFactory parseFactory = SAXParserFactory.newInstance();
		SAXParser parser = parseFactory.newSAXParser();
		XMLReader procesadorxml = parser.getXMLReader();

		procesadorxml.setContentHandler(new AlumnosReader());
		InputSource xmlFile = new InputSource("src/alumnos.xml");
		procesadorxml.parse(xmlFile);

	}

}
