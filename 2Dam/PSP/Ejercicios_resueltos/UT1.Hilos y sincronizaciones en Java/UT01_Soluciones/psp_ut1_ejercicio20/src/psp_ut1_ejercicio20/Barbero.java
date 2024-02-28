package psp_ut1_ejercicio20;

public class Barbero extends Thread{
	private String nombre;
	private Peluqueria peluqueria;
	
	public Barbero(String nombre, Peluqueria peluqueria) {
		this.nombre = nombre;
		this.peluqueria = peluqueria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Peluqueria getPeluqueria() {
		return peluqueria;
	}

	public void setPeluqueria(Peluqueria peluqueria) {
		this.peluqueria = peluqueria;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			try {
				peluqueria.cortarPelo(nombre);
				Thread.sleep(2500);
				peluqueria.terminaCortar(nombre);
				Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
