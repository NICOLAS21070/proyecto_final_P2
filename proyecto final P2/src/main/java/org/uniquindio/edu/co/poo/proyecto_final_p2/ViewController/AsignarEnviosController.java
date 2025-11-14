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

    // 🔥 MÉTODO COMPLETO YA CORREGIDO
    @FXML
    private void asignarEnvio() {

        Envio envio = tablaEnvios.getSelectionModel().getSelectedItem();

        if (envio == null) {
            mostrarError("Debes seleccionar un envío.");
            return;
        }

        List<Repartidor> disponibles = fachada.obtenerRepartidores()
                .stream()
                .filter(rep -> rep.getEstadoDisponibilidad().equalsIgnoreCase("Activo"))
                .collect(Collectors.toList());

        if (disponibles.isEmpty()) {
            mostrarError("No hay repartidores disponibles.");
            return;
        }

        List<String> nombres = disponibles.stream()
                .map(Repartidor::getNombreReal)
                .collect(Collectors.toList());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(nombres.get(0), nombres);
        dialog.setTitle("Asignar envío");
        dialog.setHeaderText("Seleccione un repartidor para asignar el envío");
        dialog.setContentText("Repartidor:");

        dialog.showAndWait().ifPresent(nombreSeleccionado -> {

            Repartidor repartidorSeleccionado = disponibles.stream()
                    .filter(r -> r.getNombreReal().equals(nombreSeleccionado))
                    .findFirst()
                    .orElse(null);

            if (repartidorSeleccionado == null) {
                mostrarError("Error interno: no se encontró el repartidor.");
                return;
            }

            // ⭐⭐⭐ CORRECCIÓN IMPORTANTE ⭐⭐⭐
            // Guardar el DOCUMENTO, no el nombre
            envio.setRepartidor(repartidorSeleccionado.getDocumento());
            envio.setEstado("En ruta");

            // Repartidor ahora queda ocupado
            repartidorSeleccionado.setEstadoDisponibilidad("En ruta");

            mostrarInfo("El envío fue asignado a: " + nombreSeleccionado);

            cargarEnviosPendientes();
            cargarRepartidoresDisponibles();
        });
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
