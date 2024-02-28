
public class Main {
	
	public static void main(String[] args) {
		Puente puente= new Puente();
		Vehiculo[] vehiculos= new Vehiculo[10];
		Vehiculo[] ambulancias= new Vehiculo[5];
		
		
		
		for (int i = 0; i < vehiculos.length; i++) {
			int peso=(int) (Math.random() * 6000 +1000);
			vehiculos[i]= new Vehiculo(peso, i, false, puente);
			vehiculos[i].start();
		}
		
		for (int i = 0; i < ambulancias.length; i++) {
			int peso=(int) (Math.random() * 8000 +4000);

			ambulancias[i]= new Vehiculo(peso, i, true, puente);
		    ambulancias[i].start();
		}
		
	}

}
