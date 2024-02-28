package personajes;

/**
 * @author Walter
 */
public abstract class Personaje {

	private String nombre;
	private int turno;
	private int idCeldaActual;
	private char marca;
	private java.util.ArrayList<Character> lRuta;

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTurno() {
		return this.turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getIdCeldaActual() {
		return this.idCeldaActual;
	}

	public void setIdCeldaActual(int idCeldaActual) {
		this.idCeldaActual = idCeldaActual;
	}

	public char getMarca() {
		return this.marca;
	}

	public void setMarca(char marca) {
		this.marca = marca;
	}

	public java.util.ArrayList<Character> getLRuta() {
		return this.lRuta;
	}

	public void setLRuta(java.util.ArrayList<Character> lRuta) {
		this.lRuta = lRuta;
	}

	/**
	 * CONSTRUCTORES
	 */
	public Personaje() {
		// TODO - implement Personaje.Personaje
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 * @param turno
	 * @param idCeldaActual
	 * @param marca
	 */
	public Personaje(String nombre, int turno, int idCeldaActual, char marca) {
		// TODO - implement Personaje.Personaje
		throw new UnsupportedOperationException();
	}

	/**
	 * MÉTODOS DE LA CLASE PERSONAJE
	 * @param pw
	 */
	public abstract void realizarAcciones(java.io.PrintWriter pw);

	/**
	 * 
	 * @param ruta
	 */
	public void cargarMovimientos(char[] ruta) {
		// TODO - implement Personaje.cargarMovimientos
		throw new UnsupportedOperationException();
	}

	public void borrarPrimerMovimiento() {
		// TODO - implement Personaje.borrarPrimerMovimiento
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param movimiento
	 */
	public void insertarNuevoMovimiento(char movimiento) {
		// TODO - implement Personaje.insertarNuevoMovimiento
		throw new UnsupportedOperationException();
	}

	public boolean lRutaVacio() {
		// TODO - implement Personaje.lRutaVacio
		throw new UnsupportedOperationException();
	}

	public generico.Celda celdaActual() {
		// TODO - implement Personaje.celdaActual
		throw new UnsupportedOperationException();
	}

	public boolean comprobarTurno() {
		// TODO - implement Personaje.comprobarTurno
		throw new UnsupportedOperationException();
	}

	public int calcularSiguienteIdCelda() {
		// TODO - implement Personaje.calcularSiguienteIdCelda
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pw
	 */
	public void mover(java.io.PrintWriter pw) {
		// TODO - implement Personaje.mover
		throw new UnsupportedOperationException();
	}

}