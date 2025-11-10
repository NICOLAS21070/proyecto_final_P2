package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;

public class VerHistorialController {

    @FXML private TableView<Envio> tablaHistorial;
    @FXML private TableColumn<Envio, String> colIdEnvio;
    @FXML private TableColumn<Envio, String> colCliente;
    @FXML private TableColumn<Envio, String> colDestino;
    @FXML private TableColumn<Envio, String> colFecha;
    @FXML private TableColumn<Envio, String> colEstado;

    private final LogisticaFacade logisticaFacade = new LogisticaFacade();
    private final ObservableList<Envio> listaHistorial = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarDatos();
    }

    private void configurarColumnas() {
        // 🔹 Muestra el ID del envío
        colIdEnvio.setCellValueFactory(new PropertyValueFactory<>("idEnvio"));

        // 🔹 Muestra el nombre del cliente asociado
        colCliente.setCellValueFactory(cellData -> {
            String nombre = "Desconocido";
            if (cellData.getValue().getUsuario() != null) {
                nombre = cellData.getValue().getUsuario().getNombreUsuario();
            }
            return new SimpleStringProperty(nombre);
        });

        // 🔹 Muestra el destino del envío
        colDestino.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDestino()));

        // 🔹 Si tienes una fecha real, cámbiala aquí. Si no, muestra “Sin fecha”
        colFecha.setCellValueFactory(cellData ->
                new SimpleStringProperty("Sin fecha"));

        // 🔹 Estado del envío ("Activo" / "Inactivo")
        colEstado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEstado()));
    }

    private void cargarDatos() {
        listaHistorial.clear();
        listaHistorial.addAll(logisticaFacade.listarEnvios());
        tablaHistorial.setItems(listaHistorial);
    }

    @FXML
    private void actualizarHistorial() {
        cargarDatos();
    }

    @FXML
    private void volverMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/ClienteView.fxml"
            ));
            Parent root = loader.load();

            Stage stage = (Stage) tablaHistorial.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
