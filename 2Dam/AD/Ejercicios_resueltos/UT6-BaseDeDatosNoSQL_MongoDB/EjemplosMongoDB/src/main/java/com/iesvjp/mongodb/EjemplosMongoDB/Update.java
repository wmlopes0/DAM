package com.iesvjp.mongodb.EjemplosMongoDB;

import static com.mongodb.client.model.Updates.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import com.mongodb.client.model.Filters;

public class Update {

	/**
	 *
	 * @param db         parámetro de tipo MongoDatabase
	 * @param collection nombre de la colección a consultar
	 */
	public static void actualizarUno(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		UpdateResult UpdateResult = coleccion.updateOne(Filters.eq("denominacion", "Portatil Acer"), set("pvp", 501));
		System.out.println("Se han seleccionado: " + UpdateResult.getMatchedCount());
	}

	/**
	 *
	 * @param db         parámetro de tipo MongoDatabase
	 * @param collection nombre de la colección a consultar
	 */
	public static void actualizarVarios(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		UpdateResult UpdateResult = coleccion.updateMany(Filters.eq("categoria", "Informática"), inc("pvp", 1));
		System.out.println("Se han seleccionado: " + UpdateResult.getMatchedCount());
		System.out.println("Se han modificado: " + UpdateResult.getModifiedCount());
	}

}
