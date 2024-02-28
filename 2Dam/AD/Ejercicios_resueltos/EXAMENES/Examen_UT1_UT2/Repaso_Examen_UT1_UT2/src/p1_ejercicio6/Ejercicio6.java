package p1_ejercicio6;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import p1_ejercicio3.Libro;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio6 {
//    ENUNCIADO===================================================
//Crea un programa en java para que lea el fichero XML Libros.xml, creado en el
//ejercicio anterior.
//    ============================================================
    //    Variables globales
    public static File ficheroXML = new File("src/p1_ejercicio3/libros.xml");
    public static List<Libro> listaLibros = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
//        Leo XML creando el objeto y añadiendolo en la lista
        leerXML();
//        Muestro los objetos de la lista
        mostrarListaLibros();
    }

    //Método para leer el XML
    public static void leerXML() throws ParserConfigurationException, IOException, SAXException {
//        Creamos nueva instancia de DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        Creamos nuevo DocumentBuilder
        DocumentBuilder builder = dbf.newDocumentBuilder();
//        Creamos document
        Document librosXML = builder.parse(ficheroXML);
//        Normalizamos
        librosXML.getDocumentElement().normalize();
//        LEEMOS =============================================
        NodeList libros = librosXML.getElementsByTagName("libro");
        for (int i = 0; i < libros.getLength(); i++) {
            Node lib = libros.item(i);
            if (lib.getNodeType() == Node.ELEMENT_NODE) {
//                Objeto auxiliar
                Libro libroAux = new Libro();
                Element libro = (Element) lib;
                libroAux.setISBN(libro.getAttribute("ISBN"));
                libroAux.setTitulo(libro.getElementsByTagName("titulo").item(0).getTextContent());
                libroAux.setAutor(libro.getElementsByTagName("autor").item(0).getTextContent());
                libroAux.setEditorial(libro.getElementsByTagName("editorial").item(0).getTextContent());
                listaLibros.add(libroAux);
            }
        }

    }

    //    Método para mostrar libros
    public static void mostrarListaLibros() {
        for (Libro libro : listaLibros) {
            libro.mostrar();
        }
    }
}
