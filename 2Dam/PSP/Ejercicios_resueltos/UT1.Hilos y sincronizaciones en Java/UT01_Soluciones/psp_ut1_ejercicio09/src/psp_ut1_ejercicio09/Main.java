package psp_ut1_ejercicio09;

public class Main {

	public static void main(String[] args) {
		Puente puente = new Puente();
		Coche coche1 = new Coche("Coche-1",puente);
		Coche coche2 = new Coche("Coche-2",puente);
		Coche coche3 = new Coche("Coche-3",puente);
		Coche coche4 = new Coche("Coche-4",puente);
		
		coche1.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		coche2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		coche3.start();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		coche4.start();
		
		
	}

}
