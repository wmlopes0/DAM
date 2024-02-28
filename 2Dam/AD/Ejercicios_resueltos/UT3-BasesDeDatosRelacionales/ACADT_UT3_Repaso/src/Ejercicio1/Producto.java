package Ejercicio1;

public class Producto {

    //Atributos
    private static int contador = 0;
    private int id;
    private String descripcion;
    private int stockActual;
    private int stockMinimo;
    private int pvp;

    //Constructor
    public Producto(String descripcion, int stockActual, int stockMinimo, int pvp) {
        contador++;
        this.id = contador;
        this.descripcion = descripcion;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.pvp = pvp;
    }

    //Getter y Setter
    public static int getContador() {
        return contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getPvp() {
        return pvp;
    }

    public void setPvp(int pvp) {
        this.pvp = pvp;
    }

    //Métodos propios
    public void mostrar(){}
}
