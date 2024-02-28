
public class Lector extends Thread{
	String nombreLector;
	Recurso recurso;
	
	
	public Lector(String nombreLector, Recurso recurso) {
		this.nombreLector = nombreLector;
		this.recurso = recurso;
	}
	
	@Override
	public void run() {
		for (int iteracion = 1; iteracion <= 5; iteracion++) {
			recurso.leer(nombreLector, iteracion);
			yield();
		}
	}
}

