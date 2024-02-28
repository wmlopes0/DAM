
public class TuberiaOxigeno extends Thread {
	
	private Controlador controlador;
	
	public TuberiaOxigeno(Controlador controlador) {
		this.controlador = controlador;
	}
	
	@Override
	public void run() {
		while(true){
			controlador.pasaOxigeno();
			yield();
		}
	}
	
}
