package ejercicio18;

public class Banio {
    //Atributos
    private int repisa = 0; // 0 significa que la repisa está vacía.
    private volatile boolean ocupado = false;

    //Acción de entrar en el baño
    public synchronized void entrar(Fumador fumador) throws InterruptedException {
        //Si está ocupado espera
        while (ocupado) {
            wait();
        }
        //Muestra los ingredientes del fumador
        System.out.println("El fumador " + fumador.getIngrediente() + " tiene " + nombreIngrediente(fumador) + ", necesita " + ingredientesNecesarios(fumador));
        //Obtiene los ingredientes si es lo que necesita, sino espera
        obtenerIngredientes(fumador.getIngrediente());
        //Digo que el baño está ocupado
        ocupado = true;
        //Fumetea
        fumador.fumar();
        //Notifico
        notifyAll();
    }

    //Acción de salir del baño
    public synchronized void salir(Fumador fumador) {
        System.out.println("                El fumador " + fumador.getIngrediente() + " ya terminó de fumar. Abandona el baño");
        ocupado = false;//Libero el baño
        notifyAll();//Notifico
    }

    //Acción de poner ingredientes, corresponde al estanquero
    public synchronized void ponerIngredientes(int ingredienteFaltante) {
        //Si la repisa esta llena o el baño esta ocupado espera
        while (repisa != 0 || ocupado) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Llena la repisa y muestra
        repisa = ingredienteFaltante;
        System.out.println("        =>El estanquero repone ingredientes: en la repisa hay " + ingredientesEnRepisa());
        notifyAll();//Notifico
    }

    //Acción obtener ingredientes, corresponde al fumador
    public synchronized void obtenerIngredientes(int ingredienteFumador) {
        //Si la repisa no tiene los ingredientes que le faltan espera
        while (repisa != ingredienteFumador) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Muestro info
        System.out.println("        El fumador " + ingredienteFumador + " ya tiene todos los ingredientes. Empieza a fumar");
        repisa = 0;//Vacio repisa
        notifyAll();//Notifico
    }

    //Método que retorna un string con los ingredientes de la repisa según el número de la variable
    private String ingredientesEnRepisa() {
        switch (repisa) {
            case 1:
                return "CERILLAS Y PAPEL";
            case 2:
                return "TABACO Y PAPEL";
            case 3:
                return "TABACO Y CERILLAS";
            default:
                return "VACIO";
        }
    }

    //    Método que retorna el string correspondiente del ingrediente que posee el fumador
    private String nombreIngrediente(Fumador fumador) {
        switch (fumador.getIngrediente()) {
            case 1:
                return "tabaco";
            case 2:
                return "cerillas";
            case 3:
                return "papel";
            default:
                return "desconocido";
        }
    }

    //Método que retorna el string de los ingredientes que le faltan al fumador
    private String ingredientesNecesarios(Fumador fumador) {
        switch (fumador.getIngrediente()) {
            case 1:
                return "CERILLAS Y PAPEL";
            case 2:
                return "TABACO Y PAPEL";
            case 3:
                return "TABACO Y CERILLAS";
            default:
                return "desconocido";
        }
    }
}
