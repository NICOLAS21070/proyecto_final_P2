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
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Incidencia;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;

import java.util.List;

public class RastrearEnviosViewController {

    @FXML private TextField txtIdEnvio;
    @FXML private TextField txtEstado;
    @FXML private TextField txtUbicacion;
    @FXML private TextArea txtIncidencias;   // ← AGREGA ESTO EN TU FXML

    private final LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    public void initialize() {
        System.out.println("Vista de rastreo cargada.");
    }

    @FXML
    private void buscarEnvio() {
        String idEnvio = txtIdEnvio.getText().trim();

        if (idEnvio.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar un ID.");
            return;
        }

        Envio envio = fachada.obtenerEnvioPorId(idEnvio);

        if (envio == null) {
            mostrarAlerta("Error", "No existe un envío con ese ID.");
            return;
        }

        // 🔹 Muestra estado real
        txtEstado.setText(envio.getEstado());

        // 🔹 Ubicación (si no tienes un campo real, dejamos un texto)
        txtUbicacion.setText("En centro de distribución");

        // 🔹 MOSTRAR INCIDENCIAS DEL SISTEMA
        mostrarIncidenciasDeEnvio(idEnvio);
    }

    private void mostrarIncidenciasDeEnvio(String idEnvio) {
        List<Incidencia> lista = fachada.obtenerIncidencias(); // ← NO TOQUÉ TU FACHADA
        StringBuilder sb = new StringBuilder();

        for (Incidencia i : lista) {
            if (i.getEnvio().getIdEnvio().equalsIgnoreCase(idEnvio)) {
                sb.append("• Tipo: ").append(i.getTipo()).append("\n");
                sb.append("  Descripción: ").append(i.getDescripcion()).append("\n\n");
            }
        }

        if (sb.isEmpty()) {
            txtIncidencias.setText("No hay incidencias reportadas.");
        } else {
            txtIncidencias.setText(sb.toString());
        }
    }
    @FXML
    private void verIncidencias() {

        String id = txtIdEnvio.getText().trim();

        if (id.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar un ID para ver incidencias.");
            return;
        }

        Envio envio = fachada.obtenerEnvioPorId(id);

        if (envio == null) {
            mostrarAlerta("Error", "No existe un envío con ese ID.");
            return;
        }

        List<Incidencia> lista = fachada.obtenerIncidencias();

        StringBuilder sb = new StringBuilder();

        for (Incidencia i : lista) {
            if (i.getEnvio().getIdEnvio().equalsIgnoreCase(id)) {
                sb.append("• ").append(i.getTipo()).append("\n");
                sb.append("  ").append(i.getDescripcion()).append("\n\n");
            }
        }

        if (sb.isEmpty()) {
            mostrarAlerta("Incidencias", "Este envío no tiene incidencias registradas.");
        } else {
            mostrarAlerta("Incidencias del Envío", sb.toString());
        }
    }


    @FXML
    private void volver() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/ClienteView.fxml"));

            Stage stage = (Stage) txtIdEnvio.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo cargar el menú.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
}
