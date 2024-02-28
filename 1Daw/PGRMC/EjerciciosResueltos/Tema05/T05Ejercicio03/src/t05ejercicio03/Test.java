/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t05ejercicio03;

/**
 *
 * @author Walter
 */
public class Test {

    public static void main(String[] args) {
        //Creamos 2 objetos de la clase Rueda
        Rueda rueda1 = new Rueda();
        Rueda rueda2 = new Rueda();

        //Establecemos material y pulgadas de las dos ruedas creadas 
        rueda1.setMaterial("Caucho");
        rueda1.setPulgadas(16);

        rueda2.setMaterial("Acero");
        rueda2.setPulgadas(17);

        //Mostramos los datos de las dos ruedas
        System.out.println("==============DATOS DE LAS RUEDAS==============");
        System.out.println("--------------------Rueda 1--------------------");
        System.out.println("Material: " + rueda1.getMaterial());
        System.out.println("Pulgadas: " + rueda1.getPulgadas());
        System.out.println("--------------------Rueda 2--------------------");
        System.out.println("Material: " + rueda2.getMaterial());
        System.out.println("Pulgadas: " + rueda2.getPulgadas());
        System.out.println("===============================================");

        //Creamos 3 objetos de la clase Coche
        Coche coche1 = new Coche();
        Coche coche2 = new Coche();
        Coche coche3 = new Coche();

        //Establecemos marca y modelo de los coches creados 
        coche1.setMarca("Peugeot");
        coche1.setModelo("308");

        coche2.setMarca("Seat");
        coche2.setModelo("Ibiza");

        coche3.setMarca("Mercedes");
        coche3.setModelo("AMG");

        //Establecemos las ruedas
        coche1.setRuedas(rueda1);
        coche2.setRuedas(rueda1);
        coche3.setRuedas(rueda2);
        
        //Mostramos los datos de los coches
        System.out.println("==============DATOS DE LOS COCHES==============");
        System.out.println("--------------------Coche 1--------------------");
        System.out.println("Marca: "+coche1.getMarca());
        System.out.println("Modelo: " + coche1.getModelo());
        System.out.println("Material de las ruedas: " + coche1.getRuedas().getMaterial());
        System.out.println("Pulgadas de las ruedas: " + coche1.getRuedas().getPulgadas());
        System.out.println("--------------------Coche 2--------------------");
        System.out.println("Marca: "+coche2.getMarca());
        System.out.println("Modelo: " + coche2.getModelo());
        System.out.println("Material de las ruedas: " + coche2.getRuedas().getMaterial());
        System.out.println("Pulgadas de las ruedas: " + coche2.getRuedas().getPulgadas());
        System.out.println("--------------------Coche 3--------------------");
        System.out.println("Marca: "+coche3.getMarca());
        System.out.println("Modelo: " + coche3.getModelo());
        System.out.println("Material de las ruedas: " + coche3.getRuedas().getMaterial());
        System.out.println("Pulgadas de las ruedas: " + coche3.getRuedas().getPulgadas());
        System.out.println("===============================================");
    }

}
