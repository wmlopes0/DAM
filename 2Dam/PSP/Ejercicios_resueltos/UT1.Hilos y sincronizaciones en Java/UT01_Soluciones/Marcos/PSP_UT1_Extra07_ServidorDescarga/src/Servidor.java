
public class Servidor {
	private Archivo[] archivos= new Archivo[10];
	private float descargandose;
	private final static float LIMITE=10240;//10GB

	public Servidor() {
		rellenarArchivos();
		descargandose=0;
		
	}
	public Archivo[] getArchivos()
	{
		return archivos;
	}
	
	private void rellenarArchivos()
	{
		//los archivos tienen un tamaño entre 300MB y 5 GB(5120MB)
		for (int i = 0; i < archivos.length; i++) {
		//	float tamanyo= (float) (Math.random()*5120) +300;
			float tamanyo= 9000;
			archivos[i]= new Archivo("archivo-"+i, tamanyo);
		}
		
	}
	
	public void descargar(String usuario, Archivo archivo)
	{
		System.out.println("El usuario "+ usuario+ " desea descargarse el archivo: "+ archivo.getNombre()+ " de "+ archivo.getTamanyo()+ "MB");
		synchronized (this) {
			while(descargandose+archivo.getTamanyo()>LIMITE) 
			{
				System.out.println("XXActualmente se están descargando simultaneamente "+ descargandose +"MB. Usuario: "+ usuario+ " esperando...");
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}	
			descargandose+=archivo.getTamanyo();
		}
		
		System.out.println("\t==>Se está descargando el archivo....."+archivo.getNombre()+ "("+usuario+") de "+ archivo.getTamanyo()+ "MB");
		//1MB--->  1ms
		long tiempo=(long) archivo.getTamanyo()*1;
	
		try {
			Thread.currentThread().sleep(tiempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized (this) {
			descargandose-=archivo.getTamanyo();
			System.out.println("\t\tArchivo "+archivo.getNombre()+ "("+usuario+") descargado...... ");
			notifyAll();
			
		}
		
	}
	

}
