package acadt_ut2_p4;

import com.google.gson.Gson;

public class Ejercicio4 {

	public static void main(String[] args) {
		//Tengo el Json
		String json = "{\"nombre\":\"Pepe\",\"apellidos\":\"Lopez\",\"edad\":20,\"puestos\":[\"Gerente\", \"Jefe de zona\"]}";
		
		//Creo objeto Gson
		Gson gson = new Gson();
		//Creo un empleado
		Empleado emp1 = gson.fromJson(json, Empleado.class);
		System.out.println(emp1.toString());
		
	}

}
