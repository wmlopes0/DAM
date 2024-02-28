package es.iesvjp.psp;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Banio {
	private Semaphore esperar;
	private Semaphore banioLleno;
	private Semaphore mutex;

	private boolean quiereLimpiar;
	private int personaDentro;
	private int cola;

	public Banio() {
		this.mutex = new Semaphore(1);
		this.esperar = new Semaphore(0);
		this.banioLleno = new Semaphore(0);
		this.quiereLimpiar = false;
		this.personaDentro = 0;
		this.cola = 0;
	}

	public void usar_banio(String nombre) {
		// entramos al baños
		System.out.println(nombre + " quiere entrar al baño");
		try {
			mutex.acquire();
			
			if (quiereLimpiar) {
				System.out.println("\tXX " + nombre + " espera a que se limpie el baño");
				cola++;
				mutex.release();
				esperar.acquire();
				mutex.acquire();
			}
			personaDentro++;
			mutex.release();
			System.out.println("\t" + nombre + " está utilizando el baño");
			Thread.sleep(2000);
			// cuando salimos comprobamos si es el ultimo
			mutex.acquire();
			personaDentro--;
			if ((personaDentro == 0) && quiereLimpiar) {
				System.out.println("\t\t =>" + nombre + " es el ultimo en abandonar el baño, avisa al limpiador");
				banioLleno.release();

			} else {
				System.out.println("\t\t" + nombre + " salió del baño");
			}
			mutex.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void limpiar() throws InterruptedException {
		mutex.acquire();
		System.out.println("-- Limpiador pone el cartel para limpiar el baño --");
		quiereLimpiar = true;
		//comprobamos si hay personas dentro 
		if (personaDentro > 0) {
			mutex.release();
			banioLleno.acquire();//aqui recogemos la llamada del último que sale del baño

		} else {
			mutex.release();
		}
		//Limpia el baño
		System.out.println("------ XX limpiando baño XX -----");
		Thread.sleep(2000);
		System.out.println("\tBaño limpio");
		//quita el cartel
		mutex.acquire();
		//avisamos a los que estan en la cola 
		quiereLimpiar = false;
	
		while (this.cola > 0) {
			this.cola--;
			esperar.release();
		}
		mutex.release();
	}
}
