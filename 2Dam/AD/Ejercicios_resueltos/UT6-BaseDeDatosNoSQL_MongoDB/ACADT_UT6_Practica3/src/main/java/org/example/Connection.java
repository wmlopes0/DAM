package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Connection {

    //Variables globales
    private static MongoClient cliente;

    /**
     * @param database nombre de la base de datos a la que queremos conectarnos
     * @return devuelve un objeto de tipo MongoDatabase
     */
    public static MongoDatabase connect(String database) {
        // Connect to the MongoDB server with internal connection pooling
        cliente = MongoClients.create();
        // Select the database
        MongoDatabase db = cliente.getDatabase(database);
        return db;
    }

    /**
     * Cerramos la conexión con la base de datos
     */
    public static void disconnect() {
        // Disconnect to the MongoDB server
        cliente.close();
    }
}
