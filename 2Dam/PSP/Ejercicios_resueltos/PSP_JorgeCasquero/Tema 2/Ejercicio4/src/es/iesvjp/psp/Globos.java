package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Globos {
	private Semaphore mutex;
	private int globos;

	public Globos() {
		this.mutex = new Semaphore(1);
		this.globos = 1000;
	}

	public void explotar() {
		this.globos--;
	}

	public Semaphore getMutex() {
		return mutex;
	}

	public void setMutex(Semaphore mutex) {
		this.mutex = mutex;

	}

	public int getGlobos() {
		return this.globos;

	}

	public void setGlobos(int globos) {
		this.globos = globos;
	}

}
