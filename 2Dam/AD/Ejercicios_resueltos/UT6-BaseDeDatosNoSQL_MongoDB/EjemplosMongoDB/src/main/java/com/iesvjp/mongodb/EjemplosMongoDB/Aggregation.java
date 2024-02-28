package com.iesvjp.mongodb.EjemplosMongoDB;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Accumulators.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Sorts.*;

public class Aggregation {

	/**
	 *
	 * @param db         instancia de la BD
	 * @param collection nombre de la colección
	 */
	public static void consultaAgregadosMatch(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		MongoCursor<Document> cursor = coleccion.aggregate(Arrays.asList(match(eq("categoria", "Deportes"))))
				.iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	/**
	 *
	 * @param db         instancia de la BD
	 * @param collection nombre de la colección
	 */
	public static void consultaAgregadosMatchProject(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		MongoCursor<Document> cursor = coleccion.aggregate(Arrays.asList(match(eq("categoria", "Deportes")),
				project(fields(include("denominacion", "pvp"), fields(exclude("_id")))))).cursor();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		cursor.close();
	}

	/**
	 *
	 * @param db         instancia de la BD
	 * @param collection nombre de la colección
	 */
	public static void consultaAgregadoMediaCurso(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		MongoCursor<Document> cursor = coleccion
				.aggregate(Arrays.asList(group("$categoria", avg("preciomedio", "$pvp")))).cursor();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		cursor.close();
	}

	/**
	 *
	 * @param db         instancia de la BD
	 * @param collection nombre de la colección
	 */
	public static void consultaMediaCursoOut(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		coleccion.aggregate(Arrays.asList(group("$categoria", avg("preciomedio", "$pvp")), out("preciomedio")))
				.toCollection();
		MongoCollection<Document> edadmedia = db.getCollection("preciomedio");
		System.out.println("Número de documentos en la colección: " + edadmedia.countDocuments());
	}

	/**
	 *
	 * @param db         instancia de la BD
	 * @param collection nombre de la colección
	 */
	public static void getPrecioMedioEscritorio(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		Bson match = match(eq("categoria", "Escritorio"));
		Bson group = group("$categoria", avg("preciomedio", "$pvp"));
		Bson sort = sort(descending("categoria"));
		List<Document> results = coleccion.aggregate(Arrays.asList(match, group, sort)).into(new ArrayList<Document>());
		for (int i = 0; i < results.size(); i++) {
			System.out.println(
					"precioMedioEscritorio " + results.get(i).toJson(JsonWriterSettings.builder().indent(true).build()));
		}
	}

}
