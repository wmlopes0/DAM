package es.iesvjp.psp;

/*
 * Las plazas del Parking están numeradas, y nos interesa saber qué plaza ocupa cada coche, en
cada momento. La plaza que quede libre será ocupada por el siguiente que entre. En un
principio todas las plazas están libres.
Cada vez que entre y salga un coche debemos mostrar actualizadas las plazas que quedan
disponible
 * */
public class Main {
	public static void main(String[] args) {
		Parking parking = new Parking();
		for (int i = 1; i <= 5; i++) {
			Coche coche = new Coche(parking, i);
			Thread th = new Thread(coche);
			th.start();
		}
	}
}
