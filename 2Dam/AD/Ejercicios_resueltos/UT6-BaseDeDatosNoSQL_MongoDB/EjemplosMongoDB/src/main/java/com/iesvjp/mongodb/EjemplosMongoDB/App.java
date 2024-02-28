package com.iesvjp.mongodb.EjemplosMongoDB;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

/**
 * Acceso a MongoDB desde Java
 *
 */
public class App {
	private static MongoClient cliente;

	public static void main(String[] args) {

		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
		rootLogger.setLevel(Level.OFF);

		MongoDatabase db = connect("pruebas");
		// MongoDatabase db = Connection.connectWithCodecRegistry("pruebas");

		// Una vez establecida la conexión puedo hacer consultas sobre las colecciones
		// de dicha BD
		// showCollection(db, "articulos");
		// showCollectionByFields(db, "articulos");
		// showCollectionPOJO(db, "articulos");
		
		// Find.consultarArticulos_Informatica_20_30(db, "articulos");
		// Find.consultarArticulos_Informatica(db, "articulos");
		
		// Aggregation.consultaAgregadosMatch(db, "articulos");
		// Aggregation.consultaAgregadosMatchProject(db, "articulos");
		// Aggregation.consultaAgregadoMediaCurso(db, "articulos");
		// Aggregation.consultaMediaCursoOut(db, "articulos");
		// Aggregation.getPrecioMedioEscritorio(db, "articulos");
		
		// Update.actualizarUno(db, "articulos");
		// Update.actualizarVarios(db, "articulos");
		
		// Delete.borrarUno(db, "articulos");
		// Delete.borrarTodos(db, "articulos");
		
		// Find.consultarArticulos_Informatica(db, "articulos");
	}

	/**
	 *
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

	/**
	 * @param db         parámetro de tipo MongoDatabase
	 * @param collection nombre de la colección a consultar
	 */
	public static void showCollection(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		List<Document> consulta = coleccion.find().into(new ArrayList<Document>());
		for (int i = 0; i < consulta.size(); i++) {
			System.out.println(" - " + consulta.get(i).toString());
		}
	}

	/**
	 * @param db         parámetro de tipo MongoDatabase
	 * @param collection nombre de la colección a consultar
	 */
	public static void showCollectionByFields(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		List<Document> consulta = coleccion.find().into(new ArrayList<Document>());
		for (int i = 0; i < consulta.size(); i++) {
			Document amig = consulta.get(i);
			System.out.println(" - " + amig.getString("denominacion") + "-" + amig.get("pvp") + "-"
					+ amig.getString("categoria") + "-" + amig.getInteger("uv"));
		}
	}

	/**
	 *
	 * @param db         parámetro de tipo MongoDatabase
	 * @param collection nombre de la colección a consultar
	 */
	public static void showCollectionPOJO(MongoDatabase db, String collection) {
		MongoCollection<ArticuloPOJO> amigos = db.getCollection("articulos", ArticuloPOJO.class);
		MongoCursor<ArticuloPOJO> cursor = amigos.find().iterator();
		while (cursor.hasNext()) {
			ArticuloPOJO amigo = cursor.next();
			System.out.println(amigo);
		}
		cursor.close();
	}

	/**
	 *
	 * @param db         instancia de la BD
	 * @param collection nombre de la colección
	 */
	public static void consultarDocumentos(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		MongoCursor<Document> cursor = coleccion.find().iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			System.out.println(doc.toJson());
		}
		cursor.close();
	}
}
