package psp_ut1_ej1;

public class main {

	public static void main(String[] args) {
		System.out.println("Este es un proceso que cuenta hasta 100");
		for (int i = 0; i <= 100; i++) {
			System.out.println(i);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
