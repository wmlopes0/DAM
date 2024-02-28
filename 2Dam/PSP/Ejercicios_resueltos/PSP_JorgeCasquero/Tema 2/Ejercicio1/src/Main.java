import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		Semaphore semaforo1 = new Semaphore(1);
		Semaphore semaforo2 = new Semaphore(0);
		Tic tic = new Tic(semaforo1, semaforo2);
		Tac tac = new Tac(semaforo1, semaforo2);

		new Thread(tic).start();
		new Thread(tac).start();

	}

}