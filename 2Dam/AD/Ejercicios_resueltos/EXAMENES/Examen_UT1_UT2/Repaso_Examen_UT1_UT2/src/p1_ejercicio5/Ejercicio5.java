package p1_ejercicio5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import p1_ejercicio2.Contacto;
import p1_ejercicio2.Ejercicio2;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

//ENUNCIADO================================
//Crea un programa en java para que lea el fichero XML Contactos.xml, creado en el
//ejercicio anterior.
//=========================================
public class Ejercicio5 {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        leerContactosXml();
    }

    public static void leerContactosXml() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document contactosXml = builder.parse(Ejercicio2.fileContactosXml);
        contactosXml.getDocumentElement().normalize();
//        LEEMOS
        NodeList contactos = contactosXml.getElementsByTagName("contacto");
        for (int i = 0; i < contactos.getLength(); i++) {
            Node emp = contactos.item(i);
            if (emp.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoContacto = (Element) emp;
                Contacto contacto = new Contacto();
                contacto.setNombre(elementoContacto.getElementsByTagName("nombre").item(0).getTextContent());
                contacto.setApellidos(elementoContacto.getElementsByTagName("apellidos").item(0).getTextContent());
                contacto.setEmail(elementoContacto.getElementsByTagName("email").item(0).getTextContent());
                contacto.setTelefono(Integer.parseInt(elementoContacto.getElementsByTagName("telefono").item(0).getTextContent()));
                contacto.mostrar();
            }
        }

    }
}
