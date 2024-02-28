package com.iesvjp.mongodb.EjemplosMongoDB;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class Connection {
	static MongoClient mongoClient;

	public static MongoDatabase connectWithCodecRegistry(String database) {
		mongoClient = MongoClients.create(getClientSettings());
		MongoDatabase db = mongoClient.getDatabase(database);
		return db;
	}

	public static void desconectar() {
		mongoClient.close();
	}

	public static MongoClientSettings getClientSettings() {
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		MongoClientSettings clientSettings = MongoClientSettings.builder().codecRegistry(codecRegistry).build();
		return clientSettings;
	}
}

