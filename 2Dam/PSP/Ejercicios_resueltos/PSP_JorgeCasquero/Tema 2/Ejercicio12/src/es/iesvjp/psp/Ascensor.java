package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Ascensor {
	private final static float MAX_PESO = 450;
	private final static float MAX_PERSONAS = 450;

	private Semaphore espera;
	private Semaphore mutex;
	private int carga;
	private int ocupacion;
	private int cola;

	/**
	 * 
	 */
	/**
	 * 
	 */
	public Ascensor() {
		this.espera = new Semaphore(0);
		this.mutex = new Semaphore(1, false);
		this.carga = 0;
		this.ocupacion = 0;
		this.cola = 0;
	}

	/**
	 * metodo que sera llamado desde la clase Personas para indicar que suben del
	 * ascensor
	 * @param nombre
	 * @param peso
	 */
	public void sube_persona(String nombre, float peso) {
		try {
			// adquirimos un permiso del mutex, zona critica
			mutex.acquire();
			System.out.println(nombre + " llama al ascensor, su peso es de " + peso);
			// condiciones que indican que no se puede subir al ascensor
			while (ocupacion == 6 || (carga + peso > MAX_PESO)) {
				// las personas a la cola aumentan
				cola++;
				System.out.println(nombre + " tiene que esperar, las condiciones actuales son [" + ocupacion + "][" + carga + " kg]");
				// devolvemos el permiso al mutex
				mutex.release();
				// quitamos un permiso para bloquear la subida al ascensor mientras se den las
				// condiciones
				espera.acquire();
				mutex.acquire();

			}
			ocupacion++;
			carga += peso;
			System.out.println("\t" + nombre + " sube al ascensor. [" + ocupacion + "][" + carga + " kg]");
			// se devuelve el permiso al mutex para seguir con el programa
			mutex.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * metodo que sera llamado desde la clase Personas para indicar que bajan del
	 * ascensor
	 * @param nombre
	 * @param peso
	 */
	public void baja_persona(String nombre, float peso) {
		try {
			// zona critica
			mutex.acquire();
			// baja persona, baja la cola
			ocupacion--;
			carga -= peso;
			System.out.println("\t\t=> " + nombre + "baja ascensor.[" + ocupacion + "][" + carga + "]");
			// damos tantos permisos como personas haya esperando
			espera.release(cola);
			cola = 0;
			mutex.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
