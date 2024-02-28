package psp_ut1_ejercicio12;

public class Cola {
	private int num;
	private boolean llena;
	
	public Cola() {
		this.num = 0;
		this.llena = false;
	}
	
	public synchronized void put(int valor, String nom) {
		while (llena) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		setNum(valor);
		llena = true;
		System.out.println(" -> " + nom + ", produce: " + valor);
		notifyAll();
	}
	
	public synchronized int get(String nom) {
		while (!llena) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		llena = false;
		System.out.println(" -> " + nom + ", consume: " + getNum());
		notifyAll();
		return getNum();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isLlena() {
		return llena;
	}

	public void setLlena(boolean llena) {
		this.llena = llena;
	}
}
