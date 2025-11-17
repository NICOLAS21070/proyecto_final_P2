package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.SesionUsuario;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Usuario;

import java.io.File;
import java.io.PrintWriter;

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

        colIdEnvio.setCellValueFactory(new PropertyValueFactory<>("idEnvio"));

        colCliente.setCellValueFactory(cellData -> {
            String nombre = "Desconocido";
            if (cellData.getValue().getUsuario() != null) {
                nombre = cellData.getValue().getUsuario().getNombreUsuario();
            }
            return new SimpleStringProperty(nombre);
        });

        colDestino.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDestino()));

        colFecha.setCellValueFactory(cellData ->
                new SimpleStringProperty("Sin fecha"));

        colEstado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEstado()));
    }

    private void cargarDatos() {
        listaHistorial.clear();

        Usuario actual = SesionUsuario.getUsuarioActual();

        listaHistorial.addAll(
                logisticaFacade.listarEnvios()
                        .stream()
                        .filter(e -> e.getUsuario() != null &&
                                e.getUsuario().getNombreUsuario().equals(actual.getNombreUsuario()))
                        .toList()
        );

        tablaHistorial.setItems(listaHistorial);
    }

    @FXML
    private void actualizarHistorial() {
        cargarDatos();
    }

    // ----------------------------------------------------------
    // 🔥 MÉTODO PARA DESCARGAR CSV
    // ----------------------------------------------------------

    @FXML
    private void descargarCSV() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar historial en CSV");
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Archivo CSV", "*.csv"));

        File archivo = fileChooser.showSaveDialog(tablaHistorial.getScene().getWindow());
        if (archivo == null) return;

        try (PrintWriter writer = new PrintWriter(archivo)) {

            writer.println("ID,Cliente,Destino,Fecha,Estado");

            for (Envio envio : listaHistorial) {

                String cliente = (envio.getUsuario() != null)
                        ? envio.getUsuario().getNombreUsuario()
                        : "Desconocido";

                writer.println(
                        envio.getIdEnvio() + "," +
                                cliente + "," +
                                envio.getDestino() + "," +
                                "Sin fecha" + "," +
                                envio.getEstado()
                );
            }

            mostrarInfo("Archivo CSV guardado correctamente:\n" + archivo.getAbsolutePath());

        } catch (Exception e) {
            mostrarError("Error al guardar el archivo CSV: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------
    // 🔥 ALERTAS
    // ----------------------------------------------------------

    private void mostrarError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg);
        a.show();
    }

    private void mostrarInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg);
        a.show();
    }

    // ----------------------------------------------------------
    // 🔙 VOLVER AL MENÚ
    // ----------------------------------------------------------

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
