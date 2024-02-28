package t07ejercicio15;

/**
 *
 * @author Walter
 */
public class Pelicula {

    private String titulo;
    private float costeLicencia;
    private Socio[] socios;

    public Pelicula() {
        titulo = "";
        costeLicencia = 0;
        socios = new Socio[4];
    }

    public Pelicula(String titulo, float costeLicencia, Socio[] socios) {
        this.titulo = titulo;
        this.costeLicencia = costeLicencia;
        this.socios = socios;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCosteLicencia(float costeLicencia) {
        this.costeLicencia = costeLicencia;
    }

    public void setSocios(Socio[] socios) {
        this.socios = socios;
    }

    public String getTitulo() {
        return titulo;
    }

    public float getCosteLicencia() {
        return costeLicencia;
    }

    public Socio[] getSocios() {
        return socios;
    }

    //METODOS ESPECIALES------------------------------------------
    public float beneficioNeto() {
        float beneficioNeto = 0;
        for (int i = 0; i < socios.length; i++) {
            beneficioNeto += socios[i].getPrecioAbonado();
        }
        return beneficioNeto - costeLicencia;
    }

    public int sociosTontos(float cantidad) {
        int sociosTontos = 0;
        for (int i = 0; i < socios.length; i++) {
            if (socios[i].getPrecioAbonado() > cantidad) {
                sociosTontos++;
            }
        }
        return sociosTontos;
    }

    //METODOS MOSTRAR------------------------------------------
    public void mostrarPelicula() {
        System.out.println(toString());//Muestro titulo y coste de licencia
        for (int i = 0; i < socios.length; i++) { //Muestro socios
            System.out.println("----------SOCIO " + (i + 1) + "----------");
            System.out.println(socios[i].toString());
        }
    }

    public void mostrarSociosPelicula() {
        for (int i = 0; i < socios.length; i++) { //Muestro socios
            System.out.println("----------SOCIO " + (i + 1) + "----------");
            System.out.println(socios[i].toString());
        }
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + "\nCoste Licencia: " + costeLicencia;
    }

}
