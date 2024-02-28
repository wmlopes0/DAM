package acadt_ut3_p2;

import static acadt_ut3_p2.Utileria.*;

public class Departamento {

    //    Atributos
    private int dep_no;
    private String dnombre;
    private String loc;

//    Constructores

    public Departamento() {
    }

    public Departamento(int dep_no, String dnombre, String loc) {
        this.dep_no = dep_no;
        this.dnombre = dnombre;
        this.loc = loc;
    }

//    Getter y Setter
    public int getDep_no() {
        return dep_no;
    }

    public void setDep_no(int dep_no) {
        this.dep_no = dep_no;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

//    MÉTODOS PROPIOS
    public void rellenarInfo(){
        System.out.println("===== DEPARTAMENTO =====");
        dep_no = pedirEntero("Introduzca el 'dep_no':");
        dnombre = pedirString("Introduzca el 'dnombre':");
        loc = pedirString("Introduzca el 'loc':");
        System.out.println("------------------------");
    }
    public void mostrar(){
        System.out.println("===== DEPARTAMENTO =====");
        System.out.println("dep_no = " + dep_no);
        System.out.println("dnombre = " + dnombre);
        System.out.println("loc = " + loc);
        System.out.println("------------------------");
    }
}
