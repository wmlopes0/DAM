
public class Banyo {
	private int repisa;// representa el ingrediente que falta, es decir, si es 1, tenemos los
						// ingredientes 2 y 3 pero falta el 1
	// TABACO = 1; CERILLAS = 2; PAPEL = 3;
	private boolean alguienFumando;

	public Banyo() {
		// inicialmente la repisa est� vac�a y no hay nadie fumando
		this.repisa = 0;
		this.alguienFumando = false;

	}

	public synchronized void entrarAFumar(int ingrediente) {
		// mientras en la repisa no est�n los ingredientes que necesita el fumador o
		// alguien est� fumando, espera
		//mostrarInfoFumador(ingrediente);
		while (repisa != ingrediente || alguienFumando) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		repisa = 0;// el fumador coge los 2 ingredientes que necesitaba, la repisa se queda vac�a
					// repisa=0;
		alguienFumando = true;
		System.out.println("	El fumador " + ingrediente + " ya tiene todos los ingredientes. Empieza a fumar");
		System.out.println("	==~~ ==~~ Fumador " + ingrediente + " fumando ==~~ ==~~");
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		notifyAll();

	}

	public synchronized void salirfumar(int fumador) {
		alguienFumando = false;
		System.out.println("		El fumador " + fumador + " ya termin� de fumar. Abandona el ba�o");
		notifyAll();

	}
/**
 * El estanquero pondr� al hazar sobre la repisa dos de los ingredientes necesarios para liar un cigarrillo.
 * Si la repisa est� llena o hay alguien fumando no repondr�
 */
	public synchronized void reponer() {
		// mientras la repisa est� llena o haya alguien fumando esperamos
		while ((repisa != 0)||(alguienFumando)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		repisa = generarIngredientes();
		mostrarInfoEstanquero();
		notifyAll();

	}

	/**
	 * Genera un n�mero aleatorio entre 1 y 3, seg�n el valor que genere: 1:tenemos
	 * ingrediente 2 y 3 2: tenemos ingredientes 1 y 3 3: tenemos ingrediente 1 y 2
	 */
	private int generarIngredientes() {

		return (int) (Math.random() * 3 + 1);

	}

	public void mostrarInfoEstanquero() {

		switch (repisa) {
		case 1:
			System.out.println("	=>El estanquero repone ingredientes:en la repisa hay CERILLAS Y PAPEL");

			break;
		case 2:
			System.out.println("	=>El estanquero repone ingredientes:en la repisa hay TABACO Y PAPEL");

			break;
		case 3:
			System.out.println("	=>El estanquero repone ingredientes:en la repisa hay TABACO Y CERILLAS");

			break;

		default:
			break;
		}
	}

	

}
