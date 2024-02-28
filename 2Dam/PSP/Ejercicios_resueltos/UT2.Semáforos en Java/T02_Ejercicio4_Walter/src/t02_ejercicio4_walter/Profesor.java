package t02_ejercicio4_walter;

public class Profesor extends Thread {

    //Atributos
    private String nombre;
    private SalaProfesores salaProfesores;

    //Constructor
    public Profesor(String nombre, SalaProfesores salaProfesores) {
        this.nombre = nombre;
        this.salaProfesores = salaProfesores;
    }

    //Getter nombre
    public String getNombre() {
        return nombre;
    }

    //Override Run
    @Override
    public void run() {
        //Cada profesor entra en la sala de profesores 3 veces
        for (int i = 0; i < 3; i++) {
            try {
                salaProfesores.entrar(this);//Entro en la Sala de Profesores
                Thread.sleep(20);//Espero 20ms antes de volver a entrar
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
            }
        }
    }
}
