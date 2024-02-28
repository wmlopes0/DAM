package es.iesvjp.psp;

public class Principal {
	public static void main(String[] args) {
		String[] vNombres = { "Antonio", "Maria", "Jesus", "Pedro", "Juan", "Alicia", "Mario", "Enrique", "Paula",
				"Rodrigo", "Jorge", "Roberto" };
		Jugador[] vJugadores = new Jugador[vNombres.length];
		Pista pista = new Pista();

		for (int i = 0; i < vJugadores.length; i++) {
			vJugadores[i] = new Jugador(vNombres[i], pista);
			vJugadores[i].start();
		}
	}

}
