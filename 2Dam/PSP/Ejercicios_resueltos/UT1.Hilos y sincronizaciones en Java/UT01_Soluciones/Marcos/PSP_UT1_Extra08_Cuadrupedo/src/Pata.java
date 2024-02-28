
public class Pata extends Thread{
	int id;
	Controlador control;
	
	
	public Pata(int id, Controlador control) {
		this.id = id;
		this.control = control;
	}
	
	
	@Override
	public void run() {
		while(true){
			control.mover(id);
			yield();
		}
	}
}
