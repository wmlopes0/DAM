package pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wmartinl01
 */
public class Pokemon {

    private String nombre;
    private int vida;
    private String tipo;
    private String estado;
    private String representacion;
    private List<Ataque> lAtaques;

    //CONSTRUCTORES
    public Pokemon(String nombre, String tipo, String representacion) {
        this.nombre = nombre;
        this.vida = Constantes.VIDA_MAX;
        this.tipo = tipo;
        this.estado = "NORMAL";
        this.representacion = representacion.replace("\\n", "\n");
        this.lAtaques = new ArrayList<>();
        rellenarAtaques();
    }

    //SETTER Y GETTER
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        //Controlo si intentamos introducir un valor inválido en la vida
        if (vida < Constantes.VIDA_MIN) {
            this.vida = Constantes.VIDA_MIN;
        } else {
            if (vida > Constantes.VIDA_MAX) {
                this.vida = Constantes.VIDA_MAX;
            } else {
                this.vida = vida;
            }
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRepresentacion() {
        return representacion;
    }

    public void setRepresentacion(String representacion) {
        this.representacion = representacion;
    }

    public List<Ataque> getLataques() {
        return lAtaques;
    }

    public void setLataques(List<Ataque> lataques) {
        this.lAtaques = lataques;
    }

    //MÉTODOS PROPIOS
    private void rellenarAtaques() {
        boolean encontrado;
        int contador;
        for (int i = 0; i < Constantes.MAX_ATAQUES; i++) {
            encontrado = false;
            contador = 0;
            while (contador < Juego.listaAtaques.size() && !encontrado) {
                if (Juego.listaAtaques.get(contador).getTipo().equalsIgnoreCase(tipo)) {
                    lAtaques.add(Juego.listaAtaques.get(contador));
                    Juego.listaAtaques.remove(contador);
                    encontrado = true;
                } else {
                    contador++;
                }
            }

            // Si después de recorrer todos los ataques disponibles no encontramos uno adecuado
            if (!encontrado) {
                System.out.println("ERROR: No hay suficientes ataques del tipo " + tipo + " para el Pokémon " + nombre);
                return;  // Salimos del método ya que no tiene sentido seguir buscando más ataques si ya sabemos que no hay suficientes
            }
        }
    }

    public void mostrarRepresentacionPokemon() {
        System.out.println("    " + this.nombre.toUpperCase());
        System.out.println("");
        System.out.println(this.representacion);
        System.out.println("-------------------");
    }

    public void mostrarInfoPokemon() {
        //Solo muestro la informacion del pokemon si el pokemon sigue con vida
        if (this.vida > 0) {
            System.out.println("------------------");
            System.out.println(this.representacion);
            System.out.println("\nNombre: " + this.nombre);
            System.out.println("Tipo: " + this.tipo);
            System.out.println("Vida: " + this.vida);
            System.out.println("Estado: "+this.estado);
            for (int i = 0; i < lAtaques.size(); i++) {
                System.out.println("#ATAQUE " + (i + 1)+" "+lAtaques.get(i).getNombre());
            }
        }
    }
}
