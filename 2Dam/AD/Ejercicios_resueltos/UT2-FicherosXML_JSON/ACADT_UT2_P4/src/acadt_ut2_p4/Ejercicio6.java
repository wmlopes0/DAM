package acadt_ut2_p4;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Ejercicio6 {

	public static void main(String[] args) {
		// Creo Lista de libros
		List<Libro> lLibros = new ArrayList<>();
		// Relleno la lista
		rellenarLlibros(lLibros);
		// Creo objeto Gson
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String listaLibrosJson = prettyGson.toJson(lLibros);
		// Muestro el formato json
		System.out.println(listaLibrosJson);
		// Deserializo de nuevo y muestro
		deserializarJson(listaLibrosJson);

	}

	public static void rellenarLlibros(List<Libro> lLibros) {
		Libro libro1 = new Libro("978-1-2345-6789-0", "El Vuelo de las Estrellas", "Mariana L. Ruiz",
				"Ediciones del Cielo");
		Libro libro2 = new Libro("978-2-3456-7890-1", "Los Secretos del Bosque", "Carlos F. Alonso",
				"Bosque Editorial");
		Libro libro3 = new Libro("978-3-4567-8901-2", "Historias del Pasado", "Pedro M. Soto",
				"Tiempo y Memoria Ediciones");
		Libro libro4 = new Libro("978-4-5678-9012-3", "Caminando entre Nubes", "Laura Hernández", "Vuelos Literarios");
		Libro libro5 = new Libro("978-5-6789-0123-4", "El Misterio de la Ciudad Olvidada", "Javier E. Torres",
				"Urbano Editores");
		lLibros.add(libro1);
		lLibros.add(libro2);
		lLibros.add(libro3);
		lLibros.add(libro4);
		lLibros.add(libro5);
	}

	public static void deserializarJson(String json) {
		List<Libro> libros;

		// Creo gson
		Gson gson = new Gson();
		// Obtengo el tipo
		Type tipoLista = new TypeToken<List<Libro>>() {
		}.getType();
		// Asigno json a la lista Libros
		libros = gson.fromJson(json, tipoLista);
		// Muestro lista
		for (Libro libro : libros) {
			libro.mostrar();
		}
	}
}
