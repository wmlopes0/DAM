package psp_ut1_ejercicio19;

public class TubOxigeno extends Thread {
	private String nombre;
	private Controlador controlador;

	public TubOxigeno(Controlador controlador) {
		this.nombre = "Oxigeno";
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
		for (int i = 0; i < 10; i++) {
			try {
				controlador.insertarEnMaquina(nombre);
				Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
