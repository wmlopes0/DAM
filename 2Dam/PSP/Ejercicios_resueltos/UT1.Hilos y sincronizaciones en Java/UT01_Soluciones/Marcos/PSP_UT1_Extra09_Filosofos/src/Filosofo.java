
public class Filosofo extends Thread{
	String nombre;
	int id;
	Controlador control;
	
	
	public Filosofo(String nombre, int id, Controlador control) {
		this.nombre = nombre;
		this.id = id;
		this.control = control;
	}
	
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			control.comer(nombre, id);
			yield();
		}
	}
	
}
