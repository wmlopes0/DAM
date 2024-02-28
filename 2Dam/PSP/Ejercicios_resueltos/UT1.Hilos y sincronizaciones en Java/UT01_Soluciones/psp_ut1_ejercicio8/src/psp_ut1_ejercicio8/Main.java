package psp_ut1_ejercicio8;

public class Main {

	public static void main(String[] args) {
		int [][] matriz1 = {{1,2,3},{4,5,6},{7,8,9}};
		int [][] matriz2 = {{4,2,1},{9,8,1},{1,1,0}};
		int [][] matrizResultado = new int [3][3];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Calculo hilo = new Calculo(i, j);
			}
		}
	}

}
