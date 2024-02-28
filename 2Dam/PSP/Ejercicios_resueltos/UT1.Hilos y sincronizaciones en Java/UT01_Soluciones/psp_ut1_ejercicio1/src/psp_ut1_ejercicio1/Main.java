package psp_ut1_ejercicio1;

public class Main {

	public static void main(String[] args) {
		Hora hora = new Hora();
		Sonido sonido = new Sonido();
		Tiempo tiempo = new Tiempo();
		
		Thread tHora = new Thread(hora);
		Thread tSonido = new Thread(sonido);
		Thread tTiempo = new Thread(tiempo);
		
		tHora.start();
		tSonido.start();
		tTiempo.start();
	}

}
