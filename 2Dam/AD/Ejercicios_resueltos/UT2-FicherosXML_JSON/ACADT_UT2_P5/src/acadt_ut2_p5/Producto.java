package acadt_ut2_p5;

public class Producto {

	private String referencia;
	private String nombre;
	private Float precio;
	private int existencias;

	// Constructores
	public Producto() {

	}

	public Producto(String referencia, String nombre, Float precio, int existencias) {
		super();
		this.referencia = referencia;
		this.nombre = nombre;
		this.precio = precio;
		this.existencias = existencias;
	}

	// Getter y Setter
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	// Metodos propios
	public void mostrar() {
		System.out.println("------ PRODUCTO ------");
		System.out.println("Referencia: " + referencia);
		System.out.println("Nombre: " + nombre);
		System.out.println("Precio: " + precio);
		System.out.println("Existencias: " + existencias);
	}

}
