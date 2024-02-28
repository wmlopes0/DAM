package com.iesvjp.psp;

import java.io.Serializable;

public class Persona implements Serializable {

	private static final long serialVersionUID = -1935777303855760517L;
	String nombre;
	int edad;

	public Persona() {
	}

	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
