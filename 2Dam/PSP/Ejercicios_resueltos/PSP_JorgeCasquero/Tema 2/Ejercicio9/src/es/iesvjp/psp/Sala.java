package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Sala {
	private int hombres;
	private int mujeres;
	private Semaphore hombresEsperando;
	private Semaphore mujeresEsperando;
	private Semaphore mutex;

	public Sala() {
		this.hombres = 0;
		this.mujeres = mujeres;
		this.hombresEsperando = new Semaphore(0);
		this.mujeresEsperando = new Semaphore(0);
		this.mutex = new Semaphore(1);
	}

	public void entrarAbailar(Persona persona) throws InterruptedException {
		this.mutex.acquire();
		System.out.println(persona.getNombre() + " quiere entrar a la sala de baile[H:" + this.hombres + "][M:"
				+ this.mujeres + "]");
		if (persona.getSexo().equals("hombre")) {
			if (hombres > mujeres) {
				System.out.println("\t" + persona.getNombre() + " esperando en la puerta");
				hombresEsperando.acquire();
				mutex.release();
				mujeresEsperando.acquire();
				mutex.acquire();

			}
			this.hombres++;

		} else {
			if (mujeres > hombres) {
				System.out.println("\t" + persona.getNombre() + " esperando en la puerta");

				mutex.release();
				mujeresEsperando.acquire();
				mutex.acquire();
			}
			
			this.mujeres++;
			
		}
		mutex.release();
		System.out.println("\t"+persona.getNombre() +" está bailando...[H: " + this.hombres + "][M: "+ this.mujeres + "]");
		Thread.sleep(200);
		mutex.acquire();
		if(persona.getSexo().equals("hombre")) {
			this.hombres--;
			System.out.println("\t"+persona.getNombre() +" terminó de bailar[H: " + this.hombres + "][M: "+ this.mujeres + "]");
			this.mujeresEsperando.release();
		}else {
			this.mujeres--;
			System.out.println("\t"+persona.getNombre() +" terminó de bailar[H: " + this.hombres + "][M: "+ this.mujeres + "]");
			this.hombresEsperando.release(); 
		}
		mutex.release();
	}

}
