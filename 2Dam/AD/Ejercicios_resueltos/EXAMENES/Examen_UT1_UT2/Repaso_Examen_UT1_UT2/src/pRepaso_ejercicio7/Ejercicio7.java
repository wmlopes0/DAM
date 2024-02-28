package pRepaso_ejercicio7;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;

//=================================================
//Finalmente, procesa el fichero “esdla.json” cargando su información en memoria y
//mostrando el contenido por pantalla para comprobar que se ha generado
//correctamente (muestra el contenido de una manera “amigable” o “pretty”)
//ENUNCIADO========================================
public class Ejercicio7 {
    //    Variables globales
    public static File esdlaJson = new File("src/pRepaso_ejercicio7/esdla.json");

    public static void main(String[] args) throws IOException {
//    Leemos esdla.json y mostramos su contenido
      Esdla esdla = leerEsdlaJson();
//      Mostramos json en formato Pretty
        esdlaPrettyJson(esdla);
    }

    public static Esdla leerEsdlaJson() throws IOException {
//        Creamos Gson
        Gson gson = new Gson();
//        Creamos input
        InputStream fis = new FileInputStream(esdlaJson);
//        JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));
//        LEEMOS ===============================================
        EsdlaWrapper esdlaWrapper = gson.fromJson(reader, EsdlaWrapper.class);
        Esdla esdla = esdlaWrapper.getEsdla();
//        Cierro reader
        reader.close();
        return esdla;
    }

    public static void esdlaPrettyJson(Esdla esdla){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(esdla,Esdla.class));
    }
}
