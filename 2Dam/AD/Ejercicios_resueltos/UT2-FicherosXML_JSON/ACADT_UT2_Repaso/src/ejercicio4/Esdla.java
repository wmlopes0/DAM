package ejercicio4;

import java.io.Serializable;
import java.util.List;

public class Esdla {

	private String autor;
	private List<Ciudad> ciudades;
	private ListaPersonajes personajes;

	// Constructor
	public Esdla(String autor, List<Ciudad> ciudades, ListaPersonajes personajes) {
		super();
		this.autor = autor;
		this.ciudades = ciudades;
		this.personajes = personajes;
	}

	// Getter y Setter
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public List<Ciudad> getCiudades() {
		return ciudades;
	}

	public void setCiudades(List<Ciudad> ciudades) {
		this.ciudades = ciudades;
	}

	public ListaPersonajes getPersonajes() {
		return personajes;
	}

	public void setPersonajes(ListaPersonajes personajes) {
		this.personajes = personajes;
	}

	public void mostrar() {
		System.out.println("AUTOR: " + autor);
		mostrarCiudades();
		personajes.mostrar();
	}

	private void mostrarCiudades() {
		for (Ciudad ciudad : ciudades) {
			ciudad.mostrar();
		}
	}
}
