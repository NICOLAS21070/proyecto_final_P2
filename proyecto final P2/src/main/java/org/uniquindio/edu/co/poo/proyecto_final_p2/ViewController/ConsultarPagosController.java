package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Pago;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;

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
}
