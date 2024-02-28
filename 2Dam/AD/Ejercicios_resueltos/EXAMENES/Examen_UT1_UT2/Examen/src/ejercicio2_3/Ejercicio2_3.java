package ejercicio2_3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
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
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio2_3 {
    //    Variables globales
    public static File disneyXml = new File("src/ejercicio2_3/disney.xml");
    public static File opcionBxml = new File("src/ejercicio2_3/opcionB.xml");
    public static File opcionCjson = new File("src/ejercicio2_3/opcionC.json");
    public static Disney disney;
    public static DisneyWrapper disneyJson;

//    MAIN
    public static void main(String[] args) {
        int opcion;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    try {
                        deserializarDisneyXml();
                    } catch (FileNotFoundException e) {
                        System.out.println("ERROR: ARCHIVO NO ENCONTRADO.");
                    }
                    break;
                case 2:
                    //Controlo que se haya cargado en memoria primero
                    if (disney != null) {
                        realizarModificacionesDisney();//Realizo modificaciones
                        try {
                            escribirOpcionBXml();//Escribo con DOM
                        } catch (ParserConfigurationException e) {
                            throw new RuntimeException(e);
                        } catch (TransformerException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("ERROR: PRIMERO NECESITAS CARGAR EN MEMORIA EL FICHERO 'disney.xml'.");
                    }
                    break;
                case 3:
                    //Controlo que se haya cargado en memoria primero
                    if (disney != null) {
                        realizarModificacionesDisney();//Realizo modificaciones
                        try {
                            escribirOpcionCjson();//Genero el json
                        } catch (IOException e) {
                            System.out.println("ERROR: NO SE PUDO ESCRIBIR EL FICHERO 'obcionC.json'.");
                        }
                    } else {
                        System.out.println("ERROR: PRIMERO NECESITAS CARGAR EN MEMORIA EL FICHERO 'disney.xml'.");
                    }
                    break;
                case 4:
                    //Controlo que se haya creado el fichero opcionC.json
                    if (opcionCjson.exists()) {
                        try {
                            procesarOpcionCjson();
                            mostrarPrettyOpcionCjson();//Muestro por consola en formato PrettyJson
                        } catch (IOException e) {
                            System.out.println("ERROR: NO SE PUDO PROCESAR EL FICHERO 'obcionC.json'.");
                        }
                    } else {
                        System.out.println("ERROR: NECESITAS CREAR PRIMERO EL FICHERO 'opcionC.json'.");
                    }
                    break;
                case 5:
                    System.out.println("******************************");
                    System.out.println("******* ¡HASTA PRONTO! *******");
                    System.out.println("******************************");
                    break;
            }
        } while (opcion != 5);
    }

