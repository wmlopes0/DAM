package org.example;

/**
 * Hello world!
 */

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;

public class App {
    public static void main(String[] args) {
        //Para quitar comentarios chungos
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
        //Creo conexión
        MongoDatabase db = Connection.connect("articulos");
        //Opción 1a
        opcion1a(db, "articulos");
        opcion1b(db, "articulos");
        opcion1c(db, "articulos");
        opcion1d(db, "articulos");
        //Cierro conexión
        Connection.disconnect();
    }

    public static void opcion1a(MongoDatabase db, String collection) {
        System.out.println("============================================================================================================================================");
        System.out.println("Obtén por cada categoría el número de artículos, el total unidades vendidas de artículos, y el total importe (la suma de los pvp*unidades)");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        MongoCollection<Document> coleccion = db.getCollection(collection);
        Bson group = group("$categoria", sum("numProductos", 1), sum("unidadesVendidas", "$uv"), sum("totalImporte", Document.parse("{'$multiply':['$pvp','$uv']}")));
        MongoCursor<Document> cursor = coleccion.aggregate(Arrays.asList(group)).cursor();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        cursor.close();
    }

    public static void opcion1b(MongoDatabase db, String collection) {
        System.out.println("============================================================================================================================================");
        System.out.println("Muestra la categoría que mayores ventas haya tenido junto con su importe.");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        MongoCollection<Document> coleccion = db.getCollection(collection);
        Bson group = group("$categoria", sum("totalImporte", Document.parse("{'$multiply':['$pvp','$uv']}")));
        Bson sort = sort(descending("totalImporte"));
        Bson limit = limit(1);
        MongoCursor<Document> cursor = coleccion.aggregate(Arrays.asList(group, sort, limit)).cursor();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        cursor.close();
    }

    public static void opcion1c(MongoDatabase db, String collection) {
        System.out.println("============================================================================================================================================");
        System.out.println("Muestra de la categoría Deportes, el número de unidades vendidas sus artículos, el total importe y la media de unidades vendidas.");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        MongoCollection<Document> coleccion = db.getCollection(collection);
        Bson match = match(eq("categoria", "Deportes"));
        Bson group = group("$categoria", sum("unidadesVendidas", "$uv"), avg("mediaUnidadesVendidas", "$uv"), sum("totalImporte", Document.parse("{'$multiply':['$pvp','$uv']}")));
        MongoCursor<Document> cursor = coleccion.aggregate(Arrays.asList(match, group)).cursor();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        cursor.close();
    }

    public static void opcion1d(MongoDatabase db, String collection) {
        System.out.println("============================================================================================================================================");
        System.out.println("Muestra el artículo más caro");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        MongoCollection<Document> coleccion = db.getCollection(collection);
        FindIterable<Document> documents = coleccion.find().sort(descending("pvp")).limit(1);
        MongoCursor<Document> cursor = documents.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        cursor.close();
    }

    //ARREGLAR
    public static void opcion1e(MongoDatabase db, String collection) {
        System.out.println("============================================================================================================================================");
        System.out.println("Muestra aquellos artículos cuyo stock actual (stock- uv) sea negativo");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        MongoCollection<Document> coleccion = db.getCollection(collection);
//        Bson filter = Filters.and(Filters.lt(Document.parse("{'$multiply':['$pvp','$uv']}"), 0));
        FindIterable<Document> documents = coleccion.find();//Filter
        MongoCursor<Document> cursor = documents.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
        cursor.close();
    }
}
