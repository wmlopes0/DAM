package personajes;

/**
 * @author Walter
 */
public abstract class Operador extends Personaje {

	private boolean reconocido;
	private boolean enPuertaDeSalida;

	public boolean isReconocido() {
		return this.reconocido;
	}

	public void setReconocido(boolean reconocido) {
		this.reconocido = reconocido;
	}

	public boolean isEnPuertaDeSalida() {
		return this.enPuertaDeSalida;
	}

	public void setEnPuertaDeSalida(boolean enPuertaDeSalida) {
		this.enPuertaDeSalida = enPuertaDeSalida;
	}

	/**
	 * CONSTRUCTORES
	 */
	public Operador() {
		// TODO - implement Operador.Operador
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 * @param turno
	 * @param idCeldaActual
	 * @param marca
	 */
	public Operador(String nombre, int turno, int idCeldaActual, char marca) {
		// TODO - implement Operador.Operador
		throw new UnsupportedOperationException();
	}

	/**
	 * MÉTODOS DE LA CLASE OPERADOR
	 * @param pw
	 */
	@Override
	public abstract void realizarAcciones(java.io.PrintWriter pw);

	public boolean comprobarPuertaSalida() {
		// TODO - implement Operador.comprobarPuertaSalida
		throw new UnsupportedOperationException();
	}

}