package es.iesvjp.psp;

public class Canario extends Thread{
	private String nombre;
	private Jaula jaula;
	
	public Canario(String nombre, Jaula jaula) {
		this.nombre = nombre;
		this.jaula = jaula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Jaula getJaula() {
		return jaula;
	}

	public void setJaula(Jaula jaula) {
		this.jaula = jaula;
	}
	
	@Override
	public void run() {
	this.jaula.comer(nombre);
	this.jaula.columpiarse(nombre);
	}
}
