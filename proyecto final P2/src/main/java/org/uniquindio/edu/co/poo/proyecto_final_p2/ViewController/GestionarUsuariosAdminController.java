package org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.LogisticaFacade;
import org.uniquindio.edu.co.poo.proyecto_final_p2.Model.Usuario;

public class GestionarUsuariosAdminController {

    @FXML private TextField txtNombre, txtCorreo; // correo opcional
    @FXML private PasswordField txtContrasena;
    @FXML private ChoiceBox<String> cbRol;
    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, String> colNombre, colCorreo, colRol;

    private final LogisticaFacade fachada = new LogisticaFacade();

    // 🔹 Se ejecuta al cargar la vista
    @FXML
    public void initialize() {
        cbRol.getItems().addAll("Cliente", "Repartidor", "Administrador");
        cbRol.getSelectionModel().selectFirst();

        // Configurar columnas
        colNombre.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNombreUsuario())
        );

        colCorreo.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getCorreo() == null
                                ? "Sin correo"
                                : data.getValue().getCorreo()
                )
        );

        colRol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getTipo())
        );

        // Cargar usuarios existentes
        actualizarTabla();
    }

    // 🔹 Agregar un nuevo usuario
    @FXML
    private void agregarUsuario() {
        String nombre = txtNombre.getText().trim();
        String contrasena = txtContrasena.getText().trim();
        String rol = cbRol.getValue();
        String correo = txtCorreo.getText().trim();

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos", "Por favor completa todos los campos.");
            return;
        }

        // Si quieres usar el correo:
        if (correo.isEmpty()) {
            correo = "Sin correo";
        }

        // 👉 Usa el constructor con correo
        fachada.agregarUsuario(nombre, contrasena, rol, correo);

        mostrarAlerta(Alert.AlertType.INFORMATION, "Usuario agregado",
                "Se agregó correctamente el usuario \"" + nombre + "\" como " + rol + ".");
        limpiarCampos();
        actualizarTabla();
    }

    // 🔹 Eliminar usuario seleccionado
    @FXML
    private void eliminarUsuario() {
        Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Sin selección", "Selecciona un usuario para eliminar.");
            return;
        }

        fachada.eliminarUsuario(seleccionado.getNombreUsuario());
        mostrarAlerta(Alert.AlertType.INFORMATION, "Usuario eliminado",
                "El usuario \"" + seleccionado.getNombreUsuario() + "\" ha sido eliminado.");
        actualizarTabla();
    }

    // 🔹 Actualizar usuario seleccionado
    @FXML
    private void actualizarUsuario() {
        Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Sin selección", "Selecciona un usuario para actualizar.");
            return;
        }

        String nuevoNombre = txtNombre.getText().trim();
        String nuevaContrasena = txtContrasena.getText().trim();
        String nuevoRol = cbRol.getValue();

        if (nuevoNombre.isEmpty() || nuevaContrasena.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos", "Por favor completa todos los campos.");
            return;
        }

        fachada.actualizarUsuario(seleccionado.getNombreUsuario(), nuevoNombre, nuevaContrasena, nuevoRol);

        mostrarAlerta(Alert.AlertType.INFORMATION, "Usuario actualizado",
                "El usuario \"" + nuevoNombre + "\" ha sido actualizado correctamente.");
        limpiarCampos();
        actualizarTabla();
    }

    // 🔹 Volver al panel del administrador
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

    // 🔹 Refresca los datos en la tabla
    private void actualizarTabla() {
        ObservableList<Usuario> usuarios = fachada.obtenerUsuarios();
        System.out.println("🔎 Usuarios cargados en tabla: " + usuarios.size());
        tablaUsuarios.setItems(usuarios);
        tablaUsuarios.refresh();
    }

    // 🔹 Limpia los campos del formulario
    private void limpiarCampos() {
        txtNombre.clear();
        txtContrasena.clear();
        txtCorreo.clear();
        cbRol.getSelectionModel().selectFirst();
    }

    // 🔹 Muestra alertas reutilizables
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
