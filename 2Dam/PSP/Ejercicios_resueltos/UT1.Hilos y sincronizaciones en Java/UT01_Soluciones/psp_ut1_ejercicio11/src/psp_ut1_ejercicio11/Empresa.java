package psp_ut1_ejercicio11;

public class Empresa {
	private int flyers;

	public Empresa(int flyers) {
		super();
		this.flyers = flyers;
	}

	public int getFlyers() {
		return flyers;
	}

	public void setFlyers(int flyers) {
		this.flyers = flyers;
	}
	
	synchronized public void agarrarFlyer(Repartidor repartidor) {
		if (this.flyers > 0) {
			this.setFlyers(this.getFlyers() - 1);
			repartidor.setNumFlyers(repartidor.getNumFlyers() + 1);
//			System.out.println(repartidor.getNombre() + " ha recogido un flyer.");
		}
	}
}
