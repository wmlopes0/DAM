package p4_Ejercicio6;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import p1_ejercicio3.*;

//ENUNCIADO===============================================
//Crea un programa en java que mediante un método serialize a JSON una lista de
//objetos Libro en formato “pretty” y posteriormente pase como parámetro dicha lista a
//otro método para que los deserialize.
//========================================================
public class Ejercicio6 {
    //    Variables globales
    public static List<Libro> listaLibroOrigen = new ArrayList<>();

    public static void main(String[] args) {
//        Relleno la lista de libros
        rellenarLibros();
//        Serializo a PrettyJson
        String librosPrettyJson = serializarLibrosPrettyJson();
//        Deserializo a la lista de objetos de nuevo
        deserializarLibrosPrettyJson(librosPrettyJson);
    }

    public static void rellenarLibros() {
        listaLibroOrigen.add(new Libro("978-3-16-148410-0", "Don Quijote de la Mancha", "Miguel de Cervantes", "Editorial Espanola"));
        listaLibroOrigen.add(new Libro("978-0-307-35193-7", "Cien años de soledad", "Gabriel García Márquez", "Editorial Sudamericana"));
        listaLibroOrigen.add(new Libro("978-0-7432-5422-2", "1984", "George Orwell", "Secker and Warburg"));
        listaLibroOrigen.add(new Libro("978-0-452-28423-4", "La odisea", "Homero", "Editorial Griega"));
        listaLibroOrigen.add(new Libro("978-0-14-044926-4", "La divina comedia", "Dante Alighieri", "Editorial Italiana"));
        listaLibroOrigen.add(new Libro("978-0-679-73122-2", "El gran Gatsby", "F. Scott Fitzgerald", "Charles Scribner's Sons"));
        listaLibroOrigen.add(new Libro("978-0-618-00221-3", "Matar a un ruiseñor", "Harper Lee", "J.B. Lippincott & Co."));
        listaLibroOrigen.add(new Libro("978-0-670-82162-4", "La carretera", "Cormac McCarthy", "Alfred A. Knopf"));
        listaLibroOrigen.add(new Libro("978-0-7432-7356-5", "El código Da Vinci", "Dan Brown", "Doubleday"));
        listaLibroOrigen.add(new Libro("978-0-307-27705-5", "Los juegos del hambre", "Suzanne Collins", "Scholastic"));
    }

    public static String serializarLibrosPrettyJson() {
//        Creamos nuevo GSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String librosPrettyJson = gson.toJson(listaLibroOrigen);
        System.out.println("=============== LIBROS EN PRETTY JSON ===============");
        System.out.println(librosPrettyJson);
        return librosPrettyJson;
    }

    public static void deserializarLibrosPrettyJson(String json) {
        System.out.println("================ MOSTRANDO OBJETOS DESERIALIZADOS =================");
//        Creamos un nuevo gson
        Gson gson = new Gson();
//        Creamos un Type
        Type type = new TypeToken<List<Libro>>(){}.getType();
//        Creamos lista de libros
        List<Libro> listaLibros = gson.fromJson(json,type);
//        Mostramos la lista
        for (Libro libro :
                listaLibros) {
            libro.mostrar();
        }
    }
}
