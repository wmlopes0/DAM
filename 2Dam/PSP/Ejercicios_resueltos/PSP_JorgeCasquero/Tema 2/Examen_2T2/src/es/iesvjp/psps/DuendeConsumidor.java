package es.iesvjp.psps;

public class DuendeConsumidor extends Thread {
	final static int JUGUETES = 10;
	private String nombre;
	private Maquina maquina;

	public DuendeConsumidor(String nombre, Maquina maquina) {
		this.nombre = nombre;
		this.maquina = maquina;
	}

	@Override
	public void run() {
		try {
			this.maquina.recoger_juguetes(JUGUETES, this.nombre);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
