package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;



import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Map;

public class ReportesController {

    @FXML
    private BarChart<String, Number> graficoEnvios;
    @FXML
    private TableView<Envio> tablaEnvios;
    @FXML
    private TableColumn<Envio, String> colOrigen, colDestino, colEstado, colRepartidor;
    @FXML
    private Button btnActualizar, btnVolver;

    private LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    public void initialize() {
        colOrigen.setCellValueFactory(data -> data.getValue().origenProperty());
        colDestino.setCellValueFactory(data -> data.getValue().destinoProperty());
        colEstado.setCellValueFactory(data -> data.getValue().estadoProperty());
        colRepartidor.setCellValueFactory(data -> data.getValue().repartidorProperty());
        cargarDatos();
    }

    @FXML
    private void cargarDatos() {
        tablaEnvios.setItems(FXCollections.observableArrayList(fachada.obtenerEnvios()));
        actualizarGrafico();
    }

    private void actualizarGrafico() {
        graficoEnvios.getData().clear();
        Map<String, Integer> conteo = fachada.contarEnviosPorEstado();

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Estados de Envíos");

        conteo.forEach((estado, cantidad) ->
                serie.getData().add(new XYChart.Data<>(estado, cantidad))
        );

        graficoEnvios.getData().add(serie);
    }

    @FXML
    private void volverMenu() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/uniquindio/logistica/view/AdminView.fxml"));
            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Administrador");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
