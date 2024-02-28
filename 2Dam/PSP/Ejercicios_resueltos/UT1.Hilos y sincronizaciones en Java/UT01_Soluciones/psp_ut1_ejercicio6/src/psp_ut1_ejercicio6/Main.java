package psp_ut1_ejercicio6;

public class Main {

	public static void main(String[] args) {
		CorredorAnimal[] vAnimales = new CorredorAnimal[3];
		CorredorAnimal tortuga = new CorredorAnimal("Tortuga");
		vAnimales[0] = tortuga;
		CorredorAnimal liebre = new CorredorAnimal("Liebre");
		vAnimales[1] = liebre;
		CorredorAnimal guepardo = new CorredorAnimal("guepardo");
		vAnimales[2] = guepardo;
		
		tortuga.setPriority(1);
		liebre.setPriority(5);
		guepardo.setPriority(10);
		
		for (int i = 0; i < vAnimales.length; i++) {
			vAnimales[i].start();
		}
	}
}
