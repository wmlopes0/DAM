
public class Usuario extends Thread{
	String nombre;
	Servidor servidor;
	

	
	public Usuario(String nombre, Servidor servidor) {
		this.nombre = nombre;
		this.servidor = servidor;
	}



	public void run() {
		
		for (int i = 0; i < 2; i++) {
			int archivo=(int) (Math.random()*9)+0;
			//System.out.println(archivo);
			servidor.descargar(nombre, servidor.getArchivos()[archivo]);
			
		}
		
	}
	
	

}
