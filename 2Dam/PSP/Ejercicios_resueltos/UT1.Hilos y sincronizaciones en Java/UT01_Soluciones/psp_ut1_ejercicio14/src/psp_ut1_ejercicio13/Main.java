package psp_ut1_ejercicio13;

public class Main {

	public static void main(String[] args) {
		Hilo hilo = new Hilo();
		TicTacToc tic = new TicTacToc("TIC", hilo);
		TicTacToc tac = new TicTacToc("TAC", hilo);
		TicTacToc toc = new TicTacToc("TOC", hilo);
		
		tic.start();
		tac.start();
		toc.start();
		
	}

}
