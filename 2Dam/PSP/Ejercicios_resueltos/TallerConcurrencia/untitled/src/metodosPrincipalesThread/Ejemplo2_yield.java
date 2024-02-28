package metodosPrincipalesThread;

public class Ejemplo2_yield {
    public static void main(String[] args) {
        /*EXPLICACIÓN:
        * Implementamos un modelo Productor-Consumidor para ejemplificar el uso del método Yield().
        * Este método tiene como función principal, SUGERIR al planificador de hilos que el hilo que lo llama
        * puede ceder el paso a otros hilos de su misma prioridad.
        * El tema esque solo lo sugiere, por lo que no hay garantías de que el plaificador ceda realmente el paso a otro hilo.
        * Por eso la salida de este programa en concreto usando o no el yield podría ser la misma.
        * */


//        Creo un hilo productor
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Produciendo: " + i);
                Thread.yield(); // Sugerencia para ceder el turno
            }
        });
//         Creo un hilo consumidor
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Consumiendo: " + i);
                Thread.yield(); // Sugerencia para ceder el turno
            }
        });

//        Inicio los hilos
        producer.start();
        consumer.start();
    }
}
