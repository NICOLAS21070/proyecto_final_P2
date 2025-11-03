package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;


import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class RepartidorController {

    @FXML
    private TableView<Envio> tablaEnvios;
    @FXML
    private TableColumn<Envio, String> colIdEnvio, colOrigen, colDestino, colEstado, colFecha;
    @FXML
    private Button btnActualizarEstado, btnRegistrarIncidencia, btnCerrarSesion;

    private LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    public void initialize() {
        configurarTabla();
        cargarEnviosAsignados();
    }

    private void configurarTabla() {
        colIdEnvio.setCellValueFactory(new PropertyValueFactory<>("idEnvio"));
        colOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
    }

    private void cargarEnviosAsignados() {
        tablaEnvios.getItems().setAll(fachada.listarEnvios());
        System.out.println("📦 Envíos cargados en tabla del repartidor.");
    }

    @FXML
    private void actualizarEstado() {
        Envio envioSeleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        if (envioSeleccionado != null) {
            fachada.actualizarEstadoEnvio(envioSeleccionado.getIdEnvio(), "Entregado");
            cargarEnviosAsignados();
            mostrarAlerta("Estado actualizado a 'Entregado'");
        } else {
            mostrarAlerta("Selecciona un envío primero.");
        }
    }

    @FXML
    private void registrarIncidencia() {
        Envio envioSeleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        if (envioSeleccionado != null) {
            fachada.reportarIncidencia(
                    envioSeleccionado.getIdEnvio(),
                    "General", // tipo de incidencia
                    "Incidencia registrada por el repartidor."
            );
            mostrarAlerta("Incidencia registrada correctamente.");
        } else {
            mostrarAlerta("Selecciona un envío primero.");
        }
    }

    @FXML
    private void cerrarSesion() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/uniquindio/logistica/view/LoginView.fxml"));
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Ingreso al Sistema de Logística");
            stage.show();
        } catch (IOException e) {
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
