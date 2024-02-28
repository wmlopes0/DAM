package psp_ut1_ejercicio10;

public class HiloPrincipal {
	
	private int nMultiplos;
	
	public HiloPrincipal(int nMultiplos) {
		super();
		this.nMultiplos = nMultiplos;
	}

	public int getnMultiplos() {
		return nMultiplos;
	}

	public void setnMultiplos(int nMultiplos) {
		this.nMultiplos = nMultiplos;
	}

	synchronized public void multiplos(int num) {
		try {
			System.out.println("\nSoy el hilo " + num + " los primeros múltiplos son: ");
			for (int i = 1; i <= nMultiplos; i++) { // 1 a 5
				System.out.println("Múltiplo[" + i + "]: " + (i*num));
				Thread.sleep(500);
			}
			System.out.println("Fin de los múltiplos de " + num);
			
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
