package com.iesvjp.mongodb.EjemplosMongoDB;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import com.mongodb.client.model.Filters;

public class Delete {
	
	/**
	 *
	 * @param db         parámetro de tipo MongoDatabase
	 * @param collection nombre de la colección a consultar
	 */
	public static void borrarUno(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		DeleteResult del = coleccion.deleteOne(Filters.eq("denominacion", "Portatil Acer"));
		System.out.println("Se han borrado: " + del.getDeletedCount());
	}

	/**
	 *
	 * @param db         parámetro de tipo MongoDatabase
	 * @param collection nombre de la colección a consultar
	 */
	public static void borrarTodos(MongoDatabase db, String collection) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		DeleteResult del = coleccion.deleteMany(Filters.exists("_id"));
		System.out.println("Se han borrado: " + del.getDeletedCount());
	}
}
