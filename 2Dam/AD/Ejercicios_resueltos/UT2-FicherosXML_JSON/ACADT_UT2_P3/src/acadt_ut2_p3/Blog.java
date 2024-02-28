package acadt_ut2_p3;

import java.io.Serializable;
import java.util.List;

public class Blog implements Serializable {

	private String autor;
	private List<Entrada> entradas;

	// Constructores
	public Blog() {

	}

	public Blog(String autor, List<Entrada> entradas) {
		this.autor = autor;
		this.entradas = entradas;
	}

	// Getter y Setter
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public List<Entrada> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}

	// Mostrar
	public void mostrar() {
		System.out.println("=========================== B L O G ===========================");
		System.out.println("AUTOR: " + autor);
		for (Entrada entrada : entradas) {
			entrada.mostrar();
		}
		System.out.println("===============================================================");
	}

}
