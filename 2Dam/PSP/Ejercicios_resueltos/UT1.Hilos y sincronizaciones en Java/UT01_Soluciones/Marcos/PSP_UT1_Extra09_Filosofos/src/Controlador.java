
public class Controlador {
	int turno;
	boolean palillos[];

	public Controlador() {
		turno = 0;
		palillos = new boolean[5];
		for (int i = 0; i < palillos.length; i++) {
			palillos[i] = false;
		}
	}

	public void comer(String nombre, int id) {

		synchronized (this) {
			while (palillos[id] || palillos[(id + 1) % palillos.length]) { // Cuando los palillos de los lados están cogidos
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for (int i = 0; i < id; i++) {
				System.out.print("\t");
			}
			
			System.out.println(nombre + " coge los palillos.");
			palillos[id] = true;
			palillos[(id + 1) % palillos.length] = true;
		}

		for (int i = 0; i < id; i++) {
			System.out.print("\t");
		}
		System.out.println(nombre + " está comiendo");

		synchronized (this) {
			for (int i = 0; i < id; i++) {
				System.out.print("\t");
			}
			System.out.println(nombre + " suelta los palilos.");
			palillos[id] = false;
			palillos[(id + 1) % palillos.length] = false;
			notifyAll();
		}

	}

}
