package p1_ejercicio3;

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
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Ejercicio3 {
    /* ENUNCIADO
    Crea un programa en java que lea de un fichero objetos de tipo p1_ejercicio3.Libro (ISBN, titulo,
    autor, editorial) y por cada objeto p1_ejercicio3.Libro se inserte un nodo p1_ejercicio3.Libro en el documento XML.
    Todos los campos serán nodos del libro excepto el ISBN que será un atributo:
    <libros>
        <libro ISBN="239-87-9964-088-4">
            <titulo>Acceso a Datos</titulo>
            <autor>Alicia Ramos</autor>
            <editorial>Garceta</editorial>
        </libro>
    </libros>
*/

    //Variables globales y constantes
    public static File ficheroXml = new File("src/p1_ejercicio3/libros.xml");
    public static File ficheroObjetos = new File("src/p1_ejercicio3/libros.obj");
    public static List<Libro> listaLibros = new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParserConfigurationException, TransformerException {
//        Creo el fichero de objetos
        generarLibros();
//        Leo el fichero OBJ y almaceno los libros en la lista
        leerLibrosOBJ();
//        Escribo el fichero XML con la información proporcionada
        escribirLibrosXML();
    }

    public static void generarLibros() throws IOException {
        // Creando un flujo de salida para escribir objetos
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroObjetos));
        // Libros reales
        Libro[] libros = {
                new Libro("978-0-7432-7354-5", "The Great Gatsby", "F. Scott Fitzgerald", "Scribner"),
                new Libro("978-0-452-28423-2", "To Kill a Mockingbird", "Harper Lee", "J. B. Lippincott & Co."),
                new Libro("978-0-140-27916-0", "1984", "George Orwell", "Penguin Books"),
                new Libro("978-0-613-52590-1", "The Catcher in the Rye", "J.D. Salinger", "Little, Brown and Company"),
                new Libro("978-0-140-13447-5", "Brave New World", "Aldous Huxley", "Penguin Books"),
                new Libro("978-0-452-27750-5", "Moby-Dick", "Herman Melville", "Penguin Books"),
                new Libro("978-0-140-30393-9", "The Odyssey", "Homer", "Penguin Books"),
                new Libro("978-0-151-01283-0", "Pride and Prejudice", "Jane Austen", "Penguin Books"),
                new Libro("978-0-553-21311-7", "Fahrenheit 451", "Ray Bradbury", "Ballantine Books"),
                new Libro("978-0-670-08380-3", "The Hobbit", "J.R.R. Tolkien", "George Allen & Unwin")
        };
        // Escribiendo libros en el fichero
        for (Libro libro : libros) {
            oos.writeObject(libro);
        }
        // Cerrando el flujo de salida
        oos.close();
    }

    public static void generarLibros(List<Libro> listalibros) {
        // Libros reales
        Libro[] libros = {
                new Libro("978-0-7432-7354-5", "The Great Gatsby", "F. Scott Fitzgerald", "Scribner"),
                new Libro("978-0-452-28423-2", "To Kill a Mockingbird", "Harper Lee", "J. B. Lippincott & Co."),
                new Libro("978-0-140-27916-0", "1984", "George Orwell", "Penguin Books"),
                new Libro("978-0-613-52590-1", "The Catcher in the Rye", "J.D. Salinger", "Little, Brown and Company"),
                new Libro("978-0-140-13447-5", "Brave New World", "Aldous Huxley", "Penguin Books"),
                new Libro("978-0-452-27750-5", "Moby-Dick", "Herman Melville", "Penguin Books"),
                new Libro("978-0-140-30393-9", "The Odyssey", "Homer", "Penguin Books"),
                new Libro("978-0-151-01283-0", "Pride and Prejudice", "Jane Austen", "Penguin Books"),
                new Libro("978-0-553-21311-7", "Fahrenheit 451", "Ray Bradbury", "Ballantine Books"),
                new Libro("978-0-670-08380-3", "The Hobbit", "J.R.R. Tolkien", "George Allen & Unwin")
        };
        for (int i = 0; i < libros.length; i++) {
            listalibros.add(libros[i]);
        }
    }

    public static void leerLibrosOBJ() throws IOException, ClassNotFoundException {
        Libro libro;
//        ABRO FLUJOS
        ObjectInputStream dis = new ObjectInputStream(new FileInputStream(ficheroObjetos));
        try {
            while (true) {
                libro = (Libro) dis.readObject();
                listaLibros.add(libro);
            }
        } catch (EOFException e) {
//            FIN FICHERO
        }
//        CIERRO FLUJOS
        dis.close();
    }

    public static void escribirLibrosXML() throws ParserConfigurationException, TransformerException {
//        Instanciamos DocumentBuildFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        Creamos DocumentBuilder
        DocumentBuilder builder = dbf.newDocumentBuilder();
//        Obtenemos la implementacion del DOM
        DOMImplementation implementacion = builder.getDOMImplementation();
//        Creamos document
        Document librosXML = implementacion.createDocument(null, "libros", null);
//        Establecemos la version XML
        librosXML.setXmlVersion("1.0");
//        ESCRIBIMOS ===================================================================
        for (Libro lib : listaLibros) {
            Element libro = librosXML.createElement("libro");
            libro.setAttribute("ISBN", lib.getISBN());
            Element titulo = librosXML.createElement("titulo");
            Text texto = librosXML.createTextNode(lib.getTitulo());
            titulo.appendChild(texto);
            libro.appendChild(titulo);
            Element autor = librosXML.createElement("autor");
            texto = librosXML.createTextNode(lib.getAutor());
            autor.appendChild(texto);
            libro.appendChild(autor);
            Element editorial = librosXML.createElement("editorial");
            texto = librosXML.createTextNode(lib.getEditorial());
            editorial.appendChild(texto);
            libro.appendChild(editorial);
//            Añadimos el nodo libro a el documentElement (Libros)
            librosXML.getDocumentElement().appendChild(libro);
        }
//        Creamos Source y le pasamos por parámetro el Document
        Source origen = new DOMSource(librosXML);
//        Creamos el Result y le pasamos el File
        Result resultado = new StreamResult(ficheroXml);
//        Creamos transform y le pasamos origen y resultado
        Transformer transformador = TransformerFactory.newInstance().newTransformer();
        transformador.transform(origen, resultado);
    }
}
