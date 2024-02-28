package psp_ut1_ejercicio10;

public class Hilo extends Thread{
	private int num;
	private HiloPrincipal hp;

	public Hilo(int num, HiloPrincipal hp) {
		super();
		this.num = num;
		this.hp = hp;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public HiloPrincipal getHp() {
		return hp;
	}

	public void setHp(HiloPrincipal hp) {
		this.hp = hp;
	}

	@Override
	public void run() {
		hp.multiplos(num);
	}
}
