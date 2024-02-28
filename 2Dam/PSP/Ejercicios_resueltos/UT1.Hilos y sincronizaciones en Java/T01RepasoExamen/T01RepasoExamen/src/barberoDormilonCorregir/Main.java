package barberoDormilonCorregir;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Barberia barberia = new Barberia(5);
        List<Cliente> clientes = new ArrayList<>();
        //Barbero
        Barbero barbero = new Barbero("Barbero",barberia);
        //Creo los 10 clientes
        for (int i = 0; i < 10; i++) {
            clientes.add(new Cliente(String.valueOf(i), barberia));
        }
        barbero.start();
        //Inicio los 10 clientes
        clientes.forEach(cliente -> {
            cliente.start();
        });
        //Espero a que terminen de cortarse el pelo
        clientes.forEach(cliente -> {
            try {
                cliente.join();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        });
        System.out.println("TODOS LOS CLIENTES SE HAN CORTADO EL PELO.");
    }
}
