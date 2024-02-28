package psp_ut1_ejercicio8;

public class Calculo extends Thread {
	private int numA;
	private int numB;
	
	public Calculo(int numA, int numB) {
		super();
		this.numA = numA;
		this.numB = numB;
	}

	public int getNumA() {
		return numA;
	}


	public void setNumA(int numA) {
		this.numA = numA;
	}


	public int getNumB() {
		return numB;
	}


	public void setNumB(int numB) {
		this.numB = numB;
	}


	public static void calculoHilo(int numA, int numB) {
		
	}
	
	@Override
	public void run() {
		
	}
}
