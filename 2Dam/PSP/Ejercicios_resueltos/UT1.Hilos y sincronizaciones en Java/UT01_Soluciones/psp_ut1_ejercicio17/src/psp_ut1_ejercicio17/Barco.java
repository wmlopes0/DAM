package psp_ut1_ejercicio17;

public class Barco extends Thread{
	private HiloPrincipal hilo;
	private String nombre;

	public Barco(String nombre, HiloPrincipal hilo) {
		this.nombre = nombre;
		this.hilo = hilo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				hilo.cruzarPuenteBarco(nombre);
				Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}