
public class Recurso {
	int numLectores;
	boolean hayEscritores;
	String recurso; //El recurso que tocan los escritores y consumidores.
	
	public Recurso() {
		recurso = "VACIO";
		numLectores = 0;
		hayEscritores = false;
	}
	
	/**
	 * Método para que lea un lector.
	 * @param nombreLector
	 * @param numIteracion
	 */
	public void leer(String nombreLector, int numIteracion){
		synchronized (this) {
			while(hayEscritores){ //Sólo me bloqueo si hay escritores.
				try {	wait();	} catch (InterruptedException e) {e.printStackTrace();}
			}
			numLectores++;
		}
		//Parte en paralelo por todos los lectores:
		System.out.println("\t"+nombreLector+"--"+numIteracion +" --> "+recurso);
		
		synchronized (this) {
			numLectores--;
			if (numLectores == 0){
				notifyAll(); //<-- DESPERTAR A TODOS LOS ESCRITORES. Imagina el caso en el que hubiese sólo 1 lector.
			}
		}
	}
	/**
	 * Método para que ejecute un escritor
	 * @param nombreEscritor
	 * @param numIteracion
	 */
	public void escribir(String nombreEscritor, int numIteracion){
		synchronized (this) { 
			while(hayEscritores || numLectores > 0){
				try {	wait();	} catch (InterruptedException e) {e.printStackTrace();}
			}
			hayEscritores = true;
		}
		//Parte no sincronizada, pero obviamente sólo va a pasar un escritor.
		recurso = nombreEscritor+"--"+numIteracion;
		System.out.println(recurso);
		
		synchronized (this) {
			hayEscritores = false;
			notifyAll();
		}
	}
	


}
