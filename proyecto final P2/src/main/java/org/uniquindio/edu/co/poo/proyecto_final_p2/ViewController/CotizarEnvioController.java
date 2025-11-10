package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Usuario;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;

import java.io.IOException;

public class CotizarEnvioController {

    @FXML private ComboBox<String> cbOrigen;
    @FXML private ComboBox<String> cbDestino;

    @FXML private TextField txtPeso;
    @FXML private TextField txtVolumen;
    @FXML private ChoiceBox<String> cbPrioridad;

    @FXML private CheckBox chkSeguro;
    @FXML private CheckBox chkFragil;
    @FXML private CheckBox chkFirma;
    @FXML private CheckBox chkPrioridadExtra;

    @FXML private Label lblCosto;
    @FXML private VBox panelResultado;

    private Usuario usuarioActual;

    @FXML
    public void initialize() {
        cbPrioridad.setValue("Normal");

        if (cbOrigen.getItems().isEmpty()) {
            cbOrigen.getItems().addAll("Armenia", "Pereira", "Calarcá", "Circasia");
        }
        if (cbDestino.getItems().isEmpty()) {
            cbDestino.getItems().addAll("Armenia", "Pereira", "Calarcá", "Circasia");
        }

        cbOrigen.getSelectionModel().selectFirst();
        cbDestino.getSelectionModel().selectFirst();
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }

    @FXML
    private void calcularTarifa() {
        try {
            String origen = cbOrigen.getValue();
            String destino = cbDestino.getValue();
            double peso = Double.parseDouble(txtPeso.getText().trim());
            double volumen = Double.parseDouble(txtVolumen.getText().trim());
            String prioridad = cbPrioridad.getValue();

            if (origen == null || destino == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Por favor selecciona origen y destino.");
                return;
            }

            LogisticaFacade fachada = new LogisticaFacade();

            double costoBase = fachada.calcularCosto(
                    peso, volumen, prioridad,
                    chkSeguro.isSelected(),
                    chkFragil.isSelected(),
                    chkFirma.isSelected(),
                    chkPrioridadExtra.isSelected()
            );

            lblCosto.setText(String.format("Costo estimado: $%,.2f", costoBase));
            panelResultado.setVisible(true);

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Formato inválido",
                    "Por favor ingresa valores numéricos válidos en peso y volumen.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error inesperado", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void crearEnvioDesdeCotizacion() {
        try {
            String origen = cbOrigen.getValue();
            String destino = cbDestino.getValue();
            double peso = Double.parseDouble(txtPeso.getText().trim());
            double volumen = Double.parseDouble(txtVolumen.getText().trim());
            String prioridad = cbPrioridad.getValue();

            if (origen == null || destino == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos",
                        "Por favor selecciona origen y destino.");
                return;
            }

            LogisticaFacade fachada = new LogisticaFacade();

            Envio nuevoEnvio = fachada.crearEnvio(
                    origen, destino, peso, volumen, prioridad,
                    chkSeguro.isSelected(),
                    chkFragil.isSelected(),
                    chkFirma.isSelected(),
                    chkPrioridadExtra.isSelected()
            );

            // ✅ Corregido: ahora se usa getCostoTotal() (devuelve double)
            mostrarAlerta(Alert.AlertType.INFORMATION, "✅ Envío Creado",
                    String.format("El envío fue creado exitosamente.\n\n🆔 ID: %s\n💰 Costo: $%,.2f",
                            nuevoEnvio.getIdEnvio(), nuevoEnvio.getCostoTotal()));

            // ✅ Volver al panel del cliente
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/ClienteView.fxml"));
            Parent root = loader.load();

            ClienteController clienteController = loader.getController();
            if (usuarioActual != null) {
                clienteController.setUsuarioActual(usuarioActual);
                clienteController.refrescarEnvios();
            }

            Stage stage = (Stage) cbOrigen.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Cliente");
            stage.show();

        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Formato inválido",
                    "Por favor ingresa valores numéricos válidos en peso y volumen.");
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al volver",
                    "No se pudo cargar la vista del cliente: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error inesperado", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/ClienteView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) cbOrigen.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Cliente");
            stage.show();

        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error al volver",
                    "No se pudo cargar la vista del cliente: " + e.getMessage());
        }
    }

    // ✅ Método flexible para mostrar alertas del tipo correcto
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
