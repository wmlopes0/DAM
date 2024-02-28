
public class Peaton extends Thread {
	Puente control;
	String nombre;
	
	
	public Peaton(Puente control, String nombre) {
		this.control = control;
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			control.pasaPeaton(nombre);
			yield();
		}
	
	}
	
}
