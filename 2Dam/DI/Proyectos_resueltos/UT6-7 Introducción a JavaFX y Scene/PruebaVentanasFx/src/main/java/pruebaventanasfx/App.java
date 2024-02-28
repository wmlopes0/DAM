package pruebaventanasfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.Modality;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        /*scene = new Scene(loadFXML("ventana"), 640, 480);
        stage.setScene(scene);
        stage.show();*/
        cargarVentana();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public void cargarVentana() throws IOException {
        // Cargo la vista
        FXMLLoader loader = new FXMLLoader(App.class.getResource("ventana.fxml")); 
        
        Parent root = loader.load(); 
        
        Scene scene = new Scene(root); 
        Stage stage = new Stage(); 
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        
    }

}
