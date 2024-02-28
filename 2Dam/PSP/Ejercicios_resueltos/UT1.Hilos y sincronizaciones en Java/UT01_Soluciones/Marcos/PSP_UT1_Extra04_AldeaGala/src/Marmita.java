
public class Marmita {
	private int total_raciones;
	private int raciones_restantes;
	private boolean estaDormidoPanoramix = true;// inicialmente está dormido

	public Marmita(int total_raciones) {
		this.total_raciones = total_raciones;
		this.raciones_restantes = total_raciones;

	}

	public synchronized void beber(String galo) {

		if (raciones_restantes == 0 && estaDormidoPanoramix) {
			// despertamos a Panoramix para que rellene la marmita
			System.out.println("000-La marmita está vacía, " + galo + " despierta a Panoramix");
			estaDormidoPanoramix = false;

		}
		while (raciones_restantes == 0) {// mientras Panoramix rellena la marmita esperamos
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		raciones_restantes--;
		System.out
				.println("---"+galo + " está tomando su ración de brebaje mágico. Raciones restantes: " + raciones_restantes);

		notifyAll();

	}

	public synchronized void rellenar() {
		while (raciones_restantes > 0) {// mientras haya raciones Panoramix duerme
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!estaDormidoPanoramix) {// si ya han avisado a Panoramix rellenamos

			raciones_restantes = total_raciones - 1;
			System.out.println(
					"+++Panoramix ya ha rellenado la marmita, toma una ración. Raciones restantes: " + raciones_restantes);
			estaDormidoPanoramix = true;
		}
		notifyAll();
	}

}
