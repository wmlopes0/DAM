/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfazGrafica;

/**
 *
 * @author wmartinl01
 */
public class Pokemon {

    private String nombre;
    private String tipo;
    private String ataque1;
    private String ataque2;
    private String ataque3;

    //Constructor
    public Pokemon(String nombre, String tipo, String ataque1, String ataque2, String ataque3) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ataque1 = ataque1;
        this.ataque2 = ataque2;
        this.ataque3 = ataque3;
    }

    //Getter y Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAtaque1() {
        return ataque1;
    }

    public void setAtaque1(String ataque1) {
        this.ataque1 = ataque1;
    }

    public String getAtaque2() {
        return ataque2;
    }

    public void setAtaque2(String ataque2) {
        this.ataque2 = ataque2;
    }

    public String getAtaque3() {
        return ataque3;
    }

    public void setAtaque3(String ataque3) {
        this.ataque3 = ataque3;
    }

    //Métodos propios
    //Este método se encarga de devolver los datos del pokemon en formato String[]
    public String[] toArrayString() {
        //Creamos array
        String[] s = new String[5];
        //Añadimos datos
        s[0] = nombre;
        s[1] = tipo;
        s[2] = ataque1;
        s[3] = ataque2;
        s[4] = ataque3;
        //Retornamos
        return s;
    }
}
