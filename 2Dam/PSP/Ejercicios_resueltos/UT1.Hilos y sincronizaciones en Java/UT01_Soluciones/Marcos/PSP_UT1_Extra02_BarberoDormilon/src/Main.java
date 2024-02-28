
public class Main {
	
public static void main(String[] args) {
		
		final int nSillas     = 5;
		final int nClientes   = 10;
		Barberia  barberia  = new Barberia(nSillas);
		Barbero   barbero   = new Barbero(barberia);
		Cliente[] losClientes = new Cliente[10];
		
		barbero.start();
		
		for (int i = 0; i < nClientes; i++ ) {
			losClientes[i] = new Cliente(i, barberia);
			losClientes[i].start();
		}
	}

}
