package acadt_ut2_p4;

import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Ejercicio1 {

	public static void main(String[] args) {
		//Me creo un empleado
		Empleado emp1 = new Empleado("Walter","Martín",25,Arrays.asList("Gerente","Jefe de zona"));
		
		//Creo objeto GSON (Nueva libreria)
		Gson gson = new Gson();
		//Muestro
		System.out.println(gson.toJson(emp1));
	
	}

}
