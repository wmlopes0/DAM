package psp_ut1_ejercicio15;

public class HiloPrincipal {
	int contador;
	boolean sePintoA;
	
	public HiloPrincipal() {
		contador = 0;
		sePintoA = false;
	}
	
	public synchronized int pintarA() {
		if (contador < 20) {
			System.out.println("A");
			contador++;
			sePintoA = true;
		}
		return contador;
	}
	
	public synchronized int pintarB() {
		while(!sePintoA) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (contador < 20) {
			System.out.println("B");
			contador++;
			sePintoA = false;
		}
		return contador;
	}
	
}
