package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Repartidor;

import java.io.IOException;

public class CambiarEstadoRepartidorController {

    private final LogisticaFacade fachada = new LogisticaFacade();

    @FXML private TextField txtBuscarDocumento;

    @FXML private TextField txtUsuario;
    @FXML private TextField txtNombreReal;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtZona;
    @FXML private TextField txtEstadoActual;

    @FXML private ChoiceBox<String> choiceNuevoEstado;

    private Repartidor repartidorEncontrado = null;

    @FXML
    private void initialize() {
        choiceNuevoEstado.getItems().addAll("Activo", "Inactivo");
        choiceNuevoEstado.getSelectionModel().selectFirst();
    }

    @FXML
    private void buscarRepartidor() {

        String doc = txtBuscarDocumento.getText();

        if (doc == null || doc.trim().isEmpty()) {
            showError("Ingresa un documento para buscar.");
            return;
        }

        doc = doc.trim();

        // Buscar en la fachada
        repartidorEncontrado = null;
        for (Repartidor r : fachada.obtenerRepartidores()) {
            if (r.getDocumento().equalsIgnoreCase(doc)) {
                repartidorEncontrado = r;
                break;
            }
        }

        if (repartidorEncontrado == null) {
            showError("No se encontró repartidor con documento: " + doc);
            clearCampos();
            return;
        }

        mostrarEnCampos(repartidorEncontrado);
    }

    private void mostrarEnCampos(Repartidor r) {
        txtUsuario.setText(r.getNombreUsuario());
        txtNombreReal.setText(r.getNombreReal());
        txtDocumento.setText(r.getDocumento());
        txtTelefono.setText(r.getTelefono());
        txtZona.setText(r.getZonaCobertura());
        txtEstadoActual.setText(r.getEstadoDisponibilidad());

        if (r.getEstadoDisponibilidad().equalsIgnoreCase("Inactivo")) {
            choiceNuevoEstado.getSelectionModel().select("Inactivo");
        } else {
            choiceNuevoEstado.getSelectionModel().select("Activo");
        }
    }

    @FXML
    private void guardarNuevoEstado() {

        if (repartidorEncontrado == null) {
            showError("Primero busca un repartidor.");
            return;
        }

        String nuevoEstado = choiceNuevoEstado.getValue();

        // 🚀 USAMOS EXACTAMENTE EL MÉTODO QUE TIENE TU FACHADA
        fachada.actualizarRepartidor(
                repartidorEncontrado.getDocumento(),         // documentoOriginal
                repartidorEncontrado.getNombreUsuario(),      // nuevoUsuario
                repartidorEncontrado.getContrasena(),         // nuevaContrasena
                repartidorEncontrado.getNombreReal(),         // nuevoNombreReal
                repartidorEncontrado.getDocumento(),          // nuevoDocumento (igual)
                repartidorEncontrado.getTelefono(),           // nuevoTelefono
                repartidorEncontrado.getZonaCobertura(),      // nuevaZona
                nuevoEstado                                   // nuevoEstado
        );

        txtEstadoActual.setText(nuevoEstado);
        showInfo("Estado actualizado correctamente a: " + nuevoEstado);
    }

    @FXML
    private void volverMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/View/RepartidorView.fxml"
            ));
            Stage stage = (Stage) txtBuscarDocumento.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showError("No se pudo volver al menú.");
        }
    }

    private void clearCampos() {
        txtUsuario.clear();
        txtNombreReal.clear();
        txtDocumento.clear();
        txtTelefono.clear();
        txtZona.clear();
        txtEstadoActual.clear();
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Error");
        a.setContentText(msg);
        a.showAndWait();
    }

    private void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
