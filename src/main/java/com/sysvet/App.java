package com.sysvet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.hibernateSessionFactorySingleton;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;  // Referência ao Stage principal

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;  // Armazena o Stage principal
        scene = new Scene(loadFXML("/view/login"), 600, 500);  // Tamanho inicial para a tela de login
        primaryStage.setScene(scene);
        primaryStage.setTitle("SysVet");
        primaryStage.setMinWidth(600);  // Definir largura mínima para a tela de login
        primaryStage.setMinHeight(500); // Definir altura mínima para a tela de login

        primaryStage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(root);

        // Ajustar o tamanho da janela com base na tela atual
        adjustStageSize(fxml);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // Método para ajustar o tamanho da janela
    private static void adjustStageSize(String fxml) {
        if (fxml.contains("login")) {
            primaryStage.setWidth(620);
            primaryStage.setHeight(540);
            primaryStage.setMinWidth(620);
            primaryStage.setMinHeight(540);
        } else {
            primaryStage.setMinWidth(1100);
            primaryStage.setMinHeight(740);
        } 
        
    }

    public static void main(String[] args) {
        new hibernateSessionFactorySingleton();
        
        launch();        
    }

}