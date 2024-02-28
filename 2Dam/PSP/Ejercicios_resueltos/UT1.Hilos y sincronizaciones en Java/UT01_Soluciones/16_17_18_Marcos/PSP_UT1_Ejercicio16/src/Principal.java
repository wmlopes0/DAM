
public class Principal {

	public static void main(String[] args) {
		Banyo banyo= new Banyo();
		Profesor [] profesores = new Profesor[5];
		String [] nombresPersonas = {"Juan", "María", "Pedro", "Ana", "Marcos"};
		
		for (int i = 0; i < nombresPersonas.length; i++) {
			profesores[i] = new Profesor(banyo, nombresPersonas[i]);
			profesores[i].start();
		}
		
	}

}
