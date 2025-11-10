package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.SistemaEnvios;

public class CancelarEnvioController {

    @FXML private TextField txtIdEnvio;
    @FXML private TextArea txtMotivo;

    // 🔹 Acceso a la fachada logística
    private final LogisticaFacade fachada = new LogisticaFacade();

    // ==========================================================
    // ❌ Cancelar envío
    // ==========================================================
    @FXML
    private void cancelarEnvio() {
        String id = txtIdEnvio.getText().trim();
        String motivo = txtMotivo.getText().trim();

        if (id.isEmpty() || motivo.isEmpty()) {
            mostrarAlerta("Campos incompletos", "Por favor ingresa el ID y el motivo de cancelación.");
            return;
        }

        var listaEnvios = SistemaEnvios.getInstancia().getBaseDatos().getListaEnvios();
        Envio envioEncontrado = null;

        for (Envio envio : listaEnvios) {
            if (envio.getIdEnvio().equals(id)) {
                envioEncontrado = envio;
                break;
            }
        }

        if (envioEncontrado == null) {
            mostrarAlerta("No encontrado", "No existe un envío con el ID: " + id);
            return;
        }

        // 🔥 Eliminar completamente el envío del sistema
        listaEnvios.remove(envioEncontrado);

        mostrarAlerta("Envío eliminado", "El envío con ID " + id + " fue cancelado y eliminado correctamente.\nMotivo: " + motivo);
        limpiarCampos();
    }


    // ==========================================================
    // 🔙 Volver al panel del cliente
    // ==========================================================
    @FXML
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/ClienteView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) txtIdEnvio.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Cliente");
            stage.show();

        } catch (Exception e) {
            mostrarAlerta("Error al volver", "No se pudo cargar la vista del cliente: " + e.getMessage());
        }
    }

    // ==========================================================
    // ⚙️ Utilidades
    // ==========================================================
    private void limpiarCampos() {
        txtIdEnvio.clear();
        txtMotivo.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
