
public class Jugar {

	public static void main(String[] args) {
		Club club= new Club();
		Jugador[] jugadores= new Jugador[12];
		for (int i = 0; i < jugadores.length; i++) {
			if(i%2==0)
			{
				jugadores[i]= new Jugador(i,"+", club);
			}
			else 
			{
				jugadores[i]= new Jugador(i,"-", club);
			}
			jugadores[i].start();
		}

	}

}
