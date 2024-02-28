
public class Principal {
	
	public static void main(String[] args) {
		Puente control = new Puente();
			
		String nombresPeatones [] = {"María", "Pedro", "Juan", "Marcos", "Ana"};
		Peaton peatones [] = new Peaton[5];
		
		for (int i = 0; i < peatones.length; i++) {
			peatones[i] = new Peaton(control, nombresPeatones[i]);
			peatones[i].start();
		}
		
		Barco barco = new Barco(control, "Titanic");
		barco.start();
		
	}
}
