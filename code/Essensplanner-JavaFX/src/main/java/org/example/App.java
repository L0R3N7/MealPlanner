package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.example.apiClient.RestApiClient;
import org.example.apiClient.dto.PersonDTO;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static BorderPane bpRoot = new BorderPane();
    public static AppData appData = new AppData();
    public static MenubarController menubarController;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menubar.fxml"));
        bpRoot.setTop(fxmlLoader.load());
        menubarController = (MenubarController) fxmlLoader.getController();
        menubarController.showHidde(false);

        scene = new Scene(bpRoot, 640*2, 480*2);
        stage.setScene(scene);
        stage.show();

        setRoot("LogIn");
    }


    public static void setRoot(String fxml) throws IOException {
        Parent p = loadFXML(fxml);
        setRoot(p);
    }
    public static void setRoot(Parent p) {
        bpRoot.setCenter(p);
    }


    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch();
    }

}