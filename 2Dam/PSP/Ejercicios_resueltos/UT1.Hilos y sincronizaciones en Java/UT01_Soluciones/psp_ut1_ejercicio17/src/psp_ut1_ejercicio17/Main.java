package psp_ut1_ejercicio17;

public class Main {

	public static void main(String[] args) {
		HiloPrincipal hilo = new HiloPrincipal();
		
		Persona juan = new Persona("Juan",hilo);
		Persona marcos = new Persona("Marcos",hilo);
		Persona ana = new Persona("Ana",hilo);
		Persona pedro = new Persona("Pedro",hilo);
		Persona maria = new Persona("Maria",hilo);
		
		Barco barco = new Barco("La perla negra", hilo);
		
		juan.start();
		marcos.start();
		ana.start();
		pedro.start();
		maria.start();
		
		barco.start();
	}

}
