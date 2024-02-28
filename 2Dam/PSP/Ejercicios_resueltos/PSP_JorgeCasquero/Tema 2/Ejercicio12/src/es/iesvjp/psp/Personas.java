package es.iesvjp.psp;

public class Personas extends Thread {
	private String nombre;
	private float peso;
	private Ascensor ascensor;

	public Personas(String nombre, float peso, Ascensor ascensor) {
		this.nombre = nombre;
		this.peso = peso;
		this.ascensor = ascensor;
	}

	@Override
	public void run() {
		try {
			this.ascensor.sube_persona(nombre, peso);
			Thread.yield();
			sleep(10);
			this.ascensor.baja_persona(nombre, peso);
			Thread.yield();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
