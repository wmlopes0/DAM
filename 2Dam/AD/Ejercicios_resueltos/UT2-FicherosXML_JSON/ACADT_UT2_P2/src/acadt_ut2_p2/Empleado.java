package acadt_ut2_p2;

public class Empleado implements Comparable<Empleado> {
	private int id;
	private String nombre;
	private String apellido;
	private int sueldo;

	public Empleado(int id, String nombre, String apellido, int sueldo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sueldo = sueldo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	// MÉTODOS PROPIOS

	public void mostrarEmpleado() {
		System.out.print(nombre+" "+apellido+", sueldo: "+sueldo+"€\n");
	}

	@Override
	public int compareTo(Empleado o) {
		if (this.sueldo > o.sueldo) {
			return 1;
		} else {
			if (this.sueldo < o.sueldo) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
