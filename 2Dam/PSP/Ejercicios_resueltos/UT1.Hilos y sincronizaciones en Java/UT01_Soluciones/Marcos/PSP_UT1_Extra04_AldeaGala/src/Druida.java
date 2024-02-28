
public class Druida extends Thread{
	Marmita marmita;
	
	public Druida(Marmita marmita) {
		this.marmita = marmita;
	}

	@Override
	public void run() {
		while (true)
		{
			marmita.rellenar();
		}
	}

}
