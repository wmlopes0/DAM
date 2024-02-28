package psp_ut1_ej2;

public class ThreadConHerencia extends Thread{
	int numHilo;
		
	public ThreadConHerencia(int numHilo) {
		this.numHilo=numHilo;
	}
		
	public void setNumHilo(int numHilo) {
		this.numHilo=numHilo;
	}
	
	public int getNumHilo() {
		return numHilo;
	}
	
	public void run() {
		for (int i = 0; i < 4; i++) {
			System.out.println("Hilo " + this.getNumHilo());
		}
	}
}
