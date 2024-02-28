package acadt_ut1_p3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) throws IOException {
		File fichero = new File("tiempo.dat");

		// Introduzco temperaturas
		System.out.println("====== INTRODUCE LAS TEMPERATURAS DE LA SEMANA ======");
		introducirTemperaturas(fichero);
		// Mostrar dia mas caluroso
		System.out.println("====== EL DIA MAS CALUROSO DE LA SEMANA FUE ======");
		diaMasCaluroso(fichero);
	}

	public static void introducirTemperaturas(File fichero) throws IOException {
		String fecha;
		double tempMin, tempMax;
		int dias = 0;

		// ABRO FLUJO
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero));

		do {
			fecha = pedirCadena("Introduzca la fecha "+(dias+1)+":");
			dos.writeUTF(fecha);
			tempMin = pedirDouble("Introduzca la temperatura minima:");
			dos.writeDouble(tempMin);
			tempMax = pedirDouble("Introduzca la temperatura maxima:");
			dos.writeDouble(tempMax);
			dias++;
		} while (dias != 7);

		// CIERRO FLUJO
		dos.close();
	}

	public static void diaMasCaluroso(File fichero) throws IOException {
		String fechaAux;
		double tempMinAux, tempMaxAux, mediaAux;
		String fecha = "";
		double tempMin = 0;
		double tempMax = 0;
		double media = 0;

		// ABRO FLUJO
		DataInputStream dis = new DataInputStream(new FileInputStream(fichero));

		while (dis.available() > 0) {
			fechaAux = dis.readUTF();
			tempMinAux = dis.readDouble();
			tempMaxAux = dis.readDouble();
			// Calculo media
			mediaAux = (tempMinAux + tempMaxAux) / 2;
			// Compruebo si es mayor a la almacenada
			if (mediaAux > media) {
				fecha = fechaAux;
				tempMin = tempMinAux;
				tempMax = tempMaxAux;
				media = mediaAux;
			}
		}

		// MUESTRO
		System.out.println("FECHA:" + fecha);
		System.out.println("TEMPERATURA MINIMA:" + tempMin);
		System.out.println("TEMPERATURA MAXIMA:" + tempMax);
		System.out.println("MEDIA:" + media);

		// CIERRO FLUJO
		dis.close();
	}

	public static String pedirCadena(String texto) {
		Scanner s = new Scanner(System.in);
		String cadena;
		System.out.println(texto);
		cadena = s.nextLine();
		return cadena;
	}

	public static double pedirDouble(String texto) {
		Scanner s = new Scanner(System.in);
		double num;
		System.out.println(texto);
		try {
			num = s.nextDouble();
		} catch (InputMismatchException e) {
			System.out.println("ERROR.NO PUEDES INTRODUCIR UNA LETRA.");
			num = pedirDouble(texto);
		}
		return num;
	}
}
