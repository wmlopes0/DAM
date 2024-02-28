package acadt_ut2_p5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Libro implements Serializable {

	private String ISBN;
	private String titulo;
	private List<String> autores;
	private String editorial;

	public Libro(String iSBN, String titulo, List<String> autores, String editorial) {
		super();
		ISBN = iSBN;
		this.titulo = titulo;
		this.autores = autores;
		this.editorial = editorial;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<String> getAutores() {
		return autores;
	}

	public void setAutores(List<String> autores) {
		this.autores = autores;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	// MOSTRAR
	public void mostrar() {
		System.out.println("----- LIBRO -----");
		System.out.println("ISBN:" + ISBN);
		System.out.println("Título:" + titulo);
		System.out.println("Editorial: " + editorial);
		System.out.println("Autores:");
		for (String string : autores) {
			System.out.println(string);
		}
	}
}
