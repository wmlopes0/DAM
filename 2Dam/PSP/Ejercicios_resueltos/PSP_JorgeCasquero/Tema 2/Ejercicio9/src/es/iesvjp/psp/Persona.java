package es.iesvjp.psp;

public class Persona extends Thread {
	private String nombre;
	private String sexo;
	private Sala sala;

	public Persona(String nombre, String sexo, Sala sala) {
		this.nombre = nombre;
		this.sexo = sexo;
		this.sala = sala;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
	public void run() {
		try {
			this.sala.entrarAbailar(this);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}

	}

}
