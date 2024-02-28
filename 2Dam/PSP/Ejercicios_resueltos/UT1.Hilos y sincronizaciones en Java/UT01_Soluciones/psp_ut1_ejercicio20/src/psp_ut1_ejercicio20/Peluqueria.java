package psp_ut1_ejercicio20;

public class Peluqueria {
	private static final int TOTAL_ASIENTOS = 5;
	private int totalClientesSentados;
	private boolean asientoBarbero;
	private boolean cortando;
	
	public Peluqueria() {
		this.totalClientesSentados = 0;
		this.cortando = false;
		this.asientoBarbero = false;
	}
	
	public int getTotalClientesSentados() {
		return totalClientesSentados;
	}

	public void setTotalClientesSentados(int totalClientesSentados) {
		this.totalClientesSentados = totalClientesSentados;
	}

	public boolean isCortando() {
		return cortando;
	}

	public void setCortando(boolean cortando) {
		this.cortando = cortando;
	}

	public boolean isAsientoBarbero() {
		return asientoBarbero;
	}

	public void setAsientoBarbero(boolean asientoBarbero) {
		this.asientoBarbero = asientoBarbero;
	}
	
	public static int getTotalAsientos() {
		return TOTAL_ASIENTOS;
	}
	
	// ########### METODOS PARA EL BARBERO
	
	public synchronized void cortarPelo(String nombre) throws InterruptedException {
		while (!asientoBarbero) { //Si es falso es que no hay nadie sentado
			System.out.println(nombre + " esperando cliente... zzz");
			wait();
		}
		System.out.println(nombre + " cortando pelo XXXXX");
	}
	
	public synchronized void terminaCortar(String nombre) {
		System.out.println(nombre + " termina de cortar pelo");
		setCortando(false);
		notifyAll();
	}

	// ########### METODOS PARA LOS CLIENTES	
	
	public synchronized void sentarseParaCortar(String nombre) throws InterruptedException {
		while (asientoBarbero) { // Si es verdadero la silla está ocupada
			wait();
		}
		
		totalClientesSentados--;
		System.out.println("----- " + nombre + " se sienta en la silla del barbero. Sillas ocupadas: " + totalClientesSentados);
		setAsientoBarbero(true);
		notifyAll();
		
		setCortando(true);
		while (cortando) { //Espera a que termine de cortarle el pelo para despues irse
			wait();
		}
		
		System.out.println(nombre + " se va con el pelo cortado");
		setAsientoBarbero(false);
		
		notifyAll();
	}
	
	public synchronized void sentarseSalaEspera(Cliente cliente) throws InterruptedException {
		cliente.setSentado(true);
		if (totalClientesSentados == TOTAL_ASIENTOS) {
			System.out.println("Sala de espera llena, " + cliente.getNombre() + " abandona la peluquería.");
			cliente.setSentado(false);
			
		}
		
		if (cliente.isSentado()) {
			totalClientesSentados++;
			System.out.println("+++++ " + cliente.getNombre() + " se sienta en la sala de espera. Sillas ocupadas: " + totalClientesSentados);
			
			sentarseParaCortar(cliente.getNombre());
		}
		notifyAll();
	}
}
