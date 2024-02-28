import java.util.concurrent.Semaphore;

public class Tic implements Runnable {

	Semaphore semaforoTic;
	Semaphore semaforoTac;

	public Tic(Semaphore semaforo1, Semaphore semaforo2) {
		this.semaforoTic = semaforo1;
		this.semaforoTac = semaforo2;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("TIC");
			semaforoTic.release();
			semaforoTac.release();

		}
	}
}
