package es.iesvjp.psp;

import java.util.concurrent.Semaphore;

public class Caja {
	private int litros_total;
	private Semaphore mutex;
	private Semaphore empaquetador;
	private Semaphore despierta2L;
	private boolean espera2L;

	public Caja() {
		this.litros_total = 5;
		this.mutex = new Semaphore(1);
		this.empaquetador = new Semaphore(0);
		despierta2L = new Semaphore(0);
		espera2L = false;

	}

	public void llenarBotellaLitro(int botella) {
		System.out.println("Maquina de 1 litro está llenando botella-" + botella + " de 1 litro");
		try {
			Thread.sleep(2500);
			mutex.acquire();
			if (litros_total > 0) {
				litros_total -= 1;
				System.out.println("\t Máquina de 1 litro" + "está soltando botella" + botella + " de 1 litro. CAJA:[ "
						+ litros_total + " ]");
				if (litros_total == 0) {
					System.out.println("\t==Enviamos señal al empaquetador. CAJA:[ " + litros_total + "]");
					mutex.release();
					empaquetador.release();
					mutex.acquire();

				}
			}
			mutex.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void llenarBotella2Litros(int botella) {
		System.out.println("Máquina 2 litros está llenando botella-" + botella + "de 2 litros");
		try {
			Thread.sleep(5000);
			mutex.acquire();
			if (litros_total == 1) {
				espera2L = true;
				System.out.println(
						"\t--Máquina 2Litros: NO HAY SUFICIENTE ESPACIO CAJA:[" + litros_total + "],espera...");
				mutex.release();
				despierta2L.release();
				mutex.acquire();
			}
			litros_total -= 2;
			System.out.println("\tMáquina está soltando botella" + botella
					+ " de 2 litros. Capacidad actual de lacaja :[ " + litros_total + " ]");

			if (litros_total == 0) {
				System.out.println("\t\tEnviamos señal al empaquetador.Caja:[" + litros_total + " ]");
				mutex.release();
				empaquetador.release();
				mutex.acquire();

			}
			mutex.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void empaquetar() {
		try {
			empaquetador.acquire();
			mutex.acquire();
			System.out.println("\t\t ++EMPAQUETADOR: empaquetando caja de 10 litros. Soltando una vacía");
			litros_total = 5;
			
			if (espera2L) {
				espera2L = false;
				mutex.release();
				despierta2L.release();
				mutex.acquire();
			}
			mutex.release();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
