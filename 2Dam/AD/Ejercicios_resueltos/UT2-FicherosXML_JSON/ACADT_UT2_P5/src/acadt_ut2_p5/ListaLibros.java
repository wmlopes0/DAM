package acadt_ut2_p5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaLibros implements Serializable {

	private List<Libro> libros = new ArrayList<>();;

	public ListaLibros(List<Libro> libros) {
		super();
		this.libros = libros;
	}

	public void add(Libro libro) {
		libros.add(libro);
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public void mostrar() {
		for (Libro libro : libros) {
			libro.mostrar();
		}
	}

}
