package metodosPrincipalesThread;

public class Ejemplo4_interrupt {
    public static void main(String[] args) {
        /*EXPLICACIÓN:
         * El método interrupt() de la clase Thread se utiliza para interrumpir un hilo en ejecución. Esto no significa que
         * el hilo se detenga inmediatamente, sino que recibe una indicación de que ha sido interrumpido. La forma en que
         * el hilo maneja esta interrupción depende de su implementación y de su estado actual.
         *Vamos a desglosarlo:
         *
         * 1-Uso Básico de interrupt():
         *   Cuando invocas el método interrupt() en un hilo, estás estableciendo su estado de interrupción.
         *   Si el hilo estaba bloqueado en operaciones como sleep(), wait(), o join(), lanzará una excepción InterruptedException.
         *
         * 2-Manejo del Estado de Interrupción:
         *   Un hilo puede verificar si ha sido interrumpido llamando al método estático Thread.interrupted(), que limpia
         *   el estado de interrupción, o al método de instancia isInterrupted(), que no lo limpia. Estos métodos permiten al
         *   hilo decidir cómo responder a la interrupción, ya sea deteniendo su ejecución, ignorándola o tomando alguna otra
         *   acción.
         *
         * EJEMPLO:
         * En este ejemplo, el hilo se ejecuta en un bucle y verifica si ha sido interrumpido en cada iteración.
         * Después de 3 segundos, el hilo principal interrumpe el hilo en ejecución usando thread.interrupt().
         * Si el hilo estaba en el sleep(), lanzará InterruptedException. Si no estaba en el sleep(),
         * simplemente detectará el estado de interrupción en la siguiente iteración y se detendrá.
         * */
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    if (Thread.interrupted()) {
                        System.out.println("Hilo interrumpido. Deteniendo la ejecución.");
                        return;  // Detiene la ejecución del hilo
                    }
                    System.out.println("Trabajando: " + i);
                    Thread.sleep(100);  // Simula alguna tarea
                }
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido durante sleep.");
            }
        });

        thread.start();

        // Esperamos 3 segundos y luego interrumpimos el hilo
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}

