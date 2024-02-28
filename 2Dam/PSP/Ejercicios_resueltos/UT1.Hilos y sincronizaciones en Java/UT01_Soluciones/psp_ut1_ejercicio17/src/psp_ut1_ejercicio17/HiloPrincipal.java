package psp_ut1_ejercicio17;

public class HiloPrincipal {
	private int barcosCruzados;
	private int gente;
	private boolean personaPasando;
	private boolean barcoPidePaso;
	private boolean barcoPasando;

	public HiloPrincipal() {
		this.barcosCruzados = 0;
		this.gente = 0;
		this.personaPasando = false;
		this.barcoPasando = false;
		this.barcoPidePaso = false;
	}
	
	public void cruzarPuenteBarco(String nombre) throws InterruptedException {
		entrarPuenteBarco(nombre);
		cruzando();
		salirPuenteBarco(nombre);
	}
	
	public synchronized void entrarPuenteBarco(String nombre) throws InterruptedException {
		while(personaPasando) {
			System.out.println("********** " + nombre + " quiere cruzar bajo el puente **********");
			setBarcoPidePaso(true);
			wait();
		}
		
		setBarcoPasando(true);
		System.out.println("********** " + nombre + " está cruzando bajo el puente **********");
		notifyAll();
	}
	
	public synchronized void salirPuenteBarco(String nombre) {
		System.out.println("********** " + nombre + " ha salido del puente **********");
		setBarcoPasando(false);
		setBarcoPidePaso(false);
		notifyAll();
	}
	
	public void cruzarPuentePersona(String nombre) throws InterruptedException {
		if (!barcoPidePaso) {
			entrarPuentePersona(nombre);
			cruzando();
			salirPuentePersona(nombre);
		}
		
	}
	
	public synchronized void entrarPuentePersona(String nombre) throws InterruptedException {
		while (barcoPasando) {
			wait();
		}
		gente++;
		System.out.println(nombre + " entra en el puente. Hay " + gente);
		setPersonaPasando(true);
		notifyAll();
	}
	
	public synchronized void salirPuentePersona(String nombre) {
		gente--;
		System.out.println(nombre + " sale del puente. Hay " + gente);
		if (gente == 0) {
			setPersonaPasando(false);
		}
		notifyAll();
	}
	
	public void cruzando() throws InterruptedException {
		Thread.sleep(3000);
	}

	public boolean isBarcoPasando() {
		return barcoPasando;
	}

	public void setBarcoPasando(boolean barcoPasando) {
		this.barcoPasando = barcoPasando;
	}

	public boolean isPersonaPasando() {
		return personaPasando;
	}

	public void setPersonaPasando(boolean personaPasando) {
		this.personaPasando = personaPasando;
	}

	public int getGente() {
		return gente;
	}

	public void setGente(int gente) {
		this.gente = gente;
	}

	public boolean isBarcoPidePaso() {
		return barcoPidePaso;
	}

	public void setBarcoPidePaso(boolean barcoPidePaso) {
		this.barcoPidePaso = barcoPidePaso;
	}
	
	public int getBarcosCruzados() {
		return barcosCruzados;
	}

	public void setBarcosCruzados(int barcosCruzados) {
		this.barcosCruzados = barcosCruzados;
	}
}
