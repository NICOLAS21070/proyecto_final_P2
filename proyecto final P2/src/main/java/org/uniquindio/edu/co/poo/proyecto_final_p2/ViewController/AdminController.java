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

    private final LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    private void initialize() {
        System.out.println("✅ Panel del Administrador cargado correctamente.");
    }

    @FXML
    private void gestionarUsuariosAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/GestionarUsuariosAdmin.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnUsuarios.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Usuarios - Panel Administrador");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/LoginView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Ingreso al Sistema de Logística");
            stage.show();

            System.out.println("👋 Sesión cerrada. Volviendo al login...");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error al intentar cerrar sesión y cargar el LoginView.fxml");
        }
    }

    // (Opcionales) Métodos placeholders si los usas en FXML
    @FXML private void gestionarUsuarios() { System.out.println("👉 Acceso a gestión de usuarios."); }
    @FXML private void gestionarRepartidores() { System.out.println("👉 Acceso a gestión de repartidores."); }
    @FXML private void verEnvios() { System.out.println("👉 Visualización de envíos registrados."); }
    @FXML private void verReportes() { System.out.println("👉 Generación de reportes y estadísticas."); }

}
