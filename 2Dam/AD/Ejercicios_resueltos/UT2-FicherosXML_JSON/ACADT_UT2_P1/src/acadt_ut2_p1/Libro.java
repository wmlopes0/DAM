package acadt_ut2_p1;

import java.io.Serializable;

public class Libro implements Serializable {
	private String isbn;
	private String titulo;
	private String autor;
	private String editorial;
	
	public Libro(String isbn, String titulo, String autor, String editorial) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	//Mostrar Libro
	public void mostrar() {
		System.out.println("-----------------------");
		System.out.println("ISBN: "+isbn);
		System.out.println("Título: "+titulo);
		System.out.println("Autor: "+autor);
		System.out.println("Editorial: "+editorial);
		System.out.println("-----------------------");
	}
	
}
