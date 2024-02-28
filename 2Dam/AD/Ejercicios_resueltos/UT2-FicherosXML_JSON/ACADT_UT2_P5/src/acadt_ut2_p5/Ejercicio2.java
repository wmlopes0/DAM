package acadt_ut2_p5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Ejercicio2 {

	public static List<Producto> productos = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// Leer productos.json
		lecturaJson();
		// Mostrar lista de productos
		mostrarProductos();
	}

	public static void lecturaJson() throws IOException {
		System.out.println("Comenzando lectura de 'productos.json'...");

		// Creo objeto File y Gson
		File fichero = new File("productos.json");
		Gson gson = new Gson();

		// Abro flujos
		FileInputStream fis = new FileInputStream(fichero);
		JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));

		// LECTURA
		reader.beginArray();
		while (reader.hasNext()) {
			Producto producto = gson.fromJson(reader, Producto.class);// Leemos y convertimos a objeto
			productos.add(producto);// Añadimos el objeto a la lista
		}
		reader.endArray();

		// Cierro flujos
		reader.close();
		fis.close();
		System.out.println("Lectura de 'productos.json'realizada con éxito!");
	}

	public static void mostrarProductos() {
		for (Producto producto : productos) {
			producto.mostrar();
		}
	}
}
