
public class Jaula {

	private final static int TOTAL_PAJAROS = 3;
	private int numcanarioscomiendo;
	private boolean reponiendo;

	public Jaula() {
		this.numcanarioscomiendo = 0;
		this.reponiendo = false;
	}

	public void comerAlpiste(int canario) {
		synchronized (this) {
			while (reponiendo || numcanarioscomiendo == TOTAL_PAJAROS) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			numcanarioscomiendo++;
		}
		System.out.println("-----Canario-" + canario + " está comiendo alpiste <O) ");
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (this) {
			System.out.println("\t Canario-" + canario + " abandona el comedero WW_O_WW");
			numcanarioscomiendo--;
			notifyAll();
		}
	}

	public synchronized void reponerAlpiste() {
		// mientras el comedero esté lleno el encargado espera
		reponiendo = true;
		System.out.println("El encargado quiere reponer alpiste");
		while (numcanarioscomiendo > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// llenamos el comedero
		System.out.println("+++++Reponiendo alpiste");
		reponiendo = false;
		notifyAll();

	}

}
