package personajes;

/**
 * @author Walter
 */
public class Robot extends Personaje {

	private java.util.List<Integer> registroCeldas;

	public java.util.List<Integer> getRegistroCeldas() {
		return this.registroCeldas;
	}

	public void setRegistroCeldas(java.util.List<Integer> registroCeldas) {
		this.registroCeldas = registroCeldas;
	}

	/**
	 * CONSTRUCTORES
	 */
	public Robot() {
		// TODO - implement Robot.Robot
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 * @param turno
	 * @param idCeldaActual
	 * @param marca
	 */
	public Robot(String nombre, int turno, int idCeldaActual, char marca) {
		// TODO - implement Robot.Robot
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 * @param turno
	 * @param idCeldaActual
	 * @param marca
	 * @param registroCeldas
	 */
	public Robot(String nombre, int turno, int idCeldaActual, char marca, java.util.List<Integer> registroCeldas) {
		// TODO - implement Robot.Robot
		throw new UnsupportedOperationException();
	}

	/**
	 * MÉTODOS DE LA CLASE ROBOT
	 * @param rutaCifrada
	 */
	public void descifrarRuta(String rutaCifrada) {
		// TODO - implement Robot.descifrarRuta
		throw new UnsupportedOperationException();
	}

	/**
	 * Registra los movimientos excepto el primero
	 */
	public void registrarMovimiento() {
		// TODO - implement Robot.registrarMovimiento
		throw new UnsupportedOperationException();
	}

	/**
	 * Obtiene el ultimo idCelda registrado distinto de -1
	 */
	public int obtenerUltimoIdCelda() {
		// TODO - implement Robot.obtenerUltimoIdCelda
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pw
	 */
	public void escanear(java.io.PrintWriter pw) {
		// TODO - implement Robot.escanear
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pw
	 */
	public void mostrarCeldasVisitadas(java.io.PrintWriter pw) {
		// TODO - implement Robot.mostrarCeldasVisitadas
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pw
	 */
	@Override
	public void realizarAcciones(java.io.PrintWriter pw) {
		// TODO - implement Robot.realizarAcciones
		throw new UnsupportedOperationException();
	}

}