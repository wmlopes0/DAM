package es.iesvjp.psp;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		String[] nombre = { "Enrique", "Sergio", "Daniel" };
		Globos globos = new Globos();
		Amigo[] amigo = new Amigo[3];
		int total = 0;
		for (int i = 0; i < amigo.length; i++) {
			amigo[i] = new Amigo(nombre[i], globos);
			amigo[i].start();
			

		}
		for (int i = 0; i < amigo.length; i++) {
			amigo[i].join();
		}
		for (int i = 0; i < amigo.length; i++) {
			total += amigo[i].getsExplotados();
			
		}
		System.out.println("Total globos explotados " + total);

	}
}
