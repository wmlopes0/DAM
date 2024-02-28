package psp_ut1_ejercicio11;

public class Main {

	public static void main(String[] args) {
		Empresa empresa = new Empresa(1000);
		Repartidor[] vRepartidors = new Repartidor[5];
		String[] nombres = {"Juan","Marta","Pedro","María","Lucía"};
		int totalRepartido = 0;
		
		for (int i = 0; i < vRepartidors.length; i++) {
			vRepartidors[i] = new Repartidor(nombres[i], empresa);
			vRepartidors[i].start();
			
		}
		
		for (int i = 0; i < vRepartidors.length; i++) {
			try {
				vRepartidors[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			totalRepartido = totalRepartido + vRepartidors[i].getNumFlyers();
		}
		
			
		
		System.out.println("\nLa suma de repartidos entre todos es: " + totalRepartido);
		System.out.println("Al final quedan " + empresa.getFlyers() + " flyers.");
	}

}
