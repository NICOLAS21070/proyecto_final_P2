package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ModificarEnvioController {

    @FXML private TextField txtIdEnvio;
    @FXML private TextField txtRemitente;
    @FXML private TextField txtDestino;
    @FXML private TextField txtDireccionDestino;
    @FXML private TextField txtPeso;
    @FXML private ChoiceBox<String> cbTipo;
    @FXML private Label lblMensaje;

    // 🧩 Simulación de una “base de datos” local
    private static final Map<String, Map<String, String>> envios = new HashMap<>();

    @FXML
    public void initialize() {
        cbTipo.setValue("Documento");

        // Datos simulados de ejemplo (para que puedas probar)
        Map<String, String> envioDemo = new HashMap<>();
        envioDemo.put("remitente", "Carlos Pérez");
        envioDemo.put("destino", "Bogotá");
        envioDemo.put("direccion", "Calle 10 #5-22");
        envioDemo.put("peso", "2.5");
        envioDemo.put("tipo", "Paquete");
        envios.put("E001", envioDemo);
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

        Map<String, String> envio = envios.get(id);
        if (envio == null) {
            mostrarAlerta("No encontrado", "No se encontró ningún envío con el ID: " + id);
            limpiarCampos();
        } else {
            txtRemitente.setText(envio.get("remitente"));
            txtDestino.setText(envio.get("destino"));
            txtDireccionDestino.setText(envio.get("direccion"));
            txtPeso.setText(envio.get("peso"));
            cbTipo.setValue(envio.get("tipo"));
            lblMensaje.setText("✅ Envío encontrado y cargado.");
        }
    }

    // ==========================================================
    // 💾 Guardar cambios
    // ==========================================================
    @FXML
    private void guardarCambios() {
        String id = txtIdEnvio.getText().trim();
        if (id.isEmpty()) {
            mostrarAlerta("Error", "Debe buscar o ingresar un ID primero.");
            return;
        }

        Map<String, String> envio = envios.get(id);
        if (envio == null) {
            mostrarAlerta("Error", "Debe buscar un envío existente antes de modificarlo.");
            return;
        }

        envio.put("remitente", txtRemitente.getText().trim());
        envio.put("destino", txtDestino.getText().trim());
        envio.put("direccion", txtDireccionDestino.getText().trim());
        envio.put("peso", txtPeso.getText().trim());
        envio.put("tipo", cbTipo.getValue());

        lblMensaje.setText("✅ Cambios guardados exitosamente.");
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
    // ⚠️ Utilidades
    // ==========================================================
    private void limpiarCampos() {
        txtRemitente.clear();
        txtDestino.clear();
        txtDireccionDestino.clear();
        txtPeso.clear();
        cbTipo.setValue("Documento");
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
