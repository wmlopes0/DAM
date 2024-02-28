
public class Cliente extends Thread {

	private int numCliente;
	private Barberia barberia;

	public Cliente(int numCliente, Barberia barberia) {

		this.numCliente = numCliente;
		this.barberia = barberia;
	}

	public void run() {

		while (true) {

			try {
				// Cortar pelo
				if (barberia.entrarBarberia(numCliente)) {
					// Espero hasta que me crezca el pelo
					Thread.sleep(25000);
				} else {
					// Espero y lo vuelvo a intentar
					Thread.sleep(4000);
				}
			} catch (InterruptedException e) {
			}
			;
		}
	}
}
