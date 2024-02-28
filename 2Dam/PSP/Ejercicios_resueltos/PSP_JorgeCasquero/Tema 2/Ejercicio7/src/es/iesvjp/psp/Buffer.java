package es.iesvjp.psp;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
	static Queue<String> cola = new LinkedList<String>();

	public static void mostrarCola() {
		System.out.println("-------------------");
		// pasamos la cola a un array para poder mostrarlo al revés
		String[] arr = cola.toArray(new String[cola.size()]);
		// recorremos el array al reves
		for (int i = arr.length - 1; i >= 0; i--) {
			System.out.print("| " + arr[i] + " ");
		}
		System.out.println("\n------------------------------\n");
	}

}
