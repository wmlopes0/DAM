package es.iesvjp.psp;



public class Principal {

	public static void main(String[] args) {
		Ambulancia[] ambulancia=new Ambulancia[6];
		
		Parking parking=new Parking();
		
		for (int i = 0; i < ambulancia.length; i++) {
			ambulancia[i]=new Ambulancia(i, parking);
			ambulancia[i].start();
		}
		

	}

}
