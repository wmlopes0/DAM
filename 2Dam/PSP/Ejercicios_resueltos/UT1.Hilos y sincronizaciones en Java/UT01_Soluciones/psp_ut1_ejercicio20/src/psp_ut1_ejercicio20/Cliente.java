package psp_ut1_ejercicio20;

public class Cliente extends Thread{
	private String nombre;
	private boolean sentado;
	private Peluqueria peluqueria;
	
	public Cliente(String nombre, Peluqueria peluqueria) {
		this.nombre = nombre;
		this.sentado = false;
		this.peluqueria = peluqueria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isSentado() {
		return sentado;
	}

	public void setSentado(boolean sentado) {
		this.sentado = sentado;
	}

	public Peluqueria getPeluqueria() {
		return peluqueria;
	}

	public void setPeluqueria(Peluqueria peluqueria) {
		this.peluqueria = peluqueria;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				peluqueria.sentarseSalaEspera(this);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
