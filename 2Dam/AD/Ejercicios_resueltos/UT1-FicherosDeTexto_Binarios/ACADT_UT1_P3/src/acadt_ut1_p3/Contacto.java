package acadt_ut1_p3;

import java.io.Serializable;

public class Contacto implements Serializable {

	private String nombre;
	private String apellido;
	private String email;
	private int telefono;

	public Contacto(String nombre, String apellido, String email, int telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}

	// GETTER Y SETTER
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	// METODOS PROPIOS
	public void mostrar() {
		System.out.println("=== " + nombre + " ===");
		System.out.println("Apellido: " + apellido);
		System.out.println("Email: " + email);
		System.out.println("Telefono: " + telefono);
	}

	public boolean busquedaOK(String cadena) {
		cadena.toLowerCase();
		String nombre = this.nombre.toLowerCase();
		String apellido = this.apellido.toLowerCase();
		if (nombre.contains(cadena) || apellido.contains(cadena)) {
			return true;
		} else {
			return false;
		}
	}
}
