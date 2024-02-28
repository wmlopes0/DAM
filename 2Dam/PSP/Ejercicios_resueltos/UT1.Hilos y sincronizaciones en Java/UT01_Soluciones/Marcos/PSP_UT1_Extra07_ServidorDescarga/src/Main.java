
public class Main {
	
	
	public static void main(String[] args) {
		Servidor servidor= new Servidor();
		String[] nombres= {"Marta","Sergio", "David", "Maria"};
		Usuario[] usuarios= new Usuario[nombres.length];
		
		for (int i = 0; i < usuarios.length; i++) {
			usuarios[i]= new Usuario(nombres[i],servidor);
			usuarios[i].start();
			
		}
	}

}
