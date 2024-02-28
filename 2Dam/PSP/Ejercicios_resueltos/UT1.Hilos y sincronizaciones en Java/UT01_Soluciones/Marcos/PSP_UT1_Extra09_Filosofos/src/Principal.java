
public class Principal {
	
	
	public static void main(String[] args) {
		Controlador control = new Controlador();
		Filosofo [] filosofos = new Filosofo[5];
		String nombres [] = {"Socrates", "kant", "Locke", "Aristoteles", "Hegel"};
		
		
		for (int i = 0; i < filosofos.length; i++) {
			filosofos[i] = new Filosofo(nombres[i], i, control);
			filosofos[i].start();
		}
	}

	
	
}
