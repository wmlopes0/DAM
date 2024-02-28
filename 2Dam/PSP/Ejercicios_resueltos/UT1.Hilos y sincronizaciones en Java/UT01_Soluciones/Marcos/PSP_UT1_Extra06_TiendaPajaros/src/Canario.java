
public class Canario extends Thread{
	Jaula jaula;
	int numero;
	
	
	public Canario(Jaula jaula,int numero) {
		this.jaula = jaula;
		this.numero=numero;
		
	}


	@Override
	public void run() {
	//	for (int i = 0; i < 2; i++) 
	//	{
		
			jaula.comerAlpiste(numero);
			try {
				sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
	
	}

}