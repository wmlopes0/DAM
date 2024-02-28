package psp_ut1_ejercicio10;

public class Main {

	public static void main(String[] args) {
		HiloPrincipal hp = new HiloPrincipal(5);
		Hilo h1 = new Hilo(1, hp);
		Hilo h2 = new Hilo(2, hp);
		Hilo h3 = new Hilo(3, hp);
		
		h1.start();
		h2.start();
		h3.start();
	}
}
