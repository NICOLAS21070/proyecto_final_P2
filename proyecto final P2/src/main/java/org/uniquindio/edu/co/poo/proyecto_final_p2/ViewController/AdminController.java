package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;

public class AdminController {

    @FXML
    private Button btnCerrarSesion, btnUsuarios, btnRepartidores, btnEnvios, btnReportes;

    private LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    private void initialize() {
        System.out.println("✅ Panel del Administrador cargado correctamente.");
    }

    @FXML
    private void gestionarUsuarios() {
        System.out.println("👉 Acceso a gestión de usuarios.");
        // Aquí luego puedes abrir un FXML de gestión de usuarios.
    }

    @FXML
    private void gestionarRepartidores() {
        System.out.println("👉 Acceso a gestión de repartidores.");
    }

    @FXML
    private void verEnvios() {
        System.out.println("👉 Visualización de envíos registrados.");
    }

    @FXML
    private void verReportes() {
        System.out.println("👉 Generación de reportes y estadísticas.");
    }

    @FXML
    private void cerrarSesion() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/uniquindio/logistica/view/LoginView.fxml"));
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Ingreso al Sistema de Logística");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
