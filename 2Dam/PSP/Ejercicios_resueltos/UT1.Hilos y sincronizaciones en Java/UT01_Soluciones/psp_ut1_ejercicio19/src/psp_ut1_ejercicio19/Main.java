package psp_ut1_ejercicio19;

public class Main {

	public static void main(String[] args) {
		Controlador control = new Controlador();
		TubHidrogeno th = new TubHidrogeno(control);
		TubOxigeno to = new TubOxigeno(control);
		
		th.start();
		to.start();
	}

}
