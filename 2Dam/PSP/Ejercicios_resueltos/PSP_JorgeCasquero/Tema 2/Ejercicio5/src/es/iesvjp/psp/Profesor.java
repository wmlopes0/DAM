package es.iesvjp.psp;

public class Profesor extends Thread {
	private String nombre;
	private SalaProfesores sala;

	public Profesor(String nombre, SalaProfesores sala) {
		this.nombre = nombre;
		this.sala = sala;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public SalaProfesores getSala() {
		return sala;
	}

	public void setSala(SalaProfesores sala) {
		this.sala = sala;
	}

	@Override
	public void run() {
		System.out.println(getNombre() + " se dirige a la Sala de profesores.");		
			sala.entrarSala(this);
		
			sala.salirSala(this);
		
		}
	}

