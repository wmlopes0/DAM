package psp_ut1_ejercicio2;

public class Main {

	public static void main(String[] args) {
		Hola hola = new Hola();
		Adios adios = new Adios();
		
		Thread tHola = new Thread(hola);
		Thread tAdios = new Thread(adios);
		
		tHola.start();
		tAdios.start();
	}

}
