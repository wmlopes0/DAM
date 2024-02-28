package psp_ut1_ejercicio5;

public class PintaDiezVeces extends Thread{
	private String texto;
	
	public PintaDiezVeces(String texto) {
		this.texto = texto;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this.getId() + " (PRIO: " + getPriority() + ") " + this.texto);
			
		}
	}
}
