package acadt_ut3_p2;

import java.sql.Date;
import static acadt_ut3_p2.Utileria.*;

public class Empleado {
    //Atributos
    private int emp_no;
    private String apellido;
    private String oficio;
    private int dir;
    private Date fecha_alt;
    private float salario;
    private float comision;
    private int dep_no;

//    Constructores

    public Empleado() {
    }

    public Empleado(int emp_no, String apellido, String oficio, int dir, Date fecha_alt, float salario, float comision, int dep_no) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fecha_alt = fecha_alt;
        this.salario = salario;
        this.comision = comision;
        this.dep_no = dep_no;
    }

//    Getter y Setter

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public Date getFecha_alt() {
        return fecha_alt;
    }

    public void setFecha_alt(Date fecha_alt) {
        this.fecha_alt = fecha_alt;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    public int getDep_no() {
        return dep_no;
    }

    public void setDep_no(int dep_no) {
        this.dep_no = dep_no;
    }

//    MÉTODOS PROPIOS
    public void rellenarInfo(){
        System.out.println("===== EMPLEADO =====");
        emp_no = pedirEntero("Introduzca el 'emp_no':");
        apellido = pedirString("Introduzca el 'apellido':");
        oficio = pedirString("Introduzca el 'oficio':");
        dir = pedirEntero("Introduzca el 'dir':");
        fecha_alt = pedirDate();
        salario = pedirFloat("Introduzca el 'salario':");
        comision = pedirFloat("Introduzca el 'comision':");
        dep_no = pedirEntero("Introduzca el 'dep_no':");
        System.out.println("--------------------");
    }
    public void mostrar(){
        System.out.println("===== EMPLEADO =====");
        System.out.println("emp_no = " + emp_no);
        System.out.println("apellido = " + apellido);
        System.out.println("oficio = " + oficio);
        System.out.println("dir = " + dir);
        System.out.println("fecha_alt = " + fecha_alt);
        System.out.println("salario = " + salario);
        System.out.println("comision = " + comision);
        System.out.println("dep_no = " + dep_no);
        System.out.println("--------------------");
    }
}
