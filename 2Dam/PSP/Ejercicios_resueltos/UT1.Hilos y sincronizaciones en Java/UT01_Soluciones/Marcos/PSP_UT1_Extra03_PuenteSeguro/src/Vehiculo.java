
public class Vehiculo extends Thread {
	int peso;
	int numVehiculo;
	boolean esAmbulancia;
	Puente puente;
	
	public Vehiculo(int peso, int numCoche, boolean esAmbulancia, Puente puente) {
		this.peso = peso;
		this.numVehiculo = numCoche;
		this.esAmbulancia=esAmbulancia;
		this.puente=puente;
	}

	@Override
	public void run() {
		puente.entrarPuente(numVehiculo,peso, esAmbulancia);
		try {
			//tarda en cruzar el puente  5 segundos
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		puente.salirPuente(numVehiculo,peso, esAmbulancia);
		
	}
	
	

}
