package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;

public class RastrearEnviosViewController {

    @FXML
    private TextField txtIdEnvio, txtEstado, txtUbicacion;

    private final LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    public void initialize() {
        System.out.println("✅ Vista de rastreo de envío cargada correctamente.");
    }

    @FXML
    private void buscarEnvio() {
        String idEnvio = txtIdEnvio.getText();

        if (idEnvio.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingresa un ID de envío válido.");
            return;
        }

        // 🔹 Simulación de consulta (luego puedes conectar con tu modelo real)
        txtEstado.setText("En tránsito");
        txtUbicacion.setText("Centro de distribución - Filandia");

        mostrarAlerta("Resultado", "El estado del envío ha sido actualizado en pantalla.");
    }

    @FXML
    private void volver() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/uniquindio/edu/co/poo/proyecto_final_p2/view/ClienteView.fxml"));
            Stage stage = (Stage) txtIdEnvio.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Cliente");
            stage.show();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo volver al panel del cliente.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
