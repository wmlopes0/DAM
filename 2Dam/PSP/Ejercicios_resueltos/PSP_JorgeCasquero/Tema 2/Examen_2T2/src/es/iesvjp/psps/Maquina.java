package es.iesvjp.psps;

import java.util.concurrent.Semaphore;

public class Maquina {

	private final static int CAPACIDAD = 40;
	private int juguetes;
	private Semaphore mutex;
	private Semaphore productorEspera;
	private Semaphore consumidorEspera;
	private int colaEntrada;
	private int colaSalida;

	public Maquina() {
		this.juguetes = 0;
		this.mutex = new Semaphore(1);
		this.productorEspera = new Semaphore(0);
		this.consumidorEspera = new Semaphore(0);
		this.colaEntrada = 0;
		this.colaSalida = 0;
	}

	public void introducir_juguetes(int cantidad, String duende) throws InterruptedException {
		mutex.acquire();
		System.out.println(
				"=>" + duende + " quiere INTRODUCIR " + cantidad + " juguetes (actuales:" + this.juguetes + ").");

		while (this.juguetes + cantidad > CAPACIDAD) {
			this.colaEntrada++;
			mutex.release();
			productorEspera.acquire();
			mutex.acquire();

		}
		this.juguetes += cantidad;
		System.out.println(
				"\t" + duende + " introduciendo " + cantidad + " juguete/s: juguetes actuales = " + this.juguetes);
		consumidorEspera.release(this.colaSalida);
		mutex.release();
	}

	public void recoger_juguetes(int cantidad, String duende) throws InterruptedException {
		mutex.acquire();
		System.out
				.println("=>" + duende + " quiere RECOGER " + cantidad + " juguetes (actuales: " + this.juguetes + ").");
		while (this.juguetes < cantidad) {
			this.colaSalida++;
			mutex.release();
			consumidorEspera.acquire(); /* Nos bloqueamos */
			mutex.acquire();
		}
		this.juguetes -= cantidad;
		System.out.println("\t" + duende + " recogiendo " + cantidad + " quedan " + this.juguetes);
		productorEspera.release(this.colaEntrada); 
		mutex.release();
	}

}
