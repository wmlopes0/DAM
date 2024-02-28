package es.iesvjp.psp;

import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class Parking extends Thread {
	// inicializamos las plazas como -1 lo que es igual a que estan vacias
	private int[] nPlaza = { -1, -1, -1, -1 };
	private Semaphore mutex = new Semaphore(1);
	private int nAmbulancias = 0;
	private int pLibres = 4;

	

	public void aparcar(int num) {
		try {
			System.out.println("\n|| La ambulancia numero " + num + " llega al parking e intenta entrar");

			System.out.println("\n\n=> La ambulancia número " + num + " entra al parking");
			mutex.acquire();
			
			for (int i = 0; i < nPlaza.length; i++) {

				if (nPlaza[i] == -1 && num<=6) {
					
					pLibres--;
					System.out.println("\n----------------- Ambulancia número " + num++ + " ha aparcado en la plaza " + i
							+ " quedan libres " + pLibres);
					
				}
				
			}
			
			nAmbulancias++;
			pLibres--;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void salir(int num) {
		for (int i = 0; i < nPlaza.length; i++) {
			pLibres ++;
			System.out.println("\n Ambulaacia numero " + num++ + " abandona el parking quedan libres " + pLibres);	
		}
		mutex.release();
		
	}

}
