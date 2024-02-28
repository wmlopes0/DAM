package ejercicio2_3;

public class DisneyWrapper {
    /*ACLARACIÓN
     * Esta clase la he tenido que crear para procesar el JSON, porque según como lo había creado necesitaba una clase wrapper, ya que el Gson se tiene que encontrar
     * la estructura de clases tal cual se usa en el fichero json para procesarlo.
     * */
    private Disney disney;

    //    Constructor
    public DisneyWrapper() {
    }

    //    Getter y setter
    public Disney getDisney() {
        return disney;
    }

    public void setDisney(Disney disney) {
        this.disney = disney;
    }
}
