public class Club {
	private static final int TOTAL_PELOTAS = 15;
	private static final int TOTAL_PALOS = 15;
	private static final String EXPERTO = "+";
	private static final String NOVATO = "-";
	private int numpalos;
	private int numpelotas;
	private int jugando;

	public Club() {

		this.numpalos = TOTAL_PALOS;
		this.numpelotas = TOTAL_PELOTAS;
		this.jugando=0;
	}

	public synchronized void reservaMaterial(Jugador jugador) {
		int pelotas = getPelotas(jugador.getNivel());
		int palos = getPalos(jugador.getNivel());
		System.out.println("Jugador:" + jugador.getNumero() + jugador.getNivel() + " quiere jugar al golf, necesita ["+palos+","+pelotas+"]. Hay "+numpalos+ " palos y "+numpelotas+" pelotas");
		if((palos > numpalos) || (pelotas > numpelotas))
		{
			System.out.println("xxxJugador:" + jugador.getNumero() + jugador.getNivel() +",no puede alquilar material.Actualmente hay "+numpalos+ " palos y "+numpelotas+" pelotas");
			
		}
		
		// mientras no haya material suficiente el jugador espera
		while ((palos > numpalos) || (pelotas > numpelotas)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		jugador.setPalos(palos);
		jugador.setPelotas(pelotas);
		numpalos-= palos;
		numpelotas-=pelotas;

		System.out.println("Jugador:" + jugador.getNumero() + jugador.getNivel() + " alquila material[" + palos + ","
				+ pelotas + "]");
		
	}

	public void jugar(Jugador jugador) {
//		System.out.println("Jugando: "+ jugando);
//		if (jugando>4) {
//			System.out.println("xxxxEl campo de golf está lleno.Jugador "+ jugador.getNumero()+ jugador.getNivel()+ "espera...");
//		}
//		synchronized (this) {
//			while(jugando>4) {
//				try {
//					wait();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		}
//		synchronized (this) {
//			jugando++;
//		}
		System.out.println("\tJugador:" + jugador.getNumero() + jugador.getNivel() + " está jugando al golf ");
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public synchronized void devolverMaterial(Jugador jugador) {
		numpelotas += jugador.getPelotas();
		numpalos += jugador.getPalos();
		System.out.println("\t\tJugador:" + jugador.getNumero() + jugador.getNivel() + "terminó el partido, devuelve el material["
				+ jugador.getPalos() + "," + jugador.getPelotas() + "]");
		jugador.setPalos(0);
		jugador.setPelotas(0);
		jugando--;
		notifyAll();

	}

	public static int getPelotas(String jugador) {
		int pelotas = 0;
		if (jugador == EXPERTO) {
			pelotas = 1;
		} else {
			pelotas = (int) (Math.random() * 5 + 1);
		}
		return pelotas;

	}

	public static int getPalos(String jugador) {
		int palos = 0;
		if (jugador == EXPERTO) {
			palos = (int) (Math.random() * 5 + 2);
		} else {
			palos = 2;
		}
		return palos;

	}
}
