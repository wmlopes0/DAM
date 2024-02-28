package psp_ut1_ejercicio13;

public class TicTacToc extends Thread {
	private String salida;
	private Hilo hilo;
	
	public TicTacToc(String salida, Hilo hilo) {
		this.salida = salida;
		this.hilo = hilo;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public Hilo getHilo() {
		return hilo;
	}

	public void setHilo(Hilo hilo) {
		this.hilo = hilo;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			try {
				hilo.pintarReloj(salida);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
