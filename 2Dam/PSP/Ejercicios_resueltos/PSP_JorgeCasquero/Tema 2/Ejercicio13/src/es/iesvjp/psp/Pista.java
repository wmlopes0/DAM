package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Pista {
	private int jugadores;
	private Semaphore mutex;
	private Semaphore ocupada;
	private Semaphore espera;
	boolean jugando;
	private int cola;

	public Pista() {
		this.jugadores = 0;
		this.mutex = new Semaphore(1);
		this.ocupada = new Semaphore(0);
		this.espera = new Semaphore(0);
		this.cola = 0;
	}

	public void jugar(String nombre) throws InterruptedException {
		mutex.acquire();
		while (jugando) {
			cola++;
			mutex.release();
			ocupada.acquire();
			mutex.acquire();
		}
		jugadores++;
		System.out.println("=>" + nombre + " entra en la pista.");
		
		if (jugadores == 4) {
			System.out.println("\t " + nombre + ": ¡¡Ya somos [" + jugadores + "], podemos empezar el partido !!");
			//cedemos los permisos para que puedan entrar despues del partido 
			espera.release(3);
			
		} else {
			System.out.println("\t" + nombre + " esperando a que lleguen más jugadores [" + jugadores + "]");
			mutex.release();
			espera.acquire();
			mutex.acquire();
		}
		
		jugando = true;
		System.out.println("\t\t" + nombre + " jugando el partido");
		mutex.release();
		Thread.sleep(100);
		mutex.acquire();
		jugadores--;
		
		if (jugadores == 0) {
			System.out.println("\t\t\t" + nombre + " es el último en salir. Avisa a resto.");
			jugando = false;
			ocupada.release(cola);
			cola = 0;
		} else {
			System.out.println("\t\t\t" + nombre + " abandona la cancha.");
		}
		mutex.release();

	}
}
