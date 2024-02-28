package ejercicio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio3 {

	private static File rutaEmails = new File("src/ejercicio3/emails");
	private static File rutaTabu = new File("src/ejercicio3/tabu.txt");
	private static List<String> palabrasAlarmantes;

	public static void main(String[] args) throws IOException {
		// Obtengo las palabras alarmantes
		palabrasAlarmantes = getPalabrasAlarmantes();
		// Obtengo la lista de ficheros dentro de la carpeta Emails
		File[] emails = rutaEmails.listFiles();
		// Compruebo los emails
		comprobarEmails(emails);

	}

	// Método que me devuelve una lista con las palabras alarmantes
	public static ArrayList<String> getPalabrasAlarmantes() throws IOException {
		// Creo lista
		List<String> palabrasAlarmantes = new ArrayList<>();
		// Abro flujos
		BufferedReader br = new BufferedReader(new FileReader(rutaTabu));
		String linea = br.readLine();
		// Leo el fichero
		while (linea != null) {
			// Añado cada palabra a la lista
			palabrasAlarmantes.add(linea);
			linea = br.readLine();
		}
		// Cierro flujos
		br.close();
		// Retorno la lista
		return (ArrayList<String>) palabrasAlarmantes;
	}

	// Método para leer cada uno de los emails
	public static void comprobarEmails(File[] emails) throws IOException {
		boolean infringido;
		// Itero cada email, leo y compruebo
		for (int i = 0; i < emails.length; i++) {
			// Abro flujos
			BufferedReader br = new BufferedReader(new FileReader(emails[i]));
			String linea = br.readLine();
			infringido = false;
			while (linea != null && infringido == false) {
				if (comprobarLinea(linea)) {
					System.out.println("El trabajador " + autorEmail(emails[i])
							+ " está infringiendo las normativas de seguridad.");
					infringido = true;
				}
				linea = br.readLine();
			}
		}
	}

	// Método para comprobar si en una linea existe alguna palabra alarmante
	public static boolean comprobarLinea(String linea) {
		boolean palabraProhibida = false;
		int contador = 0;
		// Utilizo un while para una busqueda lineal porque es mas eficiente
		while (contador < palabrasAlarmantes.size() && palabraProhibida == false) {
			if (linea.contains(palabrasAlarmantes.get(contador))) {
				palabraProhibida = true;
			} else {
				contador++;
			}
		}
		return palabraProhibida;
	}

	// Método que extrae el nombre del autor del correo
	public static String autorEmail(File email) {
		String autorEmail = email.getName().split("-")[1];
		return autorEmail.split(".txt")[0];
	}
}
