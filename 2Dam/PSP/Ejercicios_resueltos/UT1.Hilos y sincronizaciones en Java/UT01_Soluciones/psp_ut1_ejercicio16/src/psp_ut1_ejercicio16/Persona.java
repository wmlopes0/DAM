package psp_ut1_ejercicio16;

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
		try {
			for (int i = 0; i < 2; i++) {
				hilo.put(getNombre());
				hilo.espera();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
