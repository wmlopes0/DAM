package metodosPrincipalesThread;

public class Ejemplo3_join {
    public static void main(String[] args) throws InterruptedException {
        /*EXPLICACIÓN:
         * El uso de join() es una forma efectiva de sincronizar la ejecución de hilos
         * cuando necesitas asegurarte de que un hilo haya completado su tarea antes de que otro continúe.
         * También se puede especificar el número de milis máximo que deseamos esperar a que el hilo termine.
         *
         * En este caso concreto, tenemos 3 hilos, el main, el producer y el consumer
         * Cuando iniciamos el hilo principal se inician el producer y el consumer pero el hilo principal sigue, por
         * lo que el mensaje de 'Hilo principal terminado.' saldrá al principio porque es el primero en acabar y seguidamente
         * terminarán los hilos producer y consumer.
         *
         * Cuando hacemos uso del join, el hilo principal no puede acabar hasta que terminen los hilos consumer y producer
         * ya que eso hemos especificado al usar el método join, por lo que el mensaje de 'Hilo principal terminado.' saldrá
         * al final de ejecución. Es más, si no hicieramos uso del método yield, primero acabaria el productor, luego el consumidor
         * y por último el hilo main.
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

//        MOSTRAMOS PRIORIDAD DE TODOS LOS HILOS
//        System.out.println("Producer: "+producer.getPriority());
//        System.out.println("Consumer: "+consumer.getPriority());
//        System.out.println("Main: "+Thread.currentThread().getPriority());

        producer.join();  // Esperamos a que producer termine su ejecución
        consumer.join();  // Esperamos a que consumer termine su ejecución

        System.out.println("Hilo principal terminado.");
    }
}
