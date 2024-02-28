package acadt_ut2_p3;

import java.util.ArrayList;
import java.util.List;

public class ListaLibros {

	private List<Libro> lista = new ArrayList<>();

	// Constructor
	public ListaLibros() {
	}

	// Add y getter
	public void add(Libro libro) {
		lista.add(libro);
	}

	public List<Libro> getListaLibros() {
		return lista;
	}

	// Mostrar
	public void mostrar() {
		for (Libro libro : lista) {
			libro.mostrar();
		}
	}
}
