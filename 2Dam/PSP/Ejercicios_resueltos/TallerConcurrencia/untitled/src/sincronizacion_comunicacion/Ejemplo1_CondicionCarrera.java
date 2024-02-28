package sincronizacion_comunicacion;

//EXPLICACIÓN:
/*
 * Este programa ilustra un ejemplo clásico de condición de carrera en la programación concurrente,
 * utilizando una "Cuenta" bancaria como recurso compartido.
 *
 * Tenemos dos "Personas" (hilos), Ana y Juan. Ambas tienen acceso a la misma cuenta bancaria y están tratando
 *  de retirar dinero de ella.
 *
 * En un escenario ideal, si una persona retira dinero y la cuenta no tiene suficientes fondos, no debería
 *  permitirse el retiro. Si hay suficientes fondos, se debe actualizar el saldo después de cada retiro.
 *
 * Sin embargo, debido a la condición de carrera en el método "retirar", es posible que ambos hilos verifiquen
 *  el saldo de la cuenta al mismo tiempo, vean que hay suficientes fondos y procedan a retirar el dinero simultáneamente.
 *  Esto puede llevar a que se retire más dinero del disponible, resultando en un saldo negativo,
 *  lo cual no debería ser posible en una cuenta bancaria real.
 *
 * La razón de este comportamiento no determinista es que el acceso al objeto "cuenta" no está sincronizado.
 *  Ambos hilos pueden entrar al método "retirar" al mismo tiempo y operar en el saldo sin que el otro hilo se entere.
 *
 * El retardo introducido con "Thread.sleep(1000)" en el hilo hace que este comportamiento indeterminado sea más probable,
 *  ya que da tiempo a que ambos hilos intenten retirar dinero simultáneamente.
 *
 * La solución para evitar este comportamiento no determinista es sincronizar el acceso al recurso compartido.
 *  En este caso, deberíamos sincronizar el método "retirar" o el bloque de código que modifica el saldo para asegurarnos de que solo un hilo pueda acceder a él a la vez.
 *
 * En resumen: Este programa muestra cómo la falta de sincronización en el acceso a recursos compartidos
 *  en un entorno multi-hilo puede llevar a comportamientos impredecibles y no deseados.
 */

public class Ejemplo1_CondicionCarrera {
    public static void main(String[] args) throws InterruptedException {
        Cuenta cuenta = new Cuenta(50);
        Persona ana = new Persona("Ana", cuenta);
        Persona juan = new Persona("Juan", cuenta);
        ana.start();
        juan.start();

    }
}

class Cuenta {
    private double saldo;

    public Cuenta(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void retirar(double cantidad, String persona) {
        if (getSaldo() >= cantidad) {
//Hay suficiente saldo
            System.out.println("Se va a retirar dinero. El saldo actual es de: "
                    + getSaldo() + "€");
            saldo = saldo - cantidad;
            System.out.println(persona + " retira " + cantidad + " €. (Saldo actual:" + getSaldo() + "€)");
        } else {
//no hay suficiente saldo
            System.out.println(persona + " no puede retirar " + cantidad + " € porque el saldo actual es insuficiente: " + getSaldo() + "€");
        }
        if (getSaldo() < 0) {
            System.out.println("SALDO NEGATIVO:" + getSaldo());
        }
    }
}

class Persona extends Thread {
    private String nombre;
    private Cuenta cuenta;

    public Persona(String nombre, Cuenta cuenta) {
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void run() {
        for (int i = 0; i < 4; i++) {
            this.cuenta.retirar(10, getNombre());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
