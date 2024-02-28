package psp_ut1_ejercicio13;

public class Hilo {
	private String valor;
	
	public Hilo() {
		this.valor = "TOC";
	}
	
	public synchronized void pintarReloj(String pintar) throws InterruptedException {
		if (pintar.equalsIgnoreCase("tic") && valor.equalsIgnoreCase("toc")) {
			Thread.sleep(333);
			System.out.println(pintar);
			this.valor = pintar;
			notifyAll();
		} else {
			notifyAll();
			wait();
		}
		
		if (pintar.equalsIgnoreCase("tac") && valor.equalsIgnoreCase("tic")) {
			Thread.sleep(333);
			System.out.println(pintar);
			this.valor = pintar;
			notifyAll();
		} else {
			notifyAll();
			wait();
		}

		if (pintar.equalsIgnoreCase("toc") && valor.equalsIgnoreCase("tac")) {
			Thread.sleep(333);
			System.out.println(pintar);
			this.valor = pintar;
			notifyAll();
		} else {
			notifyAll();
			wait();
		}
	}
}
