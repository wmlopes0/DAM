package psp_ut1_ejercicio09;

public class Puente {
	private int tramo;
	private int incioPuente;
	private int finPuente;
	
	public Puente() {
		this.tramo = 12;
		this.incioPuente = 6;
		this.finPuente = 9;
	}
	
	public Puente(int tramo, int incioPuente, int finPuente) {
		super();
		this.tramo = tramo;
		this.incioPuente = incioPuente;
		this.finPuente = finPuente;
	}
	
	synchronized public void tramoPeligroso(Coche coche) {
		for (int j = this.incioPuente; j <= this.finPuente; j++) {
			System.out.println("	***El " + coche.getNombre() + " va por el km " + (j) + " del puente.");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getTramo() {
		return tramo;
	}

	public void setTramo(int tramo) {
		this.tramo = tramo;
	}

	public int getIncioPuente() {
		return incioPuente;
	}

	public void setIncioPuente(int incioPuente) {
		this.incioPuente = incioPuente;
	}

	public int getFinPuente() {
		return finPuente;
	}

	public void setFinPuente(int finPuente) {
		this.finPuente = finPuente;
	}
	
	
}
