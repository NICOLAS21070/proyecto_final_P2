package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;

public class RegistrarIncidenciaController {

    @FXML private TextField txtIdEnvio;
    @FXML private ChoiceBox<String> cbTipoIncidencia;
    @FXML private TextArea txtDescripcion;

    private final LogisticaFacade logistica = new LogisticaFacade();
    private Envio envioActual;


    @FXML
    public void initialize() {

        cbTipoIncidencia.getItems().addAll(
                "Retraso",
                "Daño en el paquete",
                "Dirección incorrecta",
                "No se pudo entregar"
        );

        cbTipoIncidencia.setValue("Retraso"); // valor por defecto
    }
    @FXML
    private void buscarEnvio() {
        String id = txtIdEnvio.getText().trim();

        if (id.isEmpty()) {
            mostrar("Debe ingresar un ID de envío.", Alert.AlertType.WARNING);
            return;
        }

        envioActual = logistica.obtenerEnvioPorId(id);

        if (envioActual == null) {
            mostrar("No se encontró el envío.", Alert.AlertType.ERROR);
        } else {
            mostrar("Envío encontrado: " + envioActual.getDestino(), Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void registrarIncidencia() {
        if (envioActual == null) {
            mostrar("Debe buscar un envío primero.", Alert.AlertType.WARNING);
            return;
        }

        String tipo = cbTipoIncidencia.getValue();
        String desc = txtDescripcion.getText().trim();

        if (tipo == null || desc.isEmpty()) {
            mostrar("Debe completar todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        boolean ok = logistica.reportarIncidencia(envioActual.getIdEnvio(), tipo, desc);

        if (ok) {
            mostrar("Incidencia registrada correctamente.", Alert.AlertType.INFORMATION);
        } else {
            mostrar("Error registrando la incidencia.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/AdminView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) txtIdEnvio.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (Exception e) {
            mostrar("No se pudo volver al menú.", Alert.AlertType.ERROR);
        }
    }

    private void mostrar(String msg, Alert.AlertType type) {
        Alert a = new Alert(type, msg);
        a.show();
    }
}
