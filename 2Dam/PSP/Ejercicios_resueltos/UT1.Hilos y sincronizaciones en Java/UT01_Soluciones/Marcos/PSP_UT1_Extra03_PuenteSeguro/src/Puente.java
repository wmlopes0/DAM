
public class Puente {

	private int pesoEnPuente = 0;
	private int numVehiculos = 0;
	private int numAmbulanciasEsperando = 0;
	private static final int pesoMaximo = 15000;
	private static final int nVehiculosMaximo = 10;

	public synchronized void entrarPuente(int numVehiculo, int peso, boolean esAmbulancia) {
		if (esAmbulancia) {
			numAmbulanciasEsperando++;
			System.out.println("Ambulancia "+numVehiculo+" intentando entrar en el puente, peso: " + peso + " peso actual: "
					+ pesoEnPuente + " número de vehículos: " + numVehiculos);
		} else {

			System.out.println("Vehiculo "+numVehiculo+" intentando entrar en el puente, peso: " + peso + " peso actual: " + pesoEnPuente
					+ " número de vehículos: " + numVehiculos);
		}

		while ((peso + pesoEnPuente > pesoMaximo) || (numVehiculos + 1 > nVehiculosMaximo)
				|| ((numAmbulanciasEsperando > 0) && !esAmbulancia))
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (esAmbulancia) {
			numAmbulanciasEsperando--;
			System.out.println("===Ambulancia "+numVehiculo+" cruzando el puente");
		}
		else {
			System.out.println("===Vehiculo "+numVehiculo+" cruzando el puente");
		}

		pesoEnPuente = pesoEnPuente + peso;
		numVehiculos++;
	}

	public synchronized void salirPuente(int numVehiculo,int peso, boolean esAmbulancia) {
		pesoEnPuente = pesoEnPuente - peso;
		if (esAmbulancia) {
			numAmbulanciasEsperando--;
			System.out.println("		Ambulancia "+numVehiculo+" saliendo del puente, peso actual del puente: "+ pesoEnPuente);
		} else {
			numVehiculos--;
			System.out.println("		Vehiculo "+numVehiculo+" saliendo del puente, peso actual del puente: "+ pesoEnPuente);
		}
		notifyAll();
	}

}
