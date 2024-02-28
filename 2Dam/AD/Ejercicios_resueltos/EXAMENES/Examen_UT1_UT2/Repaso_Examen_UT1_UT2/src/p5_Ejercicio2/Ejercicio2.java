package p5_Ejercicio2;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

//ENUNCIADO=============================================
//Crea un programa en java para que lea el archivo productos.json (disponible en
//Moodle).
//======================================================
public class Ejercicio2 {
    //    Variables Globales
    public static File productosJson = new File("src/p5_ejercicio2/productos.json");

    public static void main(String[] args) throws IOException {
//        Lectura productosJSON
        lecturaProductosJson();
    }

    public static void lecturaProductosJson() throws IOException {
//        Creamos Gson
        Gson gson = new Gson();
//        Creamos InputStream
        InputStream fis = new FileInputStream(productosJson);
//        Creamos JsonReader y le pasamos el fis
        JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));
//        LEEMOS
        Producto producto;
        reader.beginArray();
        while (reader.hasNext()) {
            producto = gson.fromJson(reader, Producto.class);
            producto.mostrar();
        }
        reader.endArray();
//        CIERRO READER
        reader.close();
    }
}
