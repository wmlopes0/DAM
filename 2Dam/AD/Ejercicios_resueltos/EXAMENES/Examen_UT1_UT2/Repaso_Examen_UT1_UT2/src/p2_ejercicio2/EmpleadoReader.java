package p2_ejercicio2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLOutput;

public class EmpleadoReader extends DefaultHandler {
    private boolean id;
    private boolean nombre;
    private boolean apellido;
    private boolean sueldo;

    private Empleado empleado;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("INICIANDO LECTURA DEL DOCUMENTO....");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("LECTURA DEL DOCUMENTO REALIZADA CON ÉXITO");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName) {
            case "id":
                id = true;
                break;
            case "nombre":
                nombre = true;
                break;
            case "apellido":
                apellido = true;
                break;
            case "sueldo":
                sueldo = true;
                break;
            case "empleado":
                empleado = new Empleado();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equalsIgnoreCase("empleado")) {
            Ejercicio2.listaEmpleados.add(empleado);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String car = new String(ch, start, length);
        car.replaceAll("[\t\n]", "");
//        -------------------------------------------------
        if (id == true) {
            empleado.setId(Integer.parseInt(car));
            id = false;
        }
        if (nombre == true) {
            empleado.setNombre(car);
            nombre = false;
        }
        if (apellido == true) {
            empleado.setApellido(car);
            apellido = false;
        }
        if (sueldo == true) {
            empleado.setSueldo(Integer.parseInt(car));
            sueldo = false;
        }

    }
}
