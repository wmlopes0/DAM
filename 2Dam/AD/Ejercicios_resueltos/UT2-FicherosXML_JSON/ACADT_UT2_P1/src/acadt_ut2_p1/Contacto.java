package acadt_ut2_p1;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

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

	public void rellenarContacto() {
		System.out.println("RELLENA CONTACTO:");
		this.nombre = pedirCadena("Introduce el nombre: ");
		this.apellidos = pedirCadena("Introduce los apellidos: ");
		this.email = pedirCadena("Introduce el email: ");
		this.telefono = pedirEntero("Introduce el numero de telefono:");

	}

	private int pedirEntero(String texto) {
		Scanner s = new Scanner(System.in);
		int numero;
		System.out.println(texto);
		try {
			numero = s.nextInt();

		} catch (InputMismatchException e) {
			System.out.println("ERROR.NO PUEDES INTRODUCIR UNA LETRA.");
			numero = pedirEntero(texto);
		}
		return numero;
	}

	private String pedirCadena(String texto) {
		Scanner s = new Scanner(System.in);
		System.out.println(texto);
		return s.nextLine();
	}

	public void mostrarContacto() {
		System.out.println("C O N T A C T O");
		System.out.println("Nombre: " + nombre);
		System.out.println("Apellidos: " + apellidos);
		System.out.println("Email: " + email);
		System.out.println("Teléfono: " + telefono);
		System.out.println("----------------------------");
	}
}
