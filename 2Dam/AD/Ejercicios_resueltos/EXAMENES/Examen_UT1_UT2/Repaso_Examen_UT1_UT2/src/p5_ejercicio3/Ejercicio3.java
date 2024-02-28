package p5_ejercicio3;

import com.google.gson.stream.JsonWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.util.List;

//ENUNCIADO===========================================
//Crea un programa en java que a partir del XML libros-14.xml cree un archivo
//libros.json. Utiliza la librería Xstream y Gson
//====================================================
public class Ejercicio3 {
    //    Variables globales
    public static File ficheroXml = new File("src/p5_ejercicio3/libros-14.xml");
    public static File ficheroJson = new File("src/p5_ejercicio3/libros.json");
    public static ListaLibros listaLibros;

    public static void main(String[] args) throws IOException {
//        Leo libros-14.xml con XStream y lo almaceno en una lista de libros
        leerLibros14XmlXstream();
//        Creo libros.json
        escribirLibrosJson();
    }

    public static void leerLibros14XmlXstream() throws FileNotFoundException {
        System.out.println("LEYENDO ARCHIVO XML....");
//        Creamos XStream y especificamos el DomDriver
        XStream xStream = new XStream(new DomDriver("UTF-8"));
//        Disminuimos la seguridad
        xStream.addPermission(AnyTypePermission.ANY);
//        ALIAS y IMMPLICITCOLLECTION
        xStream.alias("libro", Libro.class);
        xStream.alias("libros", ListaLibros.class);
        xStream.alias("autor", String.class);
        xStream.addImplicitCollection(ListaLibros.class, "libros");
//      Creamos objeto y lo recuperamos haciendo parse
        listaLibros = (ListaLibros) xStream.fromXML(new FileInputStream(ficheroXml));
        System.out.println("ARCHIVO XML LEÍDO CON ÉXITO");
    }

    public static void escribirLibrosJson() throws IOException {
//        Creamos JsonWriter
        JsonWriter writer = new JsonWriter(new FileWriter(ficheroJson));
//        Recupero la lista de objetos tipo libro
        List<Libro> libros = listaLibros.getLibros();
//        ESCRIBIMOS=============================
        writer.beginObject();
        writer.name("libros");
        writer.beginArray();
        for (Libro libro : libros) {
            writer.beginObject();
            writer.name("isbn").value(libro.getISBN());
            writer.name("titulo").value(libro.getTitulo());
            writer.name("autores");
            writer.beginArray();
            for (int i = 0; i < libro.getAutores().size(); i++) {
                writer.value(libro.getAutores().get(i));
            }
            writer.endArray();
            writer.name("editorial").value(libro.getEditorial());
            writer.endObject();
        }
        writer.endArray();
        writer.endObject();
//        Cierro writer
        writer.close();
    }
}
