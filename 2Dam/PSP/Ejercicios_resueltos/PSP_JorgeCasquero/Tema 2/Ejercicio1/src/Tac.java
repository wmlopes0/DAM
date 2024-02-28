import java.util.concurrent.Semaphore;

public class Tac implements Runnable {
	Semaphore semaforoTic;
	Semaphore semaforoTac;

	public Tac(Semaphore semaforo1, Semaphore semaforo2) {
		this.semaforoTic = semaforo1;
		this.semaforoTac = semaforo2;
	}

	@Override
	public void run() {
		while (true) {
			try {
				semaforoTic.acquire();
			} catch (Exception e) {
				e.printStackTrace();
			}

			semaforoTic.release();

			System.out.println("TAC");
		}

	}

}
