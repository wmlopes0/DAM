package acadt_ut2_p4;

import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Ejercicio3 {

	public static void main(String[] args) {
		// Me creo un empleado
		Empleado emp1 = new Empleado("Walter", "Martín", 25, Arrays.asList("Gerente", "Jefe de zona"));

		// Creo objeto GSON bonito (Nueva libreria)
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		// Creo clase envoltorio para serializar con el nombre del objeto
		SerializeEmpleado s_emp1 = new SerializeEmpleado(emp1);
		// Muestro
		System.out.println(prettyGson.toJson(s_emp1));

	}

}
