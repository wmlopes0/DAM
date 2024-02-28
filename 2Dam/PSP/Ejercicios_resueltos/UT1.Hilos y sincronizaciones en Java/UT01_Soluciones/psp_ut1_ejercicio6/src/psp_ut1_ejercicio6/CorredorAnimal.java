package psp_ut1_ejercicio6;

public class CorredorAnimal extends Thread{
	private String nombre;

	public CorredorAnimal(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(i + " " + this.getNombre());
		}
	}
	
}
