package p3_ejercicio3;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import p1_ejercicio3.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

//ENUNCIADO==============================================
//Crea un programa en java que serialize objetos de tipo Libro a formato XML y
//posteriormente deserialize dicho XML utilizando la librería XStream.
//=======================================================
public class Ejercicio3 {
    public static List<Libro> libros = new ArrayList<>();
    public static File libroXml = new File("src/p3_ejercicio3/libroXstream.xml");
    public static void main(String[] args) throws FileNotFoundException {
//      Genero objetos tipo Libro
        p1_ejercicio3.Ejercicio3.generarLibros(libros);
//      Creo un objeto ListaLibros y recupero la lista
        ListaLibros listaLibros = new ListaLibros(libros);
//      Escribo XML
        escribirLibroXml(listaLibros);
//      Leo XML y muestro por pantalla
        leerLibroXml();

    }
    public static void escribirLibroXml(ListaLibros listaLibros) throws FileNotFoundException {
//        Creamos XStream
        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.alias("libro",Libro.class);
        xStream.alias("libros",ListaLibros.class);
        xStream.addImplicitCollection(ListaLibros.class,"libros");
        xStream.toXML(listaLibros,new FileOutputStream(libroXml));
    }
    public static void leerLibroXml() throws FileNotFoundException {
//        Creamos XStream
        XStream xStream = new XStream(new DomDriver("UTF-8"));
//        Disminuimos seguridad
        xStream.addPermission(AnyTypePermission.ANY);
//        Alias
        xStream.alias("libros", ListaLibros.class);
        xStream.alias("libro",Libro.class);
        xStream.addImplicitCollection(ListaLibros.class,"libros");
//        Creamos objeto y lo recuperamos
        ListaLibros listaLibros = (ListaLibros) xStream.fromXML(new FileInputStream(libroXml));
        listaLibros.mostrar();

    }
}
