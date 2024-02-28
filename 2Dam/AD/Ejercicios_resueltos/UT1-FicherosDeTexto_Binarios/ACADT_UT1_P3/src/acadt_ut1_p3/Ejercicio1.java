package acadt_ut1_p3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
		File fichero = new File("notas.dat");

		int opcion;

		do {
			opcion = mostrarMenu();
			switch (opcion) {
			case 1:
				try {
					introducirAlumno(fichero);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					visualizarFichero(fichero);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					visualizarMejorMedia(fichero);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				System.out.println("¡HASTA PRONTO!");
				break;
			default:
				System.out.println("ERROR. POR FAVOR INTRODUZCA UNA OPCION VALIDA.");
			}

		} while (opcion != 4);
	}

	// OPCION 1
	public static void introducirAlumno(File fichero) throws IOException {
		String dni, nombre, apellido;
		float notaRedes, notaProgramacion;

		// ABRO FLUJO
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero));

		do {
			dni = pedirCadena("Introduzca el DNI:");
			dos.writeUTF(dni);
			nombre = pedirCadena("Introduzca el nombre:");
			dos.writeUTF(nombre);
			apellido = pedirCadena("Introduzca el apellido:");
			dos.writeUTF(apellido);
			notaRedes = pedirFloat("Introduzca la nota de Redes:");
			dos.writeFloat(notaRedes);
			notaProgramacion = pedirFloat("Introduzca la nota de Programacion:");
			dos.writeFloat(notaProgramacion);
		} while (seguir());

		// CIERRO FLUJO
		dos.close();
	}

	// OPCION 2
	public static void visualizarFichero(File fichero) throws IOException {

		// ABRO FLUJO
		DataInputStream dis = new DataInputStream(new FileInputStream(fichero));

		while (dis.available() > 0) {
			System.out.println("------------------------");
			System.out.println("DNI: " + dis.readUTF());
			System.out.println("Nombre: " + dis.readUTF());
			System.out.println("Apellido: " + dis.readUTF());
			System.out.println("Nota Redes: " + dis.readFloat());
			System.out.println("Nota Programacion: " + dis.readFloat());
			System.out.println("------------------------");
		}

		// CIERRO FLUJO
		dis.close();
	}

	// OPCION 3
	public static void visualizarMejorMedia(File fichero) throws IOException {
		String dniAux, nombreAux, apellidoAux;
		float notaRedesAux, notaProgramacionAux, mediaAux;
		String dni = "";
		String nombre = "";
		String apellido = "";
		float notaRedes = 0;
		float notaProgramacion = 0;
		float media = 0;

		// ABRO FLUJO
		DataInputStream dis = new DataInputStream(new FileInputStream(fichero));

		while (dis.available() > 0) {
			dniAux = dis.readUTF();
			nombreAux = dis.readUTF();
			apellidoAux = dis.readUTF();
			notaRedesAux = dis.readFloat();
			notaProgramacionAux = dis.readFloat();
			// Calculo media
			mediaAux = (notaRedesAux + notaProgramacionAux) / 2;
			// Compruebo si es mayor a la almacenada
			if (mediaAux > media) {
				dni = dniAux;
				nombre = nombreAux;
				apellido = apellidoAux;
				notaRedes = notaRedesAux;
				notaProgramacion = notaProgramacionAux;
			}
		}

		// MUESTRO
		System.out.println("EL ALUMNO CON MEJOR NOTA MEDIA ES:");
		System.out.println("DNI: " + dni);
		System.out.println("Nombre: " + nombre);
		System.out.println("Apellido: " + apellido);
		System.out.println("Nota Redes: " + notaRedes);
		System.out.println("Nota Programacion: " + notaProgramacion);
		// CIERRO FLUJO
		dis.close();
	}

	public static int mostrarMenu() {
		Scanner s = new Scanner(System.in);
		System.out.println("========MENU========");
		System.out.println("1. Introducir alumnos.");
		System.out.println("2. Visualizar el fichero.");
		System.out.println("3. Visualizar los usuarios con mejor media.");
		System.out.println("4. Salir.");
		System.out.println("----------------------");
		System.out.println("Por favor introduce una opcion: ");
		return s.nextInt();
	}

	// MÉTODOS PEDIR
	public static boolean seguir() {
		Scanner s = new Scanner(System.in);
		String respuesta;
		System.out.println("¿Desea seguir introduciendo usuarios?");
		respuesta = s.nextLine();
		return respuesta.equalsIgnoreCase("si");
	}

	public static String pedirCadena(String texto) {
		Scanner s = new Scanner(System.in);
		String cadena;
		System.out.println(texto);
		cadena = s.nextLine();
		return cadena;
	}

	public static float pedirFloat(String texto) {
		Scanner s = new Scanner(System.in);
		float num;
		System.out.println(texto);
		try {
			num = s.nextFloat();
		} catch (InputMismatchException e) {
			System.out.println("ERROR.NO PUEDES INTRODUCIR UNA LETRA.");
			num = pedirFloat(texto);
		}
		return num;
	}
}
