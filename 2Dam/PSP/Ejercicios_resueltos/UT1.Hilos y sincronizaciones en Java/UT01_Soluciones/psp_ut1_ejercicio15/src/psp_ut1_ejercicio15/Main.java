package psp_ut1_ejercicio15;

public class Main {

	public static void main(String[] args) {
		HiloPrincipal hilo = new HiloPrincipal();
		Letra letraA = new Letra(hilo,'A');
		Letra letraB = new Letra(hilo,'B');
		
		letraA.run();
		letraB.run();
	}

}
