
public class Principal {

	public static void main(String[] args) {
		Recurso recurso = new Recurso();
		Escritor escritores[] = new Escritor[2];
		Lector lectores [] = new Lector[3];
		
		for (int i = 0; i < escritores.length; i++) {
			escritores[i] = new Escritor("Escritores"+(i+1), recurso);
			escritores[i].start();
		}
		
		for (int i = 0; i < lectores.length; i++) {
			lectores[i] = new Lector("Lectores"+(i+1), recurso);
			lectores[i].start();
		}
		
	}
	
}
