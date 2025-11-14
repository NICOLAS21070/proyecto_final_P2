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

    /**
     * Carga una vista desde la carpeta /view/ y aplica estilos globales.
     */
    private static void cargarVista(String nombreFXML, String tituloVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    App.class.getResource("/org/uniquindio/edu/co/poo/proyecto_final_p2/view/" + nombreFXML)
            );

            Parent root = loader.load();
            Scene scene = new Scene(root);

            // 🔹 Cargar CSS global una sola vez
            scene.getStylesheets().add(
                    App.class.getResource("/org/uniquindio/edu/co/poo/proyecto_final_p2/css/estilos.css")
                            .toExternalForm()
            );

            primaryStage.setTitle(tituloVentana);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            System.out.println("❌ Error cargando vista: " + nombreFXML);
            e.printStackTrace();
        }
    }

    /**
     * Muestra la ventana de Login.
     */
    public static void mostrarLogin() {
        cargarVista("LoginView.fxml", "Sistema de Logística - Login");
    }

    /**
     * Cambia a otra ventana dentro del sistema.
     *
     * @param fxml archivo FXML dentro de /view/, por ejemplo: "AdminView.fxml"
     * @param tituloVentana título que se mostrará en la ventana
     */
    public static void cambiarVentana(String fxml, String tituloVentana) {
        cargarVista(fxml, tituloVentana);
    }

    public static void main(String[] args) {
        launch();
    }
}
