package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Repartidor;

public class GestionarRepartidoresAdminController {

    @FXML
    private TableView<Repartidor> tablaRepartidores;
    @FXML
    private TableColumn<Repartidor, String> colNombre;
    @FXML
    private TableColumn<Repartidor, String> colRol;
    @FXML
    private TableColumn<Repartidor, String> colUsuario;
    @FXML
    private TableColumn<Repartidor, String> colDocumento;
    @FXML
    private TableColumn<Repartidor, String> colTelefono;
    @FXML
    private TableColumn<Repartidor, String> colZona;
    @FXML
    private TableColumn<Repartidor, String> colEstado;
    @FXML
    private TableColumn<Repartidor, String> colId;

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private TextField txtNombreReal;
    @FXML
    private TextField txtDocumento;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtZona;
    @FXML
    private ComboBox<String> cbEstado;

    private final LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("idRepartidor"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreReal"));
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colZona.setCellValueFactory(new PropertyValueFactory<>("zonaCobertura"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoDisponibilidad"));

        // ComboBox estados
        cbEstado.getItems().addAll("Activo", "Inactivo", "En ruta");
        cbEstado.setValue("Activo");

        tablaRepartidores.setItems(fachada.obtenerRepartidores());

        // Al seleccionar un repartidor → cargar datos al formulario
        tablaRepartidores.getSelectionModel().selectedItemProperty().addListener((obs, oldV, nuevo) -> {
            if (nuevo != null) {
                txtUsuario.setText(nuevo.getNombreUsuario());
                txtContrasena.setText(nuevo.getContrasena());
                txtNombreReal.setText(nuevo.getNombreReal());
                txtDocumento.setText(nuevo.getDocumento());
                txtTelefono.setText(nuevo.getTelefono());
                txtZona.setText(nuevo.getZonaCobertura());
                cbEstado.setValue(nuevo.getEstadoDisponibilidad());
            }
        });
    }

    private void cargarRepartidores() {
        tablaRepartidores.setItems(fachada.obtenerRepartidores());
    }

    // --------------------------
    // AGREGAR
    // --------------------------
    @FXML
    private void agregarRepartidor() {

        fachada.agregarRepartidor(
                txtUsuario.getText(),
                txtContrasena.getText(),
                txtNombreReal.getText(),
                txtDocumento.getText(),
                txtTelefono.getText(),
                txtZona.getText(),
                cbEstado.getValue()
        );


        limpiar();
    }

    // --------------------------
    // ELIMINAR
    // --------------------------
    @FXML
    private void eliminarRepartidor() {

        Repartidor seleccionado = tablaRepartidores.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            fachada.eliminarRepartidor(seleccionado.getDocumento());

            limpiar();
        }
    }

    // --------------------------
    // ACTUALIZAR
    // --------------------------
    @FXML
    private void actualizarRepartidor() {

        Repartidor seleccionado = tablaRepartidores.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {

            fachada.actualizarRepartidor(
                    seleccionado.getDocumento(),       // documento original (clave)
                    txtUsuario.getText(),              // nuevo usuario
                    txtContrasena.getText(),           // nueva contraseña
                    txtNombreReal.getText(),           // nuevo nombre real
                    txtDocumento.getText(),            // nuevo documento
                    txtTelefono.getText(),             // nuevo telefono
                    txtZona.getText(),                 // nueva zona
                    cbEstado.getValue()                // nuevo estado
            );


            tablaRepartidores.refresh();
        }
    }

    // --------------------------
    // VOLVER
    // --------------------------
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

    private void limpiar() {
        txtUsuario.clear();
        txtContrasena.clear();
        txtNombreReal.clear();
        txtDocumento.clear();
        txtTelefono.clear();
        txtZona.clear();
        cbEstado.setValue("Activo");
    }
}
