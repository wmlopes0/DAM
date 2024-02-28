package es.iesvjp.psp;

public class Jugador extends Thread {
	private String nombre;
	private Pista pista;

	public Jugador(String nombre, Pista pista) {
		this.nombre = nombre;
		this.pista = pista;
	}

	@Override
	public void run() {
		try {
			pista.jugar(nombre);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
