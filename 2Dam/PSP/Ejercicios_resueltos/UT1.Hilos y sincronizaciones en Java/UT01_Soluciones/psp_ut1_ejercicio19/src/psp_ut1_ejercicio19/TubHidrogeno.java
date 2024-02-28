package psp_ut1_ejercicio19;

public class TubHidrogeno extends Thread {
	private String nombre;
	private Controlador controlador;

	public TubHidrogeno(Controlador controlador) {
		this.nombre = "Hidrogeno";
		this.controlador = controlador;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			try {
				controlador.insertarEnMaquina(nombre);
				Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
