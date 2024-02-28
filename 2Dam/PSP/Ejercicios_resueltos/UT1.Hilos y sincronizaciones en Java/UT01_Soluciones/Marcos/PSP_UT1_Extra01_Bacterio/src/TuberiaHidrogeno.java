
public class TuberiaHidrogeno extends Thread{
	
	private Controlador controlador;
	
	public TuberiaHidrogeno(Controlador controlador) {
		this.controlador = controlador;
	}
	
	@Override
	public void run() {
		while(true){
			controlador.pasaHidrogeno();
			yield();
		}
	}
	
}
