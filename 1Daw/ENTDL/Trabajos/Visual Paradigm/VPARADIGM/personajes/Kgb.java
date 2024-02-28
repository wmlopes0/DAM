package personajes;

/**
 * @author Walter
 */
public abstract class Kgb extends Personaje {

	/**
	 * CONSTRUCTORES
	 */
	public Kgb() {
		// TODO - implement Kgb.Kgb
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nombre
	 * @param turno
	 * @param idCeldaActual
	 * @param marca
	 */
	public Kgb(String nombre, int turno, int idCeldaActual, char marca) {
		// TODO - implement Kgb.Kgb
		throw new UnsupportedOperationException();
	}

	/**
	 * MÉTODOS DE LA CLASE KGB
	 * Accion de catalogar
	 * @param pw
	 */
	public void catalogar(java.io.PrintWriter pw) {
		// TODO - implement Kgb.catalogar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param operador
	 * @param pw
	 */
	public abstract void mostrarInfoCatalogado(Operador operador, java.io.PrintWriter pw);

	/**
	 * 
	 * @param pw
	 */
	@Override
	public abstract void realizarAcciones(java.io.PrintWriter pw);

}