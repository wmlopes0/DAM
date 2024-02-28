
public class Jugador extends Thread{
	private int numero;
	private String nivel;
	private int pelotas;
	private int palos;
	Club club;
	
	public Jugador(int numero, String nivel, Club club) {
		this.numero = numero;
		this.nivel = nivel;
		this.palos=0;
		this.pelotas=0;
		this.club=club;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public int getPelotas() {
		return pelotas;
	}
	public void setPelotas(int pelotas) {
		this.pelotas = pelotas;
	}
	public int getPalos() {
		return palos;
	}
	public void setPalos(int palos) {
		this.palos = palos;
	}
	@Override
	public void run() {
		
		club.reservaMaterial(this);
		club.jugar(this);
		club.devolverMaterial(this);
	}
	
	

}
