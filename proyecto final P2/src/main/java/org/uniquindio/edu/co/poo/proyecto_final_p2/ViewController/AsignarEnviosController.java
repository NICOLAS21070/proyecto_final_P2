package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.*;

import java.util.List;
import java.util.stream.Collectors;

public class AsignarEnviosController {

    @FXML private TableView<Envio> tablaEnvios;
    @FXML private TableColumn<Envio, String> colIdEnvio;
    @FXML private TableColumn<Envio, String> colRemitente;
    @FXML private TableColumn<Envio, String> colDestino;
    @FXML private TableColumn<Envio, String> colEstadoEnvio;

    @FXML private TableView<Repartidor> tablaRepartidores;
    @FXML private TableColumn<Repartidor, String> colNombreRep;
    @FXML private TableColumn<Repartidor, String> colTelefono;
    @FXML private TableColumn<Repartidor, String> colZona;
    @FXML private TableColumn<Repartidor, String> colEstadoRep;

    @FXML private Button btnAsignar;

    private LogisticaFacade fachada;

    @FXML
    public void initialize() {
        fachada = new LogisticaFacade();

        configurarTablas();
        cargarEnviosPendientes();
        cargarRepartidoresDisponibles();
    }

    private void configurarTablas() {

        colIdEnvio.setCellValueFactory(new PropertyValueFactory<>("idEnvio"));
        colRemitente.setCellValueFactory(new PropertyValueFactory<>("remitente"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colEstadoEnvio.setCellValueFactory(new PropertyValueFactory<>("estado"));

        colNombreRep.setCellValueFactory(new PropertyValueFactory<>("nombreReal"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colZona.setCellValueFactory(new PropertyValueFactory<>("zonaCobertura"));
        colEstadoRep.setCellValueFactory(new PropertyValueFactory<>("estadoDisponibilidad"));
    }

    private void cargarEnviosPendientes() {
        List<Envio> pendientes = fachada.obtenerEnvios()
                .stream()
                .filter(env -> env.getEstado().equalsIgnoreCase("Pendiente"))
                .collect(Collectors.toList());

        tablaEnvios.getItems().setAll(pendientes);
    }

    private void cargarRepartidoresDisponibles() {
        List<Repartidor> disponibles = fachada.obtenerRepartidores()
                .stream()
                .filter(rep -> rep.getEstadoDisponibilidad().equalsIgnoreCase("Activo"))
                .collect(Collectors.toList());

        tablaRepartidores.getItems().setAll(disponibles);
    }

    @FXML
    private void asignarEnvio() {
        Envio envio = tablaEnvios.getSelectionModel().getSelectedItem();
        Repartidor repartidor = tablaRepartidores.getSelectionModel().getSelectedItem();

        if (envio == null || repartidor == null) {
            mostrarError("Debes seleccionar un envío y un repartidor.");
            return;
        }

        // Actualizar datos del envío
        envio.setRepartidor(repartidor.getNombreReal());
        envio.setEstado("En ruta");

        // Actualizar estado del repartidor
        repartidor.setEstadoDisponibilidad("En ruta");

        mostrarInfo("El envío fue asignado correctamente.");

        cargarEnviosPendientes();
        cargarRepartidoresDisponibles();
    }

    private void mostrarError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.show();
    }

    private void mostrarInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg);
        a.show();
    }
    @FXML
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/AdminView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tablaRepartidores.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
