
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Fibonacci
 */
public class Fibonacci extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Fibonacci() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter escribir= response.getWriter();
		int posicion = Integer.parseInt(request.getParameter("posicion"));  //recibimos la posición
		int rango = Integer.parseInt(request.getParameter("rango"));		//recibimos el rango
		ArrayList<Integer> lNumeros = new ArrayList<>();					//lista de números que se mostrarán
		lNumeros = calcularFibonacci(posicion, rango, lNumeros);			//calculamos los números de la lista
		
		/* Escribimos los resultados en un html */
		escribir.write("<html>");
		escribir.write("<meta charset='UTF-8'>");
		escribir.write("<h1>Resultado<h1>");
		escribir.write(" 	<p>comienza en la "+posicion+ "º es: "+lNumeros+"</p>");
		escribir.write("</html>");
	}

	/**
	 * Método que calcula la sucesión de Fibonacci dentro de un rango, dandole la
	 * posición de inicio. La idea es calcular toda la sucesión hasta llegar al
	 * final, pero almacenar aquellos cuya posición esté dentro del rango. El último
	 * número que deberemos calcular será el que esté en la posición final. Esa
	 * posición final será la suma de la posición inicial + el rango.
	 * 
	 * @param posicion
	 * @param rango
	 * @param lNumeros
	 * @return
	 */
	private ArrayList<Integer> calcularFibonacci(int posicion, int rango, ArrayList<Integer> lNumeros) {
		int num = 1;
		int anterior = 0;
		int aux;

		/*
		 * Calculamos la sucesión hasta llegar a la suma de la posición inicial + el
		 * rango, pero solo añadiremos a la lista los números que estén dentro del rango
		 * (su posición sea igual o mayor a la posición inicial que recibimos por
		 * parámetros).
		 */

		for (int i = 1; i < (posicion + rango); i++) {
			if (i >= posicion) { // Si la posición del número es igual o mayor a la posición inicial...
				lNumeros.add(num); // ... se la añadimos a la lista
			}

			aux = num; // Rescatamos el número antes de actualizarlo
			num = num + anterior; // Caluculamos el siguiente número
			anterior = aux; // Tenemos que guardar también el anterior para hacer el cálculo en la siguiente
							// iteración

		}
		return lNumeros; // Retornamos la lista con los números de la sucesión que estaban dentro del
							// rango
	}

}
