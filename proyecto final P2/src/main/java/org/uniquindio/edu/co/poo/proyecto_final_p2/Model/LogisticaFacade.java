package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

public class LogisticaFacade {

    private final BaseDatosLogistica baseDatos;

    public LogisticaFacade() {
        this.baseDatos = BaseDatosLogistica.getInstancia();

    }

    // 🔹 LISTAR INCIDENCIAS
    public List<Incidencia> obtenerIncidencias() {
        return baseDatos.getListaIncidencias();
    }

    // ==========================================================
    // 🔹 USUARIOS
    // ==========================================================

    public boolean validarUsuario(String nombreUsuario, String password, String tipoUsuario) {
        for (Usuario usuario : baseDatos.getListaUsuarios()) {
            if (usuario == null) continue;

            String userName = usuario.getNombreUsuario();
            String userPass = usuario.getContrasena();
            String userTipo = usuario.getTipo();

            if (userName != null && userPass != null && userTipo != null) {
                if (userName.equalsIgnoreCase(nombreUsuario)
                        && userPass.equals(password)
                        && userTipo.equalsIgnoreCase(tipoUsuario)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Usuario obtenerUsuario(String nombreUsuario, String password) {
        for (Usuario usuario : baseDatos.getListaUsuarios()) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)
                    && usuario.getContrasena().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

    public void agregarUsuario(String nombreUsuario, String contrasena, String tipoUsuario) {
        Usuario nuevo = new Usuario(nombreUsuario, contrasena, tipoUsuario);
        baseDatos.agregarUsuario(nuevo);
        System.out.println("👤 Usuario agregado: " + nombreUsuario + " (" + tipoUsuario + ")");
    }

    public void eliminarUsuario(String nombreUsuario) {
        baseDatos.eliminarUsuario(nombreUsuario);
        System.out.println("🗑️ Usuario eliminado: " + nombreUsuario);
    }

    public void actualizarUsuario(String nombreViejo, String nuevoNombre, String nuevaContrasena, String nuevoRol) {
        for (Usuario usuario : baseDatos.getListaUsuarios()) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreViejo)) {
                usuario.setNombreUsuario(nuevoNombre);
                usuario.setContrasena(nuevaContrasena);
                usuario.setTipo(nuevoRol);
                System.out.println("✅ Usuario actualizado correctamente: " + nuevoNombre);
                return;
            }
        }
        System.out.println("⚠️ No se encontró el usuario con nombre: " + nombreViejo);
    }

    public ObservableList<Usuario> obtenerUsuarios() {
        return FXCollections.observableArrayList(baseDatos.getListaUsuarios());
    }



    // ==========================================================
    // REGISTRAR CLIENTE
    // ==========================================================

    public boolean registrarCliente(String nombreUsuario, String contrasena) {

        // Verificar si existe
        for (Usuario u : baseDatos.getListaUsuarios()) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                return false;
            }
        }

        // Agregar cliente
        Usuario nuevo = new Usuario(nombreUsuario, contrasena, "Cliente");
        baseDatos.agregarUsuario(nuevo);

