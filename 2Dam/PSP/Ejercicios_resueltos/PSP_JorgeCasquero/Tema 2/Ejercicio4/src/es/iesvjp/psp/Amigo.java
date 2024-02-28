package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

/**
 * @author jcasqueros01
 *
 */
public class Amigo extends Thread {
	private String nombre;
	private Globos globos;
	private int explotados;

	public Amigo(String nombre, Globos globos) {
		this.nombre = nombre;
		this.globos = globos;
		this.explotados = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Globos getGlobos() {
		return globos;
	}

	public void setGlobos(Globos globos) {
		this.globos = globos;
	}

	@Override
		public void run() {

		while(this.globos.getGlobos() > 0) {
			try {
				this.globos.getMutex().acquire();
				Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(this.globos.getGlobos() > 0) {
				this.globos.explotar();
				this.explotados++;
				
			}
			this.globos.getMutex().release();
			
		}
		System.out.println("Globos explotados por "+this.nombre+":"+this.explotados);
		
	}
	public int getsExplotados() {
		return explotados;
	}


}
