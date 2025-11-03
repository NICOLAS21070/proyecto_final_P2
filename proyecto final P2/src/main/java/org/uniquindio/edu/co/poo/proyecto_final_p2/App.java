package org.uniquindio.edu.co.poo.proyecto_final_p2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        mostrarLogin();
    }

    public static void mostrarLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/org/uniquindio/edu/co/poo/proyecto_final_p2/view/LoginView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Sistema de Logística - Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cambiarVentana(String fxml, String tituloVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/org/uniquindio/edu/co/poo/proyecto_final_p2/" + fxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle(tituloVentana);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
