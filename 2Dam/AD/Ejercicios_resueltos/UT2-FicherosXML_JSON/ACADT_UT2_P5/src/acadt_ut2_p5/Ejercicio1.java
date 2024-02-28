package acadt_ut2_p5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class Ejercicio1 {

	public static List<Empleado> empleados = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// Lectura
		lecturaJson();
		// Escritura
		escrituraJson();
	}

	// Este método lee de un fichero json y devuelve una lista con los objetos
	// leidos
	public static void lecturaJson() throws IOException {
		System.out.println("Empezando lectura de 'empleados.json'...\n");

		// Creo el objeto File con la ruta del fichero y el Gson
		File fichero = new File("empleados.json");
		Gson gson = new Gson();

		// ABRO FLUJOS
		FileInputStream fis = new FileInputStream(fichero);
		JsonReader reader = new JsonReader(new InputStreamReader(fis, "UTF-8"));

		// Observando el json,vemos que es un array de objetos empleados, procedemos a
		// leer
		reader.beginArray();// Abrimos el array

		while (reader.hasNext()) {// El JsonReader funciona como un iterador
			// Leemos la información utilizando el modelo Empleado
			Empleado emp = gson.fromJson(reader, Empleado.class);
			empleados.add(emp);// Añado el empleado a la lista
			System.out.println(emp.toString());
		}

		reader.endArray();// Cerramos el array

		// CIERRO FLUJOS
		reader.close();
		fis.close();

		System.out.println("\nLectura de 'empleados.json' realizada con éxito!");
	}

	// Este método escribirá en un fichero llamado 'workers.json' los objetos de la
	// lista empleados

	public static void escrituraJson() throws IOException {
		System.out.println("Empezando escritura de 'workers.json'...\n");

		// Creo el objeto File con la ruta del fichero
		File fichero = new File("workers.json");

		// Abro flujos
		JsonWriter writer = new JsonWriter(new FileWriter(fichero));

		// EMPEZAMOS LA ESCRITURA
		writer.beginArray();// Como es un array de empleados abrimos el array

		for (Empleado empleado : empleados) { // Recorremos la lista de empleados y escribimos
			writer.beginObject();// Empezamos empleado
			// Escribimos cada atributo uno a uno
			writer.name("nombre").value(empleado.getNombre());
			writer.name("apellidos").value(empleado.getApellidos());
			writer.name("edad").value(empleado.getEdad());
			writer.name("puestos"); // Puestos es un Array, asique establecemos nombre y abrimos array
			writer.beginArray();
			List<String> puestos = empleado.getPuestos(); // Lista auxiliar
			for (String puesto : puestos) {
				writer.value(puesto);
			}
			writer.endArray();
			writer.endObject();
		}

		// ACABAMOS LA ESCRITURA
		writer.endArray();

		// Cerramos flujos
		writer.close();

		System.out.println("\nFichero generado con éxito!");
	}
}
