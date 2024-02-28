package psp_ut1_ejercicio19;

public class Controlador {
	private int totalHidrogeno;
	private int totalOxigeno;
	
	public Controlador() {
		this.totalHidrogeno = 0;
		this.totalOxigeno = 0;
	}

	public int getTotalHidrogeno() {
		return totalHidrogeno;
	}

	public void setTotalHidrogeno(int totalHidrogeno) {
		this.totalHidrogeno = totalHidrogeno;
	}

	public int getTotalOxigeno() {
		return totalOxigeno;
	}

	public void setTotalOxigeno(int totalOxigeno) {
		this.totalOxigeno = totalOxigeno;
	}
	
	public synchronized void generarAgua() throws InterruptedException {
		if (totalHidrogeno == 2 && totalOxigeno == 1) {
			System.out.println("-- Se ha creado agua --\n");
			totalHidrogeno = 0;
			totalOxigeno = 0;
			Thread.sleep(300);
		}
	}

	public synchronized void introducirOxigeno() throws InterruptedException {
		while (totalOxigeno == 1) {
			System.out.println("No se puede introducir mas Oxígeno");
			wait();
		}
		totalOxigeno++;
		System.out.println("Se ha introducido Oxígeno");
		
		generarAgua();
		notifyAll();
	}
	
	public synchronized void introducirHidrogeno() throws InterruptedException {
		while (totalHidrogeno == 2) {
			System.out.println("No se puede introducir mas Hidrógeno");
			wait();
		}
		totalHidrogeno++;
		System.out.println("Se ha introducido Hidrógeno");
		
		generarAgua();
		notifyAll();
	}
	
	public synchronized void insertarEnMaquina(String nom) throws InterruptedException {
		Thread.sleep(500);
		if (nom.equalsIgnoreCase("Hidrogeno")) {
			introducirHidrogeno();
		}
		
		if (nom.equalsIgnoreCase("Oxigeno")) {
			introducirOxigeno();
		}
	}
}
