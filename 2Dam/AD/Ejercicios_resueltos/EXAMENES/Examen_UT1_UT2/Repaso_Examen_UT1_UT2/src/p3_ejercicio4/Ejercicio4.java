package p3_ejercicio4;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//ENUNCIADO=======================================
//A partir del XML blog.xml crea tu modelo de clases en Java para su deserialización.
//================================================
public class Ejercicio4 {
    //    Variables globales
    public static File blogXML = new File("src/p3_ejercicio4/blog.xml");

    public static void main(String[] args) throws FileNotFoundException {
//        Leemos XML y mostramos por pantalla
        leerXmlXStream();
    }

    public static void leerXmlXStream() throws FileNotFoundException {
//        Creamos un nuevo XStream y le especificamos el DomDriver
        XStream xStream = new XStream(new DomDriver("UTF-8"));
//    Disminuimos la seguridad
        xStream.addPermission(AnyTypePermission.ANY);
//        Especificamos los alias
        xStream.alias("blog", Blog.class);
        xStream.alias("entrada", Entrada.class);
        xStream.addImplicitCollection(Blog.class,"entradas");
//        Creamos objeto y lo recuperamos haciendo parse
        Blog blog = (Blog) xStream.fromXML(new FileInputStream(blogXML));
//        Mostramos por pantalla
        blog.mostrar();
    }
}
