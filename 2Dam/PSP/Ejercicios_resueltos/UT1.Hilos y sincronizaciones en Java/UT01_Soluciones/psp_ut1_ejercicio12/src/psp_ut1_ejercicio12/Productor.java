package psp_ut1_ejercicio12;

public class Productor extends Thread{
	private String nom;
	private Cola cola;
	
	public Productor(String nom, Cola cola) {
		this.nom = nom;
		this.cola = cola;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			cola.put(i,this.nom);
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Cola getCola() {
		return cola;
	}

	public void setCola(Cola cola) {
		this.cola = cola;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
