
public class Controlador {
	int hidrogenos = 0;
	int oxigenos = 0;
	
	
	public synchronized void pasaOxigeno(){
		while(oxigenos >= 1){
			try {
				System.out.println("No se pueden meter más oxígenos.");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		oxigenos++;
		System.out.println("Se ha metido un oxígeno");
		
		generarAgua();
	}
	
	public synchronized void pasaHidrogeno(){
		while(hidrogenos >= 2){
			try {
				System.out.println("No se pueden meter más hidrogenos.");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		hidrogenos++;
		System.out.println("Se ha metido un hidrógeno");
		generarAgua();
	}
	
	public void generarAgua(){
		if((oxigenos==1) && (hidrogenos ==2)){
			System.out.println("Se hizo agua\n\n");
			try {	Thread.sleep(300);	} catch (InterruptedException e) {e.printStackTrace();}
			oxigenos = 0;
			hidrogenos = 0;
			notify();
		}
	}
	
}
