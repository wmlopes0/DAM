package es.iesvjp.psp;

public class Principal {
	public static void main(String[] args) {
		String[] vNombres = { "Claudia", "Maria", "Sergio", "Antonio", "Jesus", "Ana", "Andrea" };
		Personas[] vPersonas = new Personas[vNombres.length];
		Ascensor ascensor = new Ascensor();
		float peso;
		for (int i = 0; i < vPersonas.length; i++) {
			peso = (float) ((Math.random() * 50) + 50);
			peso = Math.round(peso * 100) / 100;
			vPersonas[i] = new Personas(vNombres[i], peso, ascensor);
			vPersonas[i].start();
		}
	}
}
