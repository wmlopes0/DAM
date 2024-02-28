
public class Barberia {
	private int numSillas;
	private int numSillasOcupadas = 0;
	private boolean sillaBarberoOcupada = false;
	private boolean barberoDurmiendo = true;
	private boolean finCorte = false;

	public Barberia(int numSillas) {
		this.numSillas = numSillas;
	}

	public synchronized boolean entrarBarberia(int numCliente) {
		
		if (numSillas == numSillasOcupadas) {// la barbería está llena
			System.out.println("Sala de espera llena, el cliente " + numCliente + " abandona la peluquería");
			return false;
		} else {

			
			// la barbería no está llena, comprobamos si la silla del barbero está libre
			if (sillaBarberoOcupada) {// la silla del barbero está ocupada, esperamos en la sala de espera
				numSillasOcupadas++;
				System.out.println("----El cliente " + numCliente + " se sienta en la sala de espera. Sillas ocupadas: "+numSillasOcupadas);
			}
			// siempre es recomendable hacer la espera con un while
			while (sillaBarberoOcupada) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// libero una silla de la sala de espera, siempre y cuando hubiese alguna ocupada
			if(numSillasOcupadas>0) {numSillasOcupadas--;}
			
			sillaBarberoOcupada = true;
			System.out.println("----El cliente " + numCliente + " se sienta en la silla del Barbero.Sillas ocupadas: "+numSillasOcupadas);
			finCorte=false;
			if (barberoDurmiendo) {
				System.out.println("----El cliente " + numCliente + " despierta al Barbero");
				notifyAll();
			}

			// mientras nos corta el pelo esperamos en la barberia
			while (!finCorte) {
				
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			sillaBarberoOcupada = false;
			System.out.println("\t\t---El cliente " + numCliente + " se va con el pelo cortado");
			finCorte=true;
			// Que pase el siguiente
			notifyAll();

			return true;

		}

	}

	public synchronized void esperarCliente() {
		// El barbero espera a que llegue un cliente, corta el pelo fuera del monitor en
		// el método run()
		// barberoDurmiendo = true;
		while (!sillaBarberoOcupada) {
			System.out.println("Barbero esperando cliente,.. Zzzzzzz");
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		barberoDurmiendo = false;
		System.out.println("Barbero cortando el pelo XXXX");
	}

	public synchronized void acabarCorte() {
		System.out.println("Barbero termina de cortar el pelo");
		finCorte = true;
		sillaBarberoOcupada=false;
		notifyAll();
	}

}
