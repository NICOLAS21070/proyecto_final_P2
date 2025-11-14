package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FormularioEnvioController {

    @FXML
    private TextField txtOrigen, txtDestino, txtPeso, txtVolumen;
    @FXML
    private ComboBox<String> comboPrioridad;
    @FXML
    private Button btnGuardar, btnCancelar;
    @FXML
    private CheckBox chkSeguro, chkFragil, chkFirma, chkPrioridadExtra;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtRemitente;

    private final LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    public void initialize() {
        comboPrioridad.getItems().addAll("Normal", "Urgente", "Exprés");
        comboPrioridad.getSelectionModel().selectFirst();
    }

    @FXML
    private void guardarEnvio() {
        try {
            String origen = txtOrigen.getText().trim();
            String destino = txtDestino.getText().trim();
            double peso = Double.parseDouble(txtPeso.getText().trim());
            double volumen = Double.parseDouble(txtVolumen.getText().trim());
            String prioridad = comboPrioridad.getValue();

            if (origen.isEmpty() || destino.isEmpty()) {
                mostrarAlerta("⚠️ Datos incompletos", "Por favor ingrese origen y destino.");
                return;
            }

            Envio envio = fachada.crearEnvio(
                    origen,
                    destino,
                    peso,
                    volumen,
                    prioridad,
                    chkSeguro.isSelected(),
                    chkFragil.isSelected(),
                    chkFirma.isSelected(),
                    chkPrioridadExtra.isSelected(),
                    txtDireccion.getText().trim(),
                    txtRemitente.getText().trim()

            );

            mostrarAlerta("✅ Envío Creado", String.format(
                    "El envío fue creado exitosamente.\n\n🆔 ID: %s\n Costo: $%,.2f",
                    envio.getIdEnvio(),
                    envio.getCosto().getBase()
            ));

            volverAlMenuUsuario();

        } catch (NumberFormatException e) {
            mostrarAlerta("❌ Formato inválido", "Por favor ingrese valores numéricos válidos en peso y volumen.");
        } catch (Exception e) {
            mostrarAlerta("❌ Error inesperado", "No se pudo crear el envío. Detalle: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelar() {
        volverAlMenuUsuario();
    }

    private void volverAlMenuUsuario() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/UsuarioView.fxml"));
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Usuario");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
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
