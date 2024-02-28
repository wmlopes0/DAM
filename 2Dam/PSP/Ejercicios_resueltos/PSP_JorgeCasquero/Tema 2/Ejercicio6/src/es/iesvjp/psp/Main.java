package es.iesvjp.psp;

public class Main {
	public static void main(String[] args) {
		Jaula jaula = new Jaula();
		Canario[] vCanarios = new Canario[5];

		for (int i = 0; i < vCanarios.length; i++) {
			vCanarios[i] = new Canario("canario - " + i, jaula);
			vCanarios[i].start();
		}
	}
}
