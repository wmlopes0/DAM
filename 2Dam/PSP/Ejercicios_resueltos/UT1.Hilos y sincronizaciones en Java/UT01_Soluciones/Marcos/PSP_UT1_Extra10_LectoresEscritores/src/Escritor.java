
public class Escritor extends Thread{
	String nombreEscritor;
	Recurso recurso;
	
	
	public Escritor(String nombreEscritor, Recurso recurso) {
		this.nombreEscritor = nombreEscritor;
		this.recurso = recurso;
	}
	
	@Override
	public void run() {
		for (int iteracion = 1; iteracion <= 5; iteracion++) {
			recurso.escribir(nombreEscritor, iteracion);
		}
	}
}
