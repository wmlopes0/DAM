package acadt_ut2_p3;

import java.io.Serializable;

public class Contacto implements Serializable {
	
	private String nombre;
	private String apellidos;
	private String email;
	private int telefono;

	public Contacto() {
		this.nombre = "";
		this.apellidos = "";
		this.email = "";
		this.telefono = 0;
	}

	public Contacto(String nombre, String apellidos, String email, int telefono) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
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

	// Métodos propios
	public void mostrarContacto() {
		System.out.print("Nombre: " + nombre);
		System.out.print(" - Apellidos: " + apellidos);
		System.out.print(" - Email: " + email+"\n");
	}
}
