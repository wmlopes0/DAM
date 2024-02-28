package psp_ut1_ejercicio17;


public class Persona extends Thread{
	private HiloPrincipal hilo;
	private String nombre;

	public Persona(String nombre, HiloPrincipal hilo) {
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
		while (hilo.getBarcosCruzados()<5) {
			try {
				hilo.cruzarPuentePersona(nombre);
				hilo.cruzando();
				Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
