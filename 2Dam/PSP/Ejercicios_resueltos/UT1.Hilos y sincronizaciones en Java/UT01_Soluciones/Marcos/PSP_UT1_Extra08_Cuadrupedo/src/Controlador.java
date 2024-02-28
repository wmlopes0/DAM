
public class Controlador {
	boolean patasTurno[]; // Array de booleanos que guarda si una pata se ha movido en un turno.

	public Controlador() {
		patasTurno = new boolean[4];
		iniciarPatas();
	}

	// Método que pone todas las patas a false.
	public void iniciarPatas() {
		for (int i = 0; i < patasTurno.length; i++) {
			patasTurno[i] = false;
		}
	}

	// Método que comprueba si el turno está completo:
	public boolean turnoCompleto() {
		for (int i = 0; i < patasTurno.length; i++) {
			if (!patasTurno[i]) {
				return false; // Matando moscas a cañonazos!
			}
		}
		return true;
	}

	public synchronized void mover(int id) {
		// Si ya me he movido en este turno, me bloqueo
		while (patasTurno[id]) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Si me toca, pinto que me muevo
		System.out.println("Se mueve la pata " + id);
		patasTurno[id] = true;
		if (turnoCompleto()) { // En el caso en el que todas las patas se hayan movido, empiezo un nuevo turno.
			// Pinto fin de turno y espero
			System.out.println("FIN DE TURNO");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Después de dormir, arranco los demás
			iniciarPatas();
			notifyAll();
		}

	}

}
