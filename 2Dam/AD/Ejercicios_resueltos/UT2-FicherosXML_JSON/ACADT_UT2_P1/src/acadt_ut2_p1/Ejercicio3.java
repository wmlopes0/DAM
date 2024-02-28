package acadt_ut2_p1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStore.Entry.Attribute;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Ejercicio3 {
	public static File fichero = new File("libros.obj");
	public static List<Libro> lLibros = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream(fichero));
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));

		// Creo libro
		Libro libro1 = new Libro("asdf4515", "Acceso a datos", "Walter", "Gareta");
		Libro libro2 = new Libro("asdf451asd5", "Acceso a datosss", "Walter Martin", "Gareta2");

		escribirFichero(dos, libro1);
		escribirFichero(dos, libro2);

		// Cierro flujo escritura
		dos.close();
		// Leemos y escribimos en xml
		leerFichero(ois);
		// Cierro flujo de lectura
		ois.close();
		// Escribir en xml
		escribirXML();
	}

	public static void escribirFichero(ObjectOutputStream dos, Libro libro) throws IOException {
		dos.writeObject(libro);
	}

	public static void leerFichero(ObjectInputStream ois) {
		Libro c;
		try {
			while (true) {
				c = (Libro) ois.readObject();
				lLibros.add(c);
			}
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
	}

	public static void escribirXML()
			throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		// Creamos un DocumentBuilder haciendo uso de la factoria
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();

		DOMImplementation implementacion = builder.getDOMImplementation();
		Document libros = implementacion.createDocument(null, "libros", null);

		// Asignamos la version de XML
		libros.setXmlVersion("1.0");

		for (Libro c : lLibros) {

			// Creamos un nodo libro
			Element libro = libros.createElement("libro");
			// Añadimos contacto a contactos
			libros.getDocumentElement().appendChild(libro);
			// Generamos el resto del xml
			libro.setAttribute("ISBN", c.getIsbn());

			Element titulo = libros.createElement("titulo");
			Element autor = libros.createElement("autor");
			Element editorial = libros.createElement("editorial");
			libro.appendChild(titulo);
			libro.appendChild(autor);
			libro.appendChild(editorial);

			Text texto = libros.createTextNode(c.getTitulo());
			titulo.appendChild(texto);
			texto = libros.createTextNode(c.getAutor());
			autor.appendChild(texto);
			texto = libros.createTextNode(c.getEditorial());
			editorial.appendChild(texto);
		}

		Source origen = new DOMSource(libros);
		Result resultado = new StreamResult(new File("Libros.xml"));
		Transformer transformador = TransformerFactory.newInstance().newTransformer();
		transformador.transform(origen, resultado);
	}
}
