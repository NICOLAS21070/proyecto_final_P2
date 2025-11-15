package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Pago;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;

import java.io.IOException;

public class ConsultarPagosController {

    @FXML private TableView<Pago> tablaPagos;
    @FXML private TableColumn<Pago, String> colIdEnvio;
    @FXML private TableColumn<Pago, String> colCliente;
    @FXML private TableColumn<Pago, String> colDestino;
    @FXML private TableColumn<Pago, Double> colMonto;
    @FXML private TableColumn<Pago, String> colEstadoPago;

    private final ObservableList<Pago> listaPagos = FXCollections.observableArrayList();
    private final LogisticaFacade logisticaFacade = new LogisticaFacade();

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarPagos();
    }

    private void configurarColumnas() {
        colIdEnvio.setCellValueFactory(new PropertyValueFactory<>("idEnvio"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colEstadoPago.setCellValueFactory(new PropertyValueFactory<>("estadoPago"));
    }

    private void cargarPagos() {
        listaPagos.clear();

        for (Envio envio : logisticaFacade.listarEnvios()) {
            String cliente = envio.getUsuario() != null ? envio.getUsuario().getNombreUsuario() : "Desconocido";
            String destino = envio.getDestino() != null ? envio.getDestino() : "No definido";
            double monto = envio.getCostoTotal();
            String estadoPago = "Activo".equalsIgnoreCase(envio.getEstado()) ? "Pendiente" : "Pagado";

            listaPagos.add(new Pago(envio.getIdEnvio(), cliente, destino, monto, estadoPago));
        }

        tablaPagos.setItems(listaPagos);
    }

    // ========================================================
    // BOTÓN VOLVER
    // ========================================================
    @FXML
    private void volverMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/View/ClienteView.fxml"
            ));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
