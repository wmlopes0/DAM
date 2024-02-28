
public class Barco extends Thread {
	Puente control;
	String nombre;
	
	
	public Barco(Puente control, String nombre) {
		this.control = control;
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			control.pasaBarco(nombre);
			yield();
		}
	}
	
	
}
