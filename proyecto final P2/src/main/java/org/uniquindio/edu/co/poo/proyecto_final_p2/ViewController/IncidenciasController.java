package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;


import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class IncidenciasController {

    @FXML
    private TextField txtIdEnvio;
    @FXML
    private ComboBox<String> comboTipo;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private Button btnRegistrar, btnVolver;

    private LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    public void initialize() {
        comboTipo.getSelectionModel().selectFirst();
    }

    @FXML
    private void registrarIncidencia() {
        try {
            String idEnvio = txtIdEnvio.getText();
            String tipo = comboTipo.getValue();
            String descripcion = txtDescripcion.getText();

            if (idEnvio.isEmpty() || descripcion.isEmpty()) {
                mostrarAlerta("⚠️ Debes llenar todos los campos.");
                return;
            }

            boolean registrada = fachada.reportarIncidencia(idEnvio, tipo, descripcion);

            if (registrada) {
                mostrarAlerta("✅ Incidencia registrada correctamente.");
                limpiarCampos();
            } else {
                mostrarAlerta("❌ No se encontró un envío con ese ID.");
            }
        } catch (Exception e) {
            mostrarAlerta("❌ Error al registrar la incidencia.");
        }
    }

    private void limpiarCampos() {
        txtIdEnvio.clear();
        txtDescripcion.clear();
        comboTipo.getSelectionModel().selectFirst();
    }

    @FXML
    private void volverMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/uniquindio/edu/co/poo/proyecto_final_p2/view/RepartidorView.fxml"));
            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Repartidor");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
