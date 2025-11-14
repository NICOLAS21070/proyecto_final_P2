package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent; // <-- IMPORT CORRECTO
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Repartidor;

import java.io.IOException;

public class ConsultarEnviosController {

    // ============================================================
    // TABLA REPARTIDORES
    // ============================================================
    @FXML private TableView<Repartidor> tablaRepartidores;
    @FXML private TableColumn<Repartidor, String> colNombre;
    @FXML private TableColumn<Repartidor, String> colDocumento;

    // ============================================================
    // TABLA ENVIOS
    // ============================================================
    @FXML private TableView<Envio> tablaEnvios;
    @FXML private TableColumn<Envio, String> colIdEnvio;
    @FXML private TableColumn<Envio, String> colDestino;
    @FXML private TableColumn<Envio, String> colEstado;
    @FXML private TableColumn<Envio, String> colDescripcion;
    @FXML private TableColumn<Envio, Double> colCosto;

    // ============================================================
    // LISTAS OBSERVABLES
    // ============================================================
    private final ObservableList<Repartidor> listaRepartidores = FXCollections.observableArrayList();
    private final ObservableList<Envio> listaEnvios = FXCollections.observableArrayList();

    // ============================================================
    // FACHADA
    // ============================================================
    private final LogisticaFacade fachada = new LogisticaFacade();

    // ============================================================
    // INICIALIZACIÓN
    // ============================================================
    @FXML
    public void initialize() {

        // --- columnas repartidor ---
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreReal"));
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        tablaRepartidores.setItems(listaRepartidores);

        // --- columnas envío ---
        colIdEnvio.setCellValueFactory(new PropertyValueFactory<>("idEnvio"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costoTotal"));
        tablaEnvios.setItems(listaEnvios);

        // cargar repartidores
        cargarRepartidores();

        // evento: seleccionar repartidor
        tablaRepartidores.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, nuevo) -> mostrarEnviosAsignados(nuevo)
        );
    }

    // ============================================================
    // CARGAR REPARTIDORES
    // ============================================================
    private void cargarRepartidores() {
        listaRepartidores.clear();
        listaRepartidores.addAll(fachada.obtenerRepartidores());
    }

    // ============================================================
    // MOSTRAR ENVIOS DE UN REPARTIDOR
    // ============================================================
    private void mostrarEnviosAsignados(Repartidor repartidor) {

        if (repartidor == null)
            return;

        listaEnvios.clear();
        String documento = repartidor.getDocumento();

        for (Envio envio : fachada.listarEnvios()) {
            if (envio.getRepartidor() != null &&
                    envio.getRepartidor().equalsIgnoreCase(documento)) {
                listaEnvios.add(envio);
            }
        }

        if (listaEnvios.isEmpty()) {
            mostrarAlerta("Sin envíos asignados",
                    "El repartidor seleccionado no tiene envíos asignados.");
        }
    }

    // ============================================================
    // ALERTAS
    // ============================================================
    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.show();
    }

    // ============================================================
    // BOTÓN VOLVER AL MENÚ
    // ============================================================
    @FXML
    private void volverMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/View/RepartidorView.fxml"
            ));

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo volver al menú.");
        }
    }

}
