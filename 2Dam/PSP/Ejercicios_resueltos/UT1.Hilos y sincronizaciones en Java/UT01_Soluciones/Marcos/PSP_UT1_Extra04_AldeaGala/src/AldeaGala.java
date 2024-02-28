
public class AldeaGala {
	
	public static void main(String[] args) {
		String[] nombres={"Asterix", "Obelix", "Idéafix", "Falbalá", "Asurancetúrix", "Abraracúrcix"};
		Galo[] galos= new Galo[nombres.length];
		Marmita marmita= new Marmita(4);
		for (int i = 0; i < galos.length; i++) {
			galos[i]= new Galo(nombres[i],marmita);
			galos[i].start();
			
		}
		Druida druida= new Druida(marmita);
		druida.start();
	}

}
