package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;

public class ModificarEnvioController {

    @FXML private TextField txtIdEnvio;
    @FXML private TextField txtRemitente;
    @FXML private TextField txtDestino;
    @FXML private TextField txtDireccionDestino;
    @FXML private TextField txtPeso;
    @FXML private ChoiceBox<String> cbTipo;
    @FXML private Label lblMensaje;

    private final LogisticaFacade logistica = new LogisticaFacade();
    private Envio envioActual;

    @FXML
    public void initialize() {
        // Inicializa los tipos en el ChoiceBox
        cbTipo.getItems().addAll("Paquete", "Documento", "Caja", "Sobre");
        cbTipo.setValue("Paquete");
    }

    // ==========================================================
    // 🔍 Buscar envío
    // ==========================================================
    @FXML
    private void buscarEnvio() {
        String id = txtIdEnvio.getText().trim();

        if (id.isEmpty()) {
            mostrarAlerta("Campo vacío", "Por favor ingrese un ID de envío.");
            return;
        }

        envioActual = logistica.obtenerEnvioPorId(id);

        if (envioActual == null) {
            mostrarAlerta("No encontrado", "No se encontró ningún envío con el ID: " + id);
            limpiarCampos();
            return;
        }

        txtRemitente.setText(envioActual.getUsuario() != null ? envioActual.getUsuario().getNombreUsuario() : "Desconocido");
        txtDestino.setText(envioActual.getDestino());
        txtDireccionDestino.setText(envioActual.getDescripcion());
        txtPeso.setText(envioActual.getPaquete() != null ? String.valueOf(envioActual.getPaquete().getPeso()) : "");
        cbTipo.setValue(envioActual.getPaquete() != null ? envioActual.getPaquete().getTipo() : "Paquete");

        lblMensaje.setText("✅ Envío encontrado y cargado.");
    }

    // ==========================================================
    // 💾 Guardar cambios
    // ==========================================================
    @FXML
    private void guardarCambios() {
        if (envioActual == null) {
            mostrarAlerta("Error", "Debe buscar un envío antes de modificarlo.");
            return;
        }

        try {
            envioActual.setDestino(txtDestino.getText().trim());
            envioActual.setDescripcion(txtDireccionDestino.getText().trim());
            envioActual.getPaquete().setPeso(Double.parseDouble(txtPeso.getText().trim()));
            envioActual.getPaquete().setTipo(cbTipo.getValue());

            lblMensaje.setText("✅ Cambios guardados exitosamente.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron guardar los cambios: " + e.getMessage());
        }
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
            e.printStackTrace();
        }
    }

    // ==========================================================
    // ⚙️ Utilidades
    // ==========================================================
    private void limpiarCampos() {
        txtRemitente.clear();
        txtDestino.clear();
        txtDireccionDestino.clear();
        txtPeso.clear();
        cbTipo.setValue("Paquete");
        lblMensaje.setText("");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
