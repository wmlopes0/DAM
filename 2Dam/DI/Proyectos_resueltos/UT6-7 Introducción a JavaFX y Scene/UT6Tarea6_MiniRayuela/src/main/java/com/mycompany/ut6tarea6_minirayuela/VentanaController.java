package com.mycompany.ut6tarea6_minirayuela;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Walter
 */
public class VentanaController implements Initializable {

    @FXML
    private Label nombreAlumno1;
    @FXML
    private Button botonFaltaAlumno1;
    @FXML
    private ImageView imagenAlumno1;
    @FXML
    private Label nombreAlumno2;
    @FXML
    private Button botonFaltaAlumno2;
    @FXML
    private ImageView imagenAlumno2;
    @FXML
    private Label nombreAlumno3;
    @FXML
    private Button botonFaltaAlumno3;
    @FXML
    private ImageView imagenAlumno3;
    @FXML
    private Label nombreAlumno4;
    @FXML
    private Button botonFaltaAlumno4;
    @FXML
    private ImageView imagenAlumno4;

    //Booleanos para el botón
    private boolean falta1 = false;
    private boolean falta2 = false;
    private boolean falta3 = false;
    private boolean falta4 = false;

    private List<Alumno> alumnos = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        leerDatosCSV();
    }

    private void leerDatosCSV() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("recursos/alumnos.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String linea;
            int alumno = 0;
            linea = reader.readLine();//Salto la primera linea que es de columnas
            while ((linea = reader.readLine()) != null) {
                String[] datosAlumno = linea.split(",");
                //Añado el alumno al array
                alumnos.add(new Alumno(datosAlumno[0], datosAlumno[1], datosAlumno[2], Integer.parseInt(datosAlumno[3])));
                switch (alumno) {
                    case 0: // Primer alumno
                        nombreAlumno1.setText(datosAlumno[0] + " " + datosAlumno[1]);
                        imagenAlumno1.setImage(new Image(getClass().getResourceAsStream("/recursos/" + datosAlumno[2])));
                        break;
                    case 1: // Segundo alumno
                        nombreAlumno2.setText(datosAlumno[0] + " " + datosAlumno[1]);
                        imagenAlumno2.setImage(new Image(getClass().getResourceAsStream("/recursos/" + datosAlumno[2])));
                        break;
                    case 2: // Tercer alumno
                        nombreAlumno3.setText(datosAlumno[0] + " " + datosAlumno[1]);
                        imagenAlumno3.setImage(new Image(getClass().getResourceAsStream("/recursos/" + datosAlumno[2])));
                        break;
                    case 3: // Cuarto alumno
                        nombreAlumno4.setText(datosAlumno[0] + " " + datosAlumno[1]);
                        imagenAlumno4.setImage(new Image(getClass().getResourceAsStream("/recursos/" + datosAlumno[2])));
                        break;
                    default:
                        //Ignoro si hay más
                        break;
                }
                //Incremento contador
                alumno++;
            }

            //Libero recursos
            is.close();
            reader.close();

        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    @FXML
    private void gestionarFaltaAlumno1(ActionEvent event) {
        if (!falta1) {
            //Pongo falta
            alumnos.get(0).ponerFalta();
            //Actualizo botón
            botonFaltaAlumno1.setText("Quitar falta");
            botonFaltaAlumno1.setStyle("-fx-background-color: #eac4d5;");
            falta1 = true;
        } else {
            //Quito la falta
            alumnos.get(0).quitarFalta();
            //Actualizo botón
            botonFaltaAlumno1.setText("Falta");
            botonFaltaAlumno1.setStyle("-fx-background-color: #d6eadf;");
            falta1 = false;
        }
        //Actualizo csv
        try {
            PrintWriter writer = new PrintWriter("src/main/resources/recursos/alumnos.csv");
            // Escribo los encabezados
            writer.println("Nombre,Apellido,RutaFoto,Faltas");
            //Escribo alumnos
            for (Alumno alumno : alumnos) {
                writer.println(alumno.getNombre() + "," + alumno.getApellido() + "," + alumno.getRutaFoto() + "," + alumno.getFaltas());
            }
            //Libero recursos
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: No se encontró el archivo.");
        }
    }

    @FXML
    private void gestionarFaltaAlumno2(ActionEvent event) {
         if (!falta2) {
            //Pongo falta
            alumnos.get(1).ponerFalta();
            //Actualizo botón
            botonFaltaAlumno2.setText("Quitar falta");
            botonFaltaAlumno2.setStyle("-fx-background-color: #eac4d5;");
            falta2 = true;
        } else {
            //Quito la falta
            alumnos.get(1).quitarFalta();
            //Actualizo botón
            botonFaltaAlumno2.setText("Falta");
            botonFaltaAlumno2.setStyle("-fx-background-color: #d6eadf;");
            falta2 = false;
        }
        //Actualizo csv
        try {
            PrintWriter writer = new PrintWriter("src/main/resources/recursos/alumnos.csv");
            // Escribo los encabezados
            writer.println("Nombre,Apellido,RutaFoto,Faltas");
            //Escribo alumnos
            for (Alumno alumno : alumnos) {
                writer.println(alumno.getNombre() + "," + alumno.getApellido() + "," + alumno.getRutaFoto() + "," + alumno.getFaltas());
            }
            //Libero recursos
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: No se encontró el archivo.");
        }
    }

    @FXML
    private void gestionarFaltaAlumno3(ActionEvent event) {
         if (!falta3) {
            //Pongo falta
            alumnos.get(2).ponerFalta();
            //Actualizo botón
            botonFaltaAlumno3.setText("Quitar falta");
            botonFaltaAlumno3.setStyle("-fx-background-color: #eac4d5;");
            falta3 = true;
        } else {
            //Quito la falta
            alumnos.get(2).quitarFalta();
            //Actualizo botón
            botonFaltaAlumno3.setText("Falta");
            botonFaltaAlumno3.setStyle("-fx-background-color: #d6eadf;");
            falta3 = false;
        }
        //Actualizo csv
        try {
            PrintWriter writer = new PrintWriter("src/main/resources/recursos/alumnos.csv");
            // Escribo los encabezados
            writer.println("Nombre,Apellido,RutaFoto,Faltas");
            //Escribo alumnos
            for (Alumno alumno : alumnos) {
                writer.println(alumno.getNombre() + "," + alumno.getApellido() + "," + alumno.getRutaFoto() + "," + alumno.getFaltas());
            }
            //Libero recursos
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: No se encontró el archivo.");
        }
    }

    @FXML
    private void gestionarFaltaAlumno4(ActionEvent event) {
         if (!falta4) {
            //Pongo falta
            alumnos.get(3).ponerFalta();
            //Actualizo botón
            botonFaltaAlumno4.setText("Quitar falta");
            botonFaltaAlumno4.setStyle("-fx-background-color: #eac4d5;");
            falta4 = true;
        } else {
            //Quito la falta
            alumnos.get(3).quitarFalta();
            //Actualizo botón
            botonFaltaAlumno4.setText("Falta");
            botonFaltaAlumno4.setStyle("-fx-background-color: #d6eadf;");
            falta4 = false;
        }
        //Actualizo csv
        try {
            PrintWriter writer = new PrintWriter("src/main/resources/recursos/alumnos.csv");
            // Escribo los encabezados
            writer.println("Nombre,Apellido,RutaFoto,Faltas");
            //Escribo alumnos
            for (Alumno alumno : alumnos) {
                writer.println(alumno.getNombre() + "," + alumno.getApellido() + "," + alumno.getRutaFoto() + "," + alumno.getFaltas());
            }
            //Libero recursos
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: No se encontró el archivo.");
        }
    }

}
