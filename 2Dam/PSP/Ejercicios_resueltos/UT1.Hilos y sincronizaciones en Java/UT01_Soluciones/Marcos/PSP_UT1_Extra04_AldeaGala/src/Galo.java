public class Galo extends Thread{
	String nombre;
	Marmita marmita;
	public Galo(String nombre,Marmita marmita) {
		
		this.nombre = nombre;
		this.marmita=marmita;
	}
	
	@Override
	public void run() {
		while(true)
		{
			marmita.beber(nombre);
			try {
				sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
