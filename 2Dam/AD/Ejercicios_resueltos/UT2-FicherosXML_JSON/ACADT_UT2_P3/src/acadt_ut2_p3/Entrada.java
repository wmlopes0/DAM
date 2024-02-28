package acadt_ut2_p3;

import java.io.Serializable;


public class Entrada implements Serializable {

	private String titulo;
	private String descripcion;

	// Constructores
	public Entrada() {
	}

	public Entrada(String titulo, String descripcion) {
		this.titulo = titulo;
		this.descripcion = descripcion;
	}

	// Getter y Setter
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	//Mostrar
	public void mostrar() {
		System.out.println("-------------------------");
		System.out.println("TÍTULO: "+titulo);
		System.out.println("DESCRIPCIÓN: "+ descripcion);
		System.out.println("-------------------------");
	}
}
