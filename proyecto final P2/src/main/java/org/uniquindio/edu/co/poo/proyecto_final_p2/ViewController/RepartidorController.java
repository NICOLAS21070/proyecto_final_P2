package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RepartidorController {

    @FXML
    private Button btnGestionarRepartidores;

    @FXML
    private Button btnCambiarEstado;

    @FXML
    private Button btnConsultarEnvios;

    @FXML
    private Button btnCerrarSesion;  // 🔹 NUEVO BOTÓN

    // --------------------------------------------------------------
    //              MÉTODOS DE NAVEGACIÓN
    // --------------------------------------------------------------

    @FXML
    private void gestionarRepartidores() {
        cargarVista("/org/uniquindio/edu/co/poo/proyecto_final_p2/View/GestionRepartidores.fxml",
                "Gestión de Repartidores");
    }

    @FXML
    private void cambiarEstadoRepartidor() {
        cargarVista("/org/uniquindio/edu/co/poo/proyecto_final_p2/View/CambiarEstadoRepartidorView.fxml",
                "Cambiar Estado del Repartidor");
    }

    @FXML
    private void irAConsultarEnvios() {
        cargarVista("/org/uniquindio/edu/co/poo/proyecto_final_p2/View/ConsultarEnvios.fxml",
                "Consultar Envíos");
    }

    // --------------------------------------------------------------
    //                🔴 MÉTODO CERRAR SESIÓN
    // --------------------------------------------------------------
    @FXML
    private void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/View/LoginView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Ingreso al Sistema de Logística");
            stage.show();

            System.out.println("👋 Sesión cerrada. Volviendo al login...");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error al intentar cerrar sesión desde RepartidorController.");
        }
    }

    // --------------------------------------------------------------
    //      MÉTODO GENERAL PARA CARGAR VISTAS
    // --------------------------------------------------------------
    private void cargarVista(String ruta, String titulo){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ruta));
            Stage stage = (Stage) btnGestionarRepartidores.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
