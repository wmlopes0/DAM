package psp_ut1_ejercicio13;

public class Main {

	public static void main(String[] args) {
		Hilo hilo = new Hilo();
		Tic tic = new Tic(hilo);
		Tac tac = new Tac(hilo);
		
		tic.start();
		tac.start();
		
	}

}
