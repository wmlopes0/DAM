package t07ejercicio11;

/**
 *
 * @author Walter
 */
public class Test {
    public static void main(String[] args) {
        //Creo muebles
        Mueble mesa = new Mueble();
        Mueble escritorio = new Mueble(150.59f, "Escritorio Rivoli roble 155x68x76 cm");
        
        //Modifico muebles
        mesa.setPrecio(129.99f);
        mesa.setDescripcion(" Mesa PINNTORP, tinte marrón claro/tinte blanco, 125x75 cm");
        
        //Muestro muebles
        System.out.println(mesa.toString());
        System.out.println(escritorio.toString());
       
    }
}
