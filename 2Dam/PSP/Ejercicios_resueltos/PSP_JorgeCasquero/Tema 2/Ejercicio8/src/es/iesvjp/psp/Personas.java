package es.iesvjp.psp;

public class Personas extends Thread {
	private String nombre;
	private Banio banio;

	public Personas(String nombre, Banio banio) {
		this.nombre = nombre;
		this.banio = banio;
	}

	@Override
	public void run() {
		this.banio.usar_banio(nombre);
	}

}
