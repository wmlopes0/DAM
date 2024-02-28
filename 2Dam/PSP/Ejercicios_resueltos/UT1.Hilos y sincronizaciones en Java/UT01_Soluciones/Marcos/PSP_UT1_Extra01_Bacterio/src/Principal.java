	
public class Principal {

	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		
		TuberiaHidrogeno hiloHidrogeno = new TuberiaHidrogeno(controlador);
		TuberiaOxigeno hiloOxigeno = new TuberiaOxigeno(controlador);
		
		hiloHidrogeno.start();
		hiloOxigeno.start();

	}

}
