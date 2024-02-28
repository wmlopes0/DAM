package sincronizacion_comunicacion;
//EXPLICACIÓN:
/*
 * Este programa ilustra cómo abordar la condición de carrera utilizando el mecanismo de sincronización provisto por Java.
 *
 * En este caso, la clase "Cuenta2" representa una cuenta bancaria con operaciones básicas como retirar dinero. La operación
 *  "retirar" se ha marcado como 'synchronized', lo que significa que en cualquier momento dado, solo un hilo puede acceder
 * a este método en una instancia específica del objeto "Cuenta2".
 *
 * Dado que la operación "retirar" es crítica (dos hilos no deben retirar dinero al mismo tiempo sin verificar adecuadamente
 *  el saldo), la sincronización garantiza que las retiradas se manejen de manera segura.
 *
 * La clase "Persona2" representa a una persona que intenta retirar dinero de la cuenta. Aquí, "Ana" y "Juan" son dos personas
 *  (hilos) que intentan retirar dinero de la misma cuenta bancaria.
 *
 * Sin la palabra clave 'synchronized' en el método "retirar", podríamos haber enfrentado problemas de condición de carrera,
 *  donde ambos hilos podrían haber retirado dinero al mismo tiempo, posiblemente llevando el saldo a un estado negativo.
 *  Pero con 'synchronized', garantizamos que solo un hilo pueda retirar dinero a la vez, lo que hace que las operaciones
 *  de retiro sean seguras.
 *
 * En resumen, este programa muestra cómo se puede utilizar la palabra clave 'synchronized' en Java para protegerse contra
 *  condiciones de carrera al garantizar el acceso exclusivo a secciones críticas del código. Aunque se introduce un ligero
 *  costo de rendimiento debido a la sincronización, la integridad de los datos y la seguridad de las operaciones son de suma
 *  importancia, especialmente en situaciones como las operaciones bancarias.
 */

public class Ejemplo2_Synchronized_CondicionCarrera {
    public static void main(String[] args) throws InterruptedException {
        Cuenta2 cuenta = new Cuenta2(50);
        Persona2 ana = new Persona2("Ana", cuenta);
        Persona2 juan = new Persona2("Juan", cuenta);
        ana.start();
        juan.start();
    }
}

class Cuenta2 {
    private double saldo;

    public Cuenta2(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public synchronized void retirar(double cantidad, String persona) {
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

class Persona2 extends Thread {
    private String nombre;
    private Cuenta2 cuenta;

    public Persona2(String nombre, Cuenta2 cuenta) {
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