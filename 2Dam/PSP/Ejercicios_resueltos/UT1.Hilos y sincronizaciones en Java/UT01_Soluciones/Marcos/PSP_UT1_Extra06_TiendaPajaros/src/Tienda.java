
public class Tienda {

	public static void main(String[] args) {
		Jaula jaula= new Jaula();
		Encargado encargado= new Encargado(jaula);
		Canario[] canarios= new Canario[10];
		for (int i = 0; i < canarios.length; i++) {
			canarios[i]=new Canario(jaula, i);
			canarios[i].start();
		}
		
		encargado.start();
		

	}

}
