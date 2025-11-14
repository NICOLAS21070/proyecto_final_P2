package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Repartidor;

import java.io.IOException;

public class GestionRepartidoresController {

    private final LogisticaFacade fachada = new LogisticaFacade();

    @FXML private TableView<Repartidor> tablaRepartidores;
    @FXML private TableColumn<Repartidor, String> colNombreReal;
    @FXML private TableColumn<Repartidor, String> colDocumento;
    @FXML private TableColumn<Repartidor, String> colTelefono;
    @FXML private TableColumn<Repartidor, String> colZona;
    @FXML private TableColumn<Repartidor, String> colEstado;

    @FXML private TextField txtNombreReal;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtZona;
    @FXML private TextField txtEstado;

    private ObservableList<Repartidor> listaRepartidores;

    @FXML
    public void initialize() {

        listaRepartidores = FXCollections.observableArrayList();
        tablaRepartidores.setItems(listaRepartidores);

        // CONFIGURAR COLUMNAS EXACTO A COMO ESTÁ EN EL FXML
        colNombreReal.setCellValueFactory(new PropertyValueFactory<>("nombreReal"));
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colZona.setCellValueFactory(new PropertyValueFactory<>("zonaCobertura"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoDisponibilidad"));

        // cargar desde la fachada
        listaRepartidores.addAll(fachada.obtenerRepartidores());
    }

    @FXML
    public void agregarRepartidorFormulario() {
        String nombre = txtNombreReal.getText();
        String documento = txtDocumento.getText();
        String telefono = txtTelefono.getText();
        String zona = txtZona.getText();
        String estado = txtEstado.getText();

        if (nombre.isEmpty() || documento.isEmpty() || telefono.isEmpty() || zona.isEmpty() || estado.isEmpty()) {
            showError("Todos los campos deben llenarse.");
            return;
        }

        String usuario = nombre.replace(" ", "").toLowerCase();
        String contrasena = "123";

        try {
            fachada.agregarRepartidor(usuario, contrasena, nombre, documento, telefono, zona, estado);
            Repartidor r = new Repartidor(usuario, contrasena, nombre, documento, telefono, zona, estado);
            listaRepartidores.add(r);

            limpiar();
            showInfo("Repartidor agregado correctamente.");
        } catch (Exception e) {
            showError("Error al agregar: " + e.getMessage());
        }
    }

    @FXML
    public void actualizarRepartidor() {
        Repartidor r = tablaRepartidores.getSelectionModel().getSelectedItem();
        if (r == null) { showError("Selecciona un repartidor primero."); return; }

        TextInputDialog dialog = new TextInputDialog(
                r.getNombreReal()+","+r.getDocumento()+","+r.getTelefono()+","+r.getZonaCobertura()+","+r.getEstadoDisponibilidad()
        );
        dialog.setHeaderText("Modificar repartidor (nombre,doc,tel,zona,estado)");
        dialog.showAndWait().ifPresent(datos -> {
            String[] d = datos.split(",");
            if (d.length != 5) { showError("Formato incorrecto."); return; }

            r.setNombreReal(d[0]);
            r.setDocumento(d[1]);
            r.setTelefono(d[2]);
            r.setZonaCobertura(d[3]);
            r.setEstadoDisponibilidad(d[4]);

            tablaRepartidores.refresh();
            showInfo("Repartidor actualizado.");
        });
    }

    @FXML
    public void eliminarRepartidor() {
        Repartidor r = tablaRepartidores.getSelectionModel().getSelectedItem();
        if (r == null) { showError("Selecciona un repartidor."); return; }

        fachada.eliminarRepartidor(r.getDocumento());
        listaRepartidores.remove(r);

        showInfo("Repartidor eliminado.");
    }

    @FXML
    public void consultarRepartidor() {
        Repartidor r = tablaRepartidores.getSelectionModel().getSelectedItem();
        if (r == null) { showError("Selecciona un repartidor."); return; }

        showInfo(
                "Nombre: " + r.getNombreReal() +
                        "\nDocumento: " + r.getDocumento() +
                        "\nTeléfono: " + r.getTelefono() +
                        "\nZona: " + r.getZonaCobertura() +
                        "\nEstado: " + r.getEstadoDisponibilidad()
        );
    }

    @FXML
    public void volverMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/org/uniquindio/edu/co/poo/proyecto_final_p2/View/RepartidorView.fxml"));
            Stage stage = (Stage) tablaRepartidores.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            showError("No se pudo cargar el menú.");
        }
    }

    private void limpiar() {
        txtNombreReal.clear();
        txtDocumento.clear();
        txtTelefono.clear();
        txtZona.clear();
        txtEstado.clear();
    }

    private void showError(String m) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(m);
        a.show();
    }

    private void showInfo(String m) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(m);
        a.show();
    }

}
