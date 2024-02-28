package com.iesvjp.mongodb.EjemplosMongoDB;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Sorts.*;

public class Find {

	/**
	 *
	 * @param db         instancia de la BD
	 * @param collection nombre de la colección
	 */
	public static void consultarArticulos_Informatica_20_30(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		Bson filter = Filters.and(Filters.eq("categoria", "Informática"), Filters.gte("stock", 20),
				Filters.lte("stock", 30));
		FindIterable<Document> documents = coleccion.find(filter);
		MongoCursor<Document> cursor = documents.iterator();
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
	public static void consultarArticulos_Informatica(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		Bson filter = Filters.eq("categoria", "Informática");
		MongoCursor<Document> cursor = coleccion.find(filter).sort(ascending("denominacion")).iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		cursor.close();
	}

}
