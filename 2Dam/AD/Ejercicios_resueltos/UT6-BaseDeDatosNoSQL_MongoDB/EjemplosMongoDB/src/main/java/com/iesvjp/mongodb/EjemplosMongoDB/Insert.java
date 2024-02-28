package com.iesvjp.mongodb.EjemplosMongoDB;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Insert {

	/**
	 *
	 * @param db         instancia de la BD
	 * @param collection nombre de la colección
	 * @param amigo      objeto amigo
	 */
	public static void insertarUno(MongoDatabase db, String collection, ArticuloPOJO articulo) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		Document document = new Document();
		document.put("codigo", articulo.getCodigo());
		document.put("denominacion", articulo.getDenominacion());
		document.put("pvp", articulo.getPvp());
		document.put("categoria", articulo.getCategoria());
		document.put("uv", articulo.getUv());
		document.put("stock", articulo.getStock());
		coleccion.insertOne(document);
	}

	/**
	 *
	 * @param db         instancia de la BD
	 * @param collection nombre de la colección
	 * @param lista      ArrayList de objetos articulos
	 */
	public static void insertarMuchos(MongoDatabase db, String collection, ArrayList<ArticuloPOJO> lista) {
		MongoCollection<Document> coleccion = db.getCollection(collection);
		List<Document> listadocs = new ArrayList<Document>();
		for (ArticuloPOJO articulo : lista) {
			listadocs.add(new Document("codigo", articulo.getCodigo())
					.append("denominacion", articulo.getDenominacion())
					.append("pvp", articulo.getPvp())
					.append("categoria", articulo.getCategoria())
					.append("uv", articulo.getUv())
					.append("stock", articulo.getStock()));
		}
		coleccion.insertMany(listadocs);
		System.out.println("Número de documentos en la colección: " + coleccion.countDocuments());
	}

}


