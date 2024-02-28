
public class Encargado extends Thread {
	Jaula jaula;

	public Encargado(Jaula jaula) {
		this.jaula = jaula;
	}

	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
			
			jaula.reponerAlpiste();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
