package es.iesvjp.psp;

public class Main {
	public static void main(String[] args) {
		Persona[] vPersonas = new Persona[10];
		Sala sala = new Sala();

		for (int i = 1; i < vPersonas.length; i++) {
			if (i % 2 == 0) {
				vPersonas[i] = new Persona("Hombre-" + i, "hombre", sala);
				vPersonas[i].start();
			} else {
				vPersonas[i] = new Persona("Mujer-" + i, "mujer", sala);
				vPersonas[i].start();
			}
		}
	}

}
