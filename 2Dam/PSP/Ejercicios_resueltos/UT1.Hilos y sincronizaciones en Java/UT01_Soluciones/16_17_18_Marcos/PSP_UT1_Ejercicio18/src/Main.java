
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Banyo banyo = new Banyo();
		Fumador[] fumadores = new Fumador[3];
		Estanquero estanquero = new Estanquero(banyo);
		
		
		for (int i = 0; i < fumadores.length; i++) {
			int ingrediente=i+1;
			fumadores[i]= new Fumador(ingrediente,banyo);
			fumadores[i].start();
			
		}
		
		estanquero.start();
		
		
	}
}
