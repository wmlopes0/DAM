package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class SalaProfesores {

	int ocupacion;

	private Semaphore semaforo = new Semaphore(1, true);

	public SalaProfesores() {
		semaforo = new Semaphore(3);
		this.ocupacion = 0;
	}

	public int getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(int ocupacion) {
		this.ocupacion = ocupacion;
	}

	public void aumentarOcupacion() {
		this.ocupacion++;

	}

	public void desocupar() {
		this.ocupacion--;

	}

	public void entrarSala(Profesor profesor) {
		try {
			semaforo.acquire();
			if (this.ocupacion < 3) {
				this.ocupacion++;
			}
			System.out.println("\n +++" + profesor.getNombre()
					+ " está dentro de la  sala de profesores. En estos momentos hay [" + getOcupacion() + "]");
		
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void salirSala(Profesor profesor) {

		this.ocupacion--;
		semaforo.release();
		System.out.println("\n ---" + profesor.getNombre() + " abandona la sala de profesores. En estos momentos hay ["
				+ getOcupacion() + "]");
	}
}