        System.out.println("🟢 Cliente registrado: " + nombreUsuario);
        return true;
    }

    // ==========================================================
    // 🔹 ENVÍOS
    // ==========================================================

    public ObservableList<Envio> obtenerEnvios() {
        return FXCollections.observableArrayList(baseDatos.getListaEnvios());
    }

    public List<Envio> listarEnvios() {
        return baseDatos.getListaEnvios();
    }

    public double calcularCosto(double peso, double volumen, String prioridad,
                                boolean seguro, boolean fragil, boolean firma, boolean prioridadExtra) {

        double costoBase = (peso * 5000) + (volumen * 80000);

        switch (prioridad) {
            case "Urgente" -> costoBase *= 1.25;
            case "Exprés" -> costoBase *= 1.5;
        }

        if (seguro) costoBase += 10000;
        if (fragil) costoBase += 7000;
        if (firma) costoBase += 5000;
        if (prioridadExtra) costoBase *= 1.1;

        return costoBase;
    }

    public Envio crearEnvio(String origen, String destino, double peso, double volumen, String prioridad,
                            boolean seguro, boolean fragil, boolean firma, boolean prioridadExtra,
                            String descripcion, String remitente) {

        double costo = calcularCosto(peso, volumen, prioridad, seguro, fragil, firma, prioridadExtra);


        Usuario usuarioTemporal = SesionUsuario.getUsuarioActual();

        Direccion dirOrigen = new Direccion(origen);
        Direccion dirDestino = new Direccion(destino);

        Paquete paquete = new Paquete(peso, 30, 25, 20, fragil);

        Tarifa tarifa = new Tarifa(costo, 0, 0, 0, 0);

        Envio envio = new Envio(
                null,
                usuarioTemporal.getNombreUsuario(),
                dirOrigen,
                dirDestino,
                usuarioTemporal,
                paquete,
                tarifa,
                false
        );

        envio.setDescripcion(descripcion);

        baseDatos.agregarEnvio(envio);

        System.out.println("📦 Envío creado por: " + usuarioTemporal.getNombreUsuario());

        return envio;
    }


    public void actualizarEstadoEnvio(String idEnvio, String nuevoEstado) {
        for (Envio envio : baseDatos.getListaEnvios()) {
            if (envio.getIdEnvio().equals(idEnvio)) {
                envio.setEstado(nuevoEstado);
                System.out.println("✅ Estado del envío " + idEnvio + " actualizado a: " + nuevoEstado);
                return;
            }
        }
        System.out.println("⚠️ No se encontró ningún envío con el ID: " + idEnvio);
    }

    public void cancelarEnvio(String idEnvio) {
        for (Envio envio : baseDatos.getListaEnvios()) {
            if (envio.getIdEnvio().equals(idEnvio)) {
                envio.setEstado("Cancelado");
                System.out.println("❌ Envío " + idEnvio + " ha sido cancelado correctamente.");
                return;
            }
        }
        System.out.println("⚠️ No se encontró ningún envío con el ID: " + idEnvio);
    }

    // ==========================================================
    // 🔹 INCIDENCIAS
    // ==========================================================

    public boolean reportarIncidencia(String idEnvio, String tipoIncidencia, String descripcion) {
        for (Envio envio : baseDatos.getListaEnvios()) {
            if (envio.getIdEnvio().equals(idEnvio)) {
                Incidencia nuevaIncidencia = new Incidencia(envio, tipoIncidencia, descripcion);
                baseDatos.agregarIncidencia(nuevaIncidencia);
                envio.setEstado("Incidencia reportada");
                System.out.println("🟡 Incidencia registrada para el envío ID: " + idEnvio);
                return true;
            }
        }
        System.out.println("⚠️ No se encontró ningún envío con el ID: " + idEnvio);
        return false;
    }

    public Map<String, Integer> contarEnviosPorEstado() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Envio envio : baseDatos.getListaEnvios()) {
            String estado = envio.getEstado();
            conteo.put(estado, conteo.getOrDefault(estado, 0) + 1);
        }
        return conteo;
    }

    public Envio obtenerEnvioPorId(String idEnvio) {
        if (idEnvio == null || idEnvio.isEmpty()) return null;

        for (Envio envio : baseDatos.getListaEnvios()) {
            if (envio != null && idEnvio.equalsIgnoreCase(envio.getIdEnvio())) {
                return envio;
            }
        }
        return null;
    }

    // ==========================================================
    // 🔹 REPARTIDORES
    // ==========================================================

    public ObservableList<Repartidor> obtenerRepartidores() {
        return baseDatos.getListaRepartidores();
    }

    public void agregarRepartidor(String usuario, String contrasena, String nombreReal,
                                  String documento, String telefono, String zona, String estado) {

        Repartidor nuevo = new Repartidor(usuario, contrasena, nombreReal, documento, telefono, estado, zona);

        // Agregar al observable (tabla)
        BaseDatosLogistica.getInstancia().getListaRepartidores().add(nuevo);

        // Agregar a listaUsuarios para login
        BaseDatosLogistica.getInstancia().getListaUsuarios().add(nuevo);
    }

    public void eliminarRepartidor(String documento) {
        baseDatos.getListaRepartidores()
                .removeIf(r -> r.getDocumento().equalsIgnoreCase(documento));
    }

    public void actualizarRepartidor(String documentoOriginal,
                                     String nuevoUsuario,
                                     String nuevaContrasena,
                                     String nuevoNombreReal,
                                     String nuevoDocumento,
                                     String nuevoTelefono,
                                     String nuevaZona,
                                     String nuevoEstado) {

        for (Repartidor r : baseDatos.getListaRepartidores()) {
            if (r.getDocumento().equalsIgnoreCase(documentoOriginal)) {

                r.setNombreUsuario(nuevoUsuario);
                r.setContrasena(nuevaContrasena);

                r.setDocumento(nuevoDocumento);
                r.setNombreReal(nuevoNombreReal);
                r.setTelefono(nuevoTelefono);
                r.setZonaCobertura(nuevaZona);
                r.setEstadoDisponibilidad(nuevoEstado);

                return;
            }
        }
    }

    public void agregarUsuario(String nombreUsuario, String contrasena, String tipoUsuario, String correo) {
        Usuario nuevo = new Usuario(nombreUsuario, contrasena, tipoUsuario, correo);
        baseDatos.agregarUsuario(nuevo);
    }

}
