package repasoexamentablemodel;

/**
 *
 * @author wmartinl01
 */
public class Mensaje {

    //Atributos
    private String emisor;
    private String destinatario;
    private String asunto;
    private String mensaje;

    //Constructor
    public Mensaje(String emisor, String destinatario, String asunto, String mensaje) {
        this.emisor = emisor;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    //Getter y Setter
    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
