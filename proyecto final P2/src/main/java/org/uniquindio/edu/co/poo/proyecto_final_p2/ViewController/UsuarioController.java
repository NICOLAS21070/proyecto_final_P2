package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Usuario;

import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class UsuarioController {

    @FXML
    private TableView<Envio> tablaEnvios;
    @FXML
    private TableColumn<Envio, String> colIdEnvio, colOrigen, colDestino, colEstado;
    @FXML
    private TableColumn<Envio, Double> colCosto;
    @FXML
    private Button btnNuevoEnvio, btnCancelarEnvio, btnVerDetalles, btnCerrarSesion;

    private LogisticaFacade fachada = new LogisticaFacade();
    private Usuario usuarioActual;

    @FXML
    public void initialize() {
        configurarTabla();
        cargarMisEnvios();
    }

    private void configurarTabla() {
        colIdEnvio.setCellValueFactory(new PropertyValueFactory<>("idEnvio"));
        colOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
    }

    private void cargarMisEnvios() {
        tablaEnvios.getItems().setAll(fachada.listarEnvios());
        System.out.println("📬 Envíos del usuario cargados.");
    }

    @FXML
    private void crearEnvio() {
        fachada.crearEnvio("Calle 10 #5-22", "Carrera 20 #12-45", 4.2, 0.15, "Normal",false, // seguro
                false, // fragil
                false, // firma
                false,  // prioridadExtra
                "Carrera 20 #12-45",
                "Samuel"
        );
        mostrarAlerta("✅ Envío creado correctamente.");
        cargarMisEnvios();
    }

    @FXML
    private void cancelarEnvio() {
        Envio envioSeleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        if (envioSeleccionado != null) {
            fachada.cancelarEnvio(envioSeleccionado.getIdEnvio());
            mostrarAlerta("🛑 Envío cancelado correctamente.");
            cargarMisEnvios();
        } else {
            mostrarAlerta("Selecciona un envío primero.");
        }
    }

    @FXML
    private void verDetalles() {
        Envio envioSeleccionado = tablaEnvios.getSelectionModel().getSelectedItem();
        if (envioSeleccionado != null) {
            mostrarAlerta("📄 Detalles del Envío:\n" +
                    "Origen: " + envioSeleccionado.getOrigen() +
                    "\nDestino: " + envioSeleccionado.getDestino() +
                    "\nEstado: " + envioSeleccionado.getEstado() +
                    "\nCosto: $" + envioSeleccionado.getCosto());
        } else {
            mostrarAlerta("Selecciona un envío para ver sus detalles.");
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
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
        // Aquí podrías actualizar etiquetas, datos o inicializar vistas con su información
    }
}
