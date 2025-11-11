package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Usuario;

public class GestionarUsuariosAdminController {

    @FXML private TextField txtNombre, txtCorreo;
    @FXML private PasswordField txtContrasena;
    @FXML private ChoiceBox<String> cbRol;
    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, String> colNombre, colCorreo, colRol;

    private LogisticaFacade fachada = new LogisticaFacade();

    @FXML
    public void initialize() {
        System.out.println("✅ Vista de gestión de usuarios cargada.");
        cbRol.getItems().addAll("Cliente", "Repartidor", "Administrador");
        cbRol.getSelectionModel().selectFirst();
    }

    @FXML
    private void agregarUsuario() {
        System.out.println("🟢 Usuario agregado: " + txtNombre.getText());
        // Lógica para agregar usuario con fachada
    }

    @FXML
    private void actualizarUsuario() {
        System.out.println("🟡 Usuario actualizado: " + txtNombre.getText());
    }

    @FXML
    private void eliminarUsuario() {
        System.out.println("🔴 Usuario eliminado: " + txtNombre.getText());
    }

    @FXML
    private void volverAlPanelAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/org/uniquindio/edu/co/poo/proyecto_final_p2/view/AdminView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) txtNombre.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panel del Administrador");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
