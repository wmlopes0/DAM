package psp_ut1_ejercicio16;

import java.util.Random;

public class HiloPrincipal {
	private boolean hueco;
	private int gente;
	
	public HiloPrincipal() {
		this.hueco = true;
		this.gente = 0;
	}

	public boolean isHueco() {
		return hueco;
	}

	public void setHueco(boolean hueco) {
		this.hueco = hueco;
	}

	public int getGente() {
		return gente;
	}

	public void setGente(int gente) {
		this.gente = gente;
	}
	
	public void put(String nombre) throws InterruptedException {
		entraPersona(nombre);
		get(nombre);
	}
	
	public synchronized void entraPersona(String nombre) throws InterruptedException {
		while (!hueco) {
			System.out.println("¡" + nombre + " espera porque el baño está lleno!");
			wait();
		}
		gente++;
		System.out.println("++" + nombre + " entra ---------- Hay " + getGente());
		if (this.gente == 3) {
			setHueco(false);
		}
		
		notifyAll();
	}
	
	public void get(String nombre) throws InterruptedException {
		Random r = new Random();
		int tiempo = r.nextInt(5)+2;
		
		Thread.sleep(tiempo*1000);
		System.out.println(nombre + " permanece en el baño " + tiempo + " segundos.");
		
		salePersona(nombre);
	}
	
	public synchronized void salePersona(String nombre) {
		this.gente--;
		System.out.println("--" + nombre + " sale ---------- Hay " + this.gente);
		
		setHueco(true);
		notifyAll();
		
	}
	
	public void espera() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
