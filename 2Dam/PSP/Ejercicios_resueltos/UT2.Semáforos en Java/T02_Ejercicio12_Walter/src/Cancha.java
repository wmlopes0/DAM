import java.util.concurrent.Semaphore;

public class Cancha {

    //Variables y Semáforos
    private int numParticipantes;
    private int jugadoresEsperando;
    private int jugadoresPreparados;
    private Semaphore mutex;
    private Semaphore jugarPartido;
    private Semaphore semaforoEsperarJuego;

    //Constructor
    public Cancha(int numParticipantes) {
        this.numParticipantes = numParticipantes;
        jugadoresEsperando = 0;
        jugadoresPreparados = 0;
        mutex = new Semaphore(1, true);
        jugarPartido = new Semaphore(0, true);
        semaforoEsperarJuego = new Semaphore(0, true);
    }


    public void jugarPartido(Jugador jugador) throws InterruptedException {
        //Mensaje de entrar a la cancha
        mutex.acquire();
        System.out.println("=>" + jugador.getNombre() + " entra en la cancha");
        //Comprobación de jugadores preparados
        if (jugadoresPreparados == numParticipantes) {
            jugadoresEsperando++;
            mutex.release();
            semaforoEsperarJuego.acquire();//Bloqueo el jugador hasta que terminen de jugar
            mutex.acquire();
        }
        //Jugador preparado para jugar
        jugadoresPreparados++;
        if (jugadoresPreparados != numParticipantes) {
            System.out.println("\t\t" + jugador.getNombre() + " está esperando a que lleguen más jugadores [" + jugadoresPreparados + "]");
            mutex.release();
            jugarPartido.acquire();//Bloqueo el jugador hasta que llega mas gente para jugar
        } else {
            System.out.println("\t\t" + jugador.getNombre() + ": -¡¡Ya somos [" + jugadoresPreparados + "] podemos empezar el partido!!");
            mutex.release();
            jugarPartido.release(jugadoresPreparados - 1);//Libero los permisos necesarios para jugar el partido le resto uno porque el jugador que entra en este else no esta esperando en el semáforo
        }
        //Jugando partido
        System.out.println("\t\t\t\t" + jugador.getNombre() + " jugando partido");
        Thread.sleep(10);
        //Abandonando la cancha
        mutex.acquire();
        jugadoresPreparados--;
        if (jugadoresPreparados != 0) {
            System.out.println("\t\t\t\t\t\t" + jugador.getNombre() + " abandonando la cancha...");
        } else {
            System.out.println("\t\t\t\t\t\t" + jugador.getNombre() + " es el/la último/a en salir, avisa al resto");
            semaforoEsperarJuego.release(numParticipantes);//Suelto permisos necesarios
            jugadoresEsperando = 0;//Reseteo contador de jugadores esperando
        }
        mutex.release();
    }
}
