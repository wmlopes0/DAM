package psp_ut1_ejercicio5;

public class Main {

	public static void main(String[] args) {
		PintaDiezVeces primero = new PintaDiezVeces("Hola");
		PintaDiezVeces segundo = new PintaDiezVeces("Hello");
		PintaDiezVeces tercero = new PintaDiezVeces("Salut");
		PintaDiezVeces cuarto = new PintaDiezVeces("Hallo");
		PintaDiezVeces quinto = new PintaDiezVeces("Olá");
		
		primero.setPriority(10);
		segundo.setPriority(8);
		tercero.setPriority(6);
		cuarto.setPriority(4);
		quinto.setPriority(2);
		
		primero.start();
		segundo.start();
		tercero.start();
		cuarto.start();
		quinto.start();
	}

}
