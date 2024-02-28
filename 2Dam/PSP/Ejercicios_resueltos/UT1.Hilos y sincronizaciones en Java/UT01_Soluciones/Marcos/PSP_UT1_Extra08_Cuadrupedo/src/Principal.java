
public class Principal {
	public static void main(String[] args) {
		
		Controlador control = new Controlador();
		Pata patas[] = new Pata[4];
		
		for (int i = 0; i < patas.length; i++) {
			patas[i] = new Pata(i, control);
			patas[i].start();
		}
	}
}
