package org.uniquindio.edu.co.poo.proyecto_final_p2.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CancelarEnvioController {

    @FXML
    private TextField txtIdEnvio;

    @FXML
    private TextArea txtMotivo;

    /**
     * Acción del botón "Cancelar Envío".
     */
    @FXML
    private void cancelarEnvio() {
        String idEnvio = txtIdEnvio.getText();
        String motivo = txtMotivo.getText();

        if (idEnvio.isEmpty() || motivo.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese el ID del envío y el motivo de cancelación.", Alert.AlertType.ERROR);
            return;
        }

        // Aquí podrías conectar con tu modelo o base de datos
        // para marcar el envío como cancelado.
        System.out.println("✅ Envío con ID " + idEnvio + " cancelado. Motivo: " + motivo);

        mostrarAlerta("Éxito", "El envío con ID " + idEnvio + " ha sido cancelado correctamente.", Alert.AlertType.INFORMATION);

        limpiarCampos();
    }

    /**
     * Acción del botón "Volver".
     */
    @FXML
    private void volver() {
        System.out.println("🔙 Volviendo al panel anterior...");
        // Aquí puedes agregar la lógica para regresar a la vista del cliente.
    }

    /**
     * Muestra una alerta informativa o de error.
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Limpia los campos del formulario.
     */
    private void limpiarCampos() {
        txtIdEnvio.clear();
        txtMotivo.clear();
    }
}
