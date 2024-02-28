package psp_ut1_ejercicio09;

public class Coche extends Thread{

	private String nombre;
	private Puente puente;
	
	public Coche() {
		this.nombre = "";
		this.puente = null;
	}
	
	public Coche(String nombre, Puente puente) {
		this.nombre = nombre;
		this.puente = puente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		for (int i = 1; i <= puente.getTramo(); i++) {
			if (i == puente.getIncioPuente()) {
				puente.tramoPeligroso(this);
				i = puente.getFinPuente();
			} else {
				System.out.println("El " + this.getNombre() + " va por el km " + (i) + " de la carretera.");
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
