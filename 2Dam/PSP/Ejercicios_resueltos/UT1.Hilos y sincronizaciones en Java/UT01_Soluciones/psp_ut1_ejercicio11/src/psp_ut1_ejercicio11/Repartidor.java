package psp_ut1_ejercicio11;

public class Repartidor extends Thread {
	private String nombre;
	private Empresa empresa;
	private int numFlyers;
	
	public Repartidor(String nombre, Empresa empresa) {
		super();
		this.nombre = nombre;
		this.empresa = empresa;
		this.numFlyers = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public int getNumFlyers() {
		return numFlyers;
	}

	public void setNumFlyers(int numFlyers) {
		this.numFlyers = numFlyers;
	}
	
	@Override
	public void run() {
		while (empresa.getFlyers() > 0) {
			empresa.agarrarFlyer(this);
			Thread.yield();
		}
		if (empresa.getFlyers() == 0) {
			System.out.println("El repartidor " + this.getNombre() + " ha repartido " + this.getNumFlyers() + " flyers.");
		}
		
	}
}
