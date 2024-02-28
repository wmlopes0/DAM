package psp_ut1_ejercicio15;

public class Letra extends Thread {
	private char letra;
	private HiloPrincipal hilo;
	
	public Letra(HiloPrincipal hilo, char letra) {
		this.hilo = hilo;
		this.letra = letra;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			i = hilo.pintarA();
		}
		
	}
}
