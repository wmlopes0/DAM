package acadt_ut2_p4;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Ejercicio5 {
	public static void main(String[] args) {
		String json = "[ {\"nombre\": \"Pepe\",\"apellidos\": \"Lopez\", "
				+ "\"edad\": 20,\"puestos\":[ \"Gerente\", \"Jefe de zona\"]},"
				+ "{ \"nombre\": \"Maria\", \"apellidos\": \"Gutierrez\", "
				+ "\"edad\": 30, \"puestos\":[\"Jefa RR.HH\"] }]";

		// Creo objeto gson
		Gson gson = new Gson();
		Type tipoListaEmpleados = new TypeToken<List<Empleado>>() {
		}.getType();
		List<Empleado> empleados = gson.fromJson(json, tipoListaEmpleados);

		for (Empleado empleado : empleados) {
			System.out.println(empleado.toString());
		}
	}
}
