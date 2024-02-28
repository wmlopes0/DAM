package es.iesvjp.psps;

public class DuendeProductor extends Thread {
	private final static int JUGUETES = 12;
	private String nombre;
	private Maquina maquina;

	public DuendeProductor(String nombre, Maquina maquina) {
		this.nombre = nombre;
		this.maquina = maquina;
	}

	public void run() {
		try {
			this.maquina.introducir_juguetes(JUGUETES, this.nombre);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
