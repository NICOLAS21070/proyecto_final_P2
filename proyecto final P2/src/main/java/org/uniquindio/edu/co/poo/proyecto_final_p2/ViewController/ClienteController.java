package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Envio;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.SistemaEnvios;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Usuario;

public class ClienteController {

    @FXML private Button btnCotizar;
    @FXML private Button btnNuevoEnvio;
    @FXML private Button btnModificarEnvio;
    @FXML private Button btnCancelarEnvio;
    @FXML private Button btnPagos;
    @FXML private Button btnHistorial;
    @FXML private Button btnRastrear;
    @FXML private Button btnCerrarSesion;

    // 👇 Eliminamos la tabla real, ya que no se usa en el FXML
    // pero mantenemos el método por compatibilidad futura
    // private TableView<Envio> tablaEnvios;

    private final LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    public void initialize() {
        System.out.println("✅ Panel del cliente cargado correctamente (sin tabla de envíos).");
        // No llamamos a refrescarEnvios() porque no hay tabla
    }

    @FXML
    private void cotizarEnvio() {
        cargarVista("/org/uniquindio/edu/co/poo/proyecto_final_p2/view/CotizarEnvio.fxml",
                "Cotizar / Crear Envío");
    }

    @FXML
    private void modificarEnvio() {
        cargarVista("/org/uniquindio/edu/co/poo/proyecto_final_p2/view/ModificarEnvio.fxml",
                "Modificar Envío");
    }

    @FXML
    private void cancelarEnvio() {
        cargarVista("/org/uniquindio/edu/co/poo/proyecto_final_p2/view/CancelarEnvio.fxml",
                "Cancelar Envío");
    }

    @FXML
    private void verHistorial() {
        cargarVista("/org/uniquindio/edu/co/poo/proyecto_final_p2/view/HistorialEnviosView.fxml",
                "Historial de Envíos");
    }

    @FXML
    private void consultarPagos() {
        cargarVista("/org/uniquindio/edu/co/poo/proyecto_final_p2/view/PagosView.fxml",
                "Pagos y Comprobantes");
    }

    @FXML
    private void rastrearEnvio() {
        cargarVista("/org/uniquindio/edu/co/poo/proyecto_final_p2/view/RastrearEnvio.fxml",
                "Rastrear Envío");
    }

    // Método dejado solo por compatibilidad (no se ejecuta nada)
    public void refrescarEnvios() {
        System.out.println("ℹ️ No se muestran envíos en esta vista (tabla no presente).");
    }

    @FXML
    private void cerrarSesion() {
        cargarVista("/org/uniquindio/edu/co/poo/proyecto_final_p2/view/LoginView.fxml",
                "Ingreso al Sistema de Logística");
    }

    private void cargarVista(String ruta, String titulo) {
        try {
            var url = getClass().getResource(ruta);
            if (url == null) {
                System.err.println("❌ No se encontró el archivo FXML en la ruta: " + ruta);
                return;
            }

            Parent root = FXMLLoader.load(url);
            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();

            System.out.println("✅ Vista abierta correctamente: " + titulo);
        } catch (Exception e) {
            System.err.println("❌ Error al abrir vista: " + titulo + " → " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        // Método reservado por si luego se usa para pasar el usuario logueado
    }
}