//    Mostrar menú
    public static int mostrarMenu() {
        Scanner s = new Scanner(System.in);
        int opcion;
        System.out.println("===== MENU =====");
        System.out.println("Opción 1: Deserializar y cargar en memoria 'disney.xml' mediante XStream.");
        System.out.println("Opción 2: Generar 'opcionB.xml' con DOM.");
        System.out.println("Opción 3: Generar 'opcionC.json'.");
        System.out.println("Opción 4: Procesar 'opcionC.json' y mostrar en formato Pretty.");
        System.out.println("Opción 5: SALIR.");
        System.out.println("=================");
        System.out.println("Por favor, introduzca una opción: ");
        try {
            opcion = s.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: No puedes introducir una letra.");
            opcion = mostrarMenu();//Vuelvo a pedir
        }
        return opcion;
    }

    //    OPCIÓN 1==============================================================
    public static void deserializarDisneyXml() throws FileNotFoundException {
//        Creamos un nuevo XStream
        XStream xStream = new XStream(new DomDriver("UTF-8"));
//        Disminuimos la seguridad
        xStream.addPermission(AnyTypePermission.ANY);
//        Utilizamos Alias para especificar las clases
        xStream.alias("disney", Disney.class);
        xStream.alias("franquicia", String.class);
        xStream.alias("peliculas", ListaPeliculas.class);
        xStream.alias("pelicula", String.class);
        xStream.alias("parque", Parque.class);
//        Creamos un objeto Disney y lo recuperamos
        disney = (Disney) xStream.fromXML(new FileInputStream(disneyXml));
        System.out.println("¡EL ARCHIVO HA SIDO CARGADO EN MEMORIA CON ÉXITO!");
    }

    //    OPCIÓN 2==============================================================
    public static void realizarModificacionesDisney() {
//        Eliminar la ultima franquicia
        disney.getFranquicias().remove(disney.getFranquicias().size() - 1);
//        Añadir un nuevo parque
        Parque parque = new Parque("Disneyland", "París");
        disney.getParques().add(parque);
    }

    public static void escribirOpcionBXml() throws ParserConfigurationException, TransformerException {
//        Creamos nueva instancia DocumentBuildFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        Creamos DocumentBuilder
        DocumentBuilder builder = dbf.newDocumentBuilder();
//        Obtenemos la implementación DOM
        DOMImplementation implementacion = builder.getDOMImplementation();
//        Creamos un Document
        Document opcionB = implementacion.createDocument(null, "disney", null);
//        Establecemos la version XML
        opcionB.setXmlVersion("1.0");
//        ===========================================================
//        =================== E S C R I B I M O S ===================
//        ===========================================================
//        FRANQUICIAS------------------------------------------------
        Element franquicias = opcionB.createElement("franquicias");//Creamos etiqueta franquicias
        opcionB.getDocumentElement().appendChild(franquicias);//Añadimos la etiqueta al elemento padre 'disney'
        for (String s : disney.getFranquicias()) {
            Element franquicia = opcionB.createElement("franquicia");//Creamos franquicia
            Text texto = opcionB.createTextNode(s);//Extraemos contenido
            franquicia.appendChild(texto);//Introducimos el contenido en la etiqueta
            franquicias.appendChild(franquicia);//Añadimos la franquicia a la etiqueta franquicias
        }
//        PELICULAS---------------------------------------------------
        Element peliculas = opcionB.createElement("peliculas");//Creamos etiqueta peliculas
        opcionB.getDocumentElement().appendChild(peliculas);//Añadimos la etiqueta al elemento padre 'disney'
//        Clásicos
        Element clasicos = opcionB.createElement("clasicos");//Creamos etiqueta clasicos
        peliculas.appendChild(clasicos);//Añadimos clasicos a peliculas
        for (String s : disney.getPeliculas().getClasicos()) {
            Element pelicula = opcionB.createElement("pelicula");//Creamos etiqueta pelicula
            Text texto = opcionB.createTextNode(s);
            pelicula.appendChild(texto);//Añadimos el contenido
            clasicos.appendChild(pelicula);//Añadimos la pelicula a clasicos
        }
//        Pixar
        Element pixar = opcionB.createElement("pixar");//Creamos etiqueta pixar
        peliculas.appendChild(pixar);//Añadimos pixar a peliculas
        for (String s : disney.getPeliculas().getPixar()) {
            Element pelicula = opcionB.createElement("pelicula");//Creamos etiqueta pelicula
            Text texto = opcionB.createTextNode(s);
            pelicula.appendChild(texto);//Añadimos el contenido
            pixar.appendChild(pelicula);//Añadimos la pelicula a pixar
        }
//        PARQUES-----------------------------------------------------
        Element parques = opcionB.createElement("parques");//Creo etiquete parques
        opcionB.getDocumentElement().appendChild(parques);//Añadimos la etiqueta al elemento padre 'disney'
        for (Parque p : disney.getParques()) {
            Element parque = opcionB.createElement("parque");//Creamos etiqueta parque
            parques.appendChild(parque);//Añadimos el parque a la etiqueta parques
            Element nombre = opcionB.createElement("nombre");
            Text texto = opcionB.createTextNode(p.getNombre());
            nombre.appendChild(texto);//Añadimos el contenido
            parque.appendChild(nombre);//Añadimos la etiqueta nombre al parque
            Element ciudad = opcionB.createElement("ciudad");//Creamos etiqueta ciudad
            texto = opcionB.createTextNode(p.getNombre());
            ciudad.appendChild(texto);//Añadimos el contenido
            parque.appendChild(ciudad);//Añadimos la etiqueta ciudad al parque
        }
//        CREAMOS EL FICHERO====================================================
//        Creamos nuevo origen
        Source origen = new DOMSource(opcionB);
//        Creamos nuevo resultado
        Result resultado = new StreamResult(opcionBxml);
//        Creamos un transformador
        Transformer transformador = TransformerFactory.newInstance().newTransformer();
//        VOLCAMOS
        transformador.transform(origen, resultado);
        System.out.println("¡EL FICHERO 'opcionB.xml' GENERADO CON ÉXITO!");
    }

    //    OPCIÓN 3==============================================================
    public static void escribirOpcionCjson() throws IOException {
//        Creamos JsonWriter
        JsonWriter writer = new JsonWriter(new FileWriter(opcionCjson));
//        ESCRIBIMOS=======================================================
        writer.beginObject();//Objeto wrapper para poder poner nombre al objeto disney
        writer.name("disney").beginObject();//Creo objeto disney
//      Franquicias-------------------------------------
        writer.name("franquicias").beginArray();
        for (String s : disney.getFranquicias()) {
            writer.value(s);
        }
        writer.endArray();
//        Peliculas------------------------------------
        writer.name("peliculas").beginObject();
        //Clasicos
        writer.name("clasicos").beginArray();
        for (String s : disney.getPeliculas().getClasicos()) {
            writer.value(s);
        }
        writer.endArray();
        //Pixar
        writer.name("pixar").beginArray();
        for (String s : disney.getPeliculas().getPixar()) {
            writer.value(s);
        }
        writer.endArray();
        writer.endObject();
//       PARQUES -------------------------------------
        writer.name("parques").beginArray();
        for (Parque p : disney.getParques()) {
            writer.beginObject();
            writer.name("nombre").value(p.getNombre());
            writer.name("ciudad").value(p.getCiudad());
            writer.endObject();
        }
        writer.endArray();
//        -------------------------------------
        writer.endObject();
        writer.endObject();
        //CERRAMOS WRITER
        writer.close();
        System.out.println("¡FICHERO 'opcionC.json' GENERADO CON ÉXITO!");
    }

    //    OPCIÓN 4==============================================================
    public static void procesarOpcionCjson() throws IOException {
//        Creamos Gson
        Gson gson = new Gson();
//        Creamos InputStream
        InputStream fis = new FileInputStream(opcionCjson);
//        Creamos el reader
        JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));
//        LEEMOS
        disneyJson = gson.fromJson(reader, DisneyWrapper.class);//He necesitado una clase wrapper para disney porque está envuelto en un objeto
//        CERRAMOS EL READER
        reader.close();
        System.out.println("¡FICHERO 'opcionC.json' SE HA PROCESADO CON ÉXITO!");
    }

    public static void mostrarPrettyOpcionCjson() {
        System.out.println("***********************************************");
        System.out.println("***** F O R M A T O   P R E T T Y J S O N *****");
        System.out.println("***********************************************");
//        Creamos PrettyGson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        .toJson y mostramos
        System.out.println(gson.toJson(disneyJson));
    }
}
