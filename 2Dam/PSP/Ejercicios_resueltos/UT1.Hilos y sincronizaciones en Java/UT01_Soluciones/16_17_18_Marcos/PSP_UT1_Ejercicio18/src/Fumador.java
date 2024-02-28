
public class Fumador extends Thread {
	Banyo banyo;
	int ingrediente;// 1-tabaco, 2-papel, 3-cerillas

	public Fumador(int _ingrediente, Banyo _banyo) {
		this.ingrediente = _ingrediente;
		this.banyo = _banyo;
	}

	@Override
	public void run() {
		while (true) {
			mostrarInfoFumador(ingrediente);
			banyo.entrarAFumar(ingrediente);
			banyo.salirfumar(ingrediente);
		}

	}

	public void mostrarInfoFumador(int fumador) {

		switch (fumador) {
		case 1:
			System.out.println("El fumador " + fumador + " tiene tabaco, necesita CERILLAS Y PAPEL");

			break;
		case 2:
			System.out.println("El fumador " + fumador + " tiene cerillas, necesita TABACO Y PAPEL");

			break;
		case 3:
			System.out.println("El fumador " + fumador + " tiene papel, necesita TABACO Y CERILLAS");

			break;

		default:
			break;
		}
	}

}
