package t02_ejercicio4_walter;


public class Main {

    //Variables globales
    private final static String[] NOMBRES = {"Encarni", "Bacterio", "La de mates", "Eusebio", "Topo Gigio", "Rompetechos", "El gallego"};
    private static Profesor[] profesores = new Profesor[NOMBRES.length];

    public static void main(String[] args) throws InterruptedException {
        //Instancio una Sala de Profesores con una capacidad máxima de 3 personas
        SalaProfesores salaProfesores = new SalaProfesores(3);

        //Instancio e inicio profesores
        for (int i = 0; i < profesores.length; i++) {
            profesores[i] = new Profesor(NOMBRES[i], salaProfesores);
            profesores[i].start();
        }

        //Espero a que terminen todos los hilos para mostrar el mensaje 'FIN PROGRAMA'
        for (int i = 0; i < profesores.length; i++) {
            profesores[i].join();
        }

        //Mensaje Check
        System.out.println("FIN DEL PROGRAMA");
    }
}
