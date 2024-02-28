package es.iesvjp.psp;

public class Main {

	public static void main(String[] args) {
		String[] vNombres = { "Ana", "Natalia", "Victor", "Jesus", "Sergio", "Pepe", "Claudia", "Antonio", "Paula",
				"Jorge" };
		Banio banio = new Banio();
		Limpiador limpiador = new Limpiador(banio);
		Personas[] vPersonas = new Personas[10];
		for (int i = 0; i < vPersonas.length; i++) {
			vPersonas[i] = new Personas(vNombres[i], banio);
			vPersonas[i].start();
			if (i == 5) {
				limpiador.start();
			}
		}

	}

}
