package acadt_ut2_p4;

import java.util.List;


public class Empleado {
	
	private String nombre;
	private String apellidos;
	private int edad;
	private List<String> puestos;

	public Empleado() {
	}

	public Empleado(String nombre, String apellidos, int edad, List<String> puestos) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.puestos = puestos;
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public List<String> getPuestos() {
		return puestos;
	}

	public void setPuestos(List<String> puestos) {
		this.puestos = puestos;
	}

	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", apellidos=" + apellidos + ", edad=" + edad + ", puestos=" + puestos
				+ "]";
	}
}
