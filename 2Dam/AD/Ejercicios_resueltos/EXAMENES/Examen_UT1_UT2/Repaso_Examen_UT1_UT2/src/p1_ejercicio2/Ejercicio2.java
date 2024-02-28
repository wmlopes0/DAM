package p1_ejercicio2;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

//ENUNCIADO===================================
//Crea un programa en java que lea de un fichero objetos de tipo Contacto (Nombre,
//Apellidos, email, teléfono) y por cada objeto Contacto se inserte un nodo contacto en
//el documento XML.
//============================================
public class Ejercicio2 {
    //    VARIABLES GLOBALES
    public static File fileContactosXml = new File("src/p1_ejercicio2/contactos.xml");

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        List<Contacto> contactos = new ArrayList<>();
        rellenarContactos(contactos);
//      ESCRIBIR XML
        escribirContactosXml(contactos);
    }

    public static void rellenarContactos(List<Contacto> contactos) {
        contactos.add(new Contacto("Juan", "Pérez", "juan.perez@example.com", 123456789));
        contactos.add(new Contacto("María", "González", "maria.gonzalez@example.com", 987654321));
        contactos.add(new Contacto("Carlos", "Rodríguez", "carlos.rodriguez@example.com", 112233445));
        contactos.add(new Contacto("Ana", "Martín", "ana.martin@example.com", 998877665));
        contactos.add(new Contacto("Pedro", "García", "pedro.garcia@example.com", 554433221));
        contactos.add(new Contacto("Laura", "López", "laura.lopez@example.com", 667788990));
        contactos.add(new Contacto("Javier", "Hernández", "javier.hernandez@example.com", 223344556));
        contactos.add(new Contacto("Mónica", "Jiménez", "monica.jimenez@example.com", 445566778));
        contactos.add(new Contacto("Fernando", "Ruiz", "fernando.ruiz@example.com", 112233445));
        contactos.add(new Contacto("Sofía", "Alvarez", "sofia.alvarez@example.com", 998877665));
    }

    public static void escribirContactosXml(List<Contacto> contactos) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        DOMImplementation implentacion = builder.getDOMImplementation();
        Document contactosXml = implentacion.createDocument(null, "contactos", null);
        contactosXml.setXmlVersion("1.0");
//        ESCRIBIMOS
        for (Contacto c : contactos) {
            Element contacto = contactosXml.createElement("contacto");
            Element nombre = contactosXml.createElement("nombre");
            Text texto = contactosXml.createTextNode(c.getNombre());
            nombre.appendChild(texto);
            contacto.appendChild(nombre);
            Element apellidos = contactosXml.createElement("apellidos");
            texto = contactosXml.createTextNode(c.getApellidos());
            apellidos.appendChild(texto);
            contacto.appendChild(apellidos);
            Element email = contactosXml.createElement("email");
            texto = contactosXml.createTextNode(c.getEmail());
            email.appendChild(texto);
            contacto.appendChild(email);
            Element telefono = contactosXml.createElement("telefono");
            texto = contactosXml.createTextNode(String.valueOf(c.getTelefono()));
            telefono.appendChild(texto);
            contacto.appendChild(telefono);
            contactosXml.getDocumentElement().appendChild(contacto);
        }

        Source origen = new DOMSource(contactosXml);
        Result resultado = new StreamResult(fileContactosXml);
        Transformer transformador = TransformerFactory.newInstance().newTransformer();
        transformador.transform(origen,resultado);
    }
}
