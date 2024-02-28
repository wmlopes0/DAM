package es.iesvjp.psp;

public class Coche implements Runnable {
	Parking parking;
	int numcoche;
	int plaza;

	public Coche(Parking parking, int numcoche) {
		this.parking = parking;
		this.numcoche = numcoche;
		this.plaza = 0;
	}

	public int getNumcoche() {
		return numcoche;
	}

	public void setNumcoche(int numcoche) {
		this.numcoche = numcoche;
	}

	public int getPlaza() {
		return plaza;
	}

	public void setPlaza(int plaza) {
		this.plaza = plaza;
	}

	@Override
	public void run() {
		System.out.println("Arranca el coche número " + getNumcoche());
		try {
			// el coche sale de su casa y se da una vuelta por Plasencia
			Thread.sleep((long) Math.random() * 40000);
			System.out.println("\t||El coche número " + getNumcoche() + " llega al parking e intenta entrar");
			parking.entrarParking(this);
			// encuentra aparcamiento y se va de compras
			Thread.sleep((long) Math.random() * 90000);
			parking.salirParking(this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
