package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ChoiceBox<String> cbTipoUsuario;

    @FXML
    private Label lblMensaje;

    @FXML
    private Button btnLogin;

    private final LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    private void initialize() {
        // Agregar opciones de tipo de usuario
        cbTipoUsuario.getItems().addAll("Administrador", "Cliente", "Repartidor");
        cbTipoUsuario.setValue("Cliente");
    }

    @FXML
    private void ingresar() {
        try {
            String usuario = txtUsuario.getText().trim();
            String password = txtPassword.getText().trim();
            String tipo = cbTipoUsuario.getValue();

            if (usuario.isEmpty() || password.isEmpty()) {
                lblMensaje.setText("⚠️ Por favor completa todos los campos.");
                return;
            }

            // Validación (simulada o real)
            if (fachada.validarUsuario(usuario, password, tipo)) {
                abrirVista(tipo);
            } else {
                lblMensaje.setText("❌ Usuario o contraseña incorrectos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            lblMensaje.setText("❌ Error al intentar iniciar sesión.");
        }
    }

    private void abrirVista(String tipo) {
        try {
            String fxml = switch (tipo) {
                case "Administrador" -> "AdminView.fxml";
                case "Repartidor" -> "RepartidorView.fxml";
                default -> "ClienteView.fxml";
            };

            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/" + fxml
            ));
            Parent root = loader.load();

            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel de " + tipo);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            lblMensaje.setText("❌ Error al abrir la vista de " + tipo);
        }
    }
}
