package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

/**
 * Fachada principal del sistema logístico.
 * Centraliza la interacción entre controladores y la base de datos.
 * Aplica el patrón Facade para simplificar la comunicación con las capas internas.
 */
public class LogisticaFacade {

    private final BaseDatosLogistica baseDatos;

    // ==========================================================
    // 🔹 Constructor
    // ==========================================================
    public LogisticaFacade() {
        this.baseDatos = BaseDatosLogistica.getInstancia();
        inicializarUsuariosPorDefecto(); // ✅ Carga inicial de usuarios por defecto
    }

    // ==========================================================
    // 🔹 USUARIOS
    // ==========================================================

    /**
     * Valida si un usuario existe con el tipo indicado.
     */
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

    /**
     * Devuelve el usuario correspondiente al nombre y contraseña.
     */
    public Usuario obtenerUsuario(String nombreUsuario, String password) {
        for (Usuario usuario : baseDatos.getListaUsuarios()) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)
                    && usuario.getContrasena().equals(password)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Agrega un nuevo usuario al sistema.
     */
    public void agregarUsuario(String nombreUsuario, String contrasena, String tipoUsuario) {
        Usuario nuevo = new Usuario(nombreUsuario, contrasena, tipoUsuario);
        baseDatos.agregarUsuario(nuevo);
        System.out.println("👤 Usuario agregado: " + nombreUsuario + " (" + tipoUsuario + ")");
    }

    /**
     * Elimina un usuario del sistema según su nombre.
     */
    public void eliminarUsuario(String nombreUsuario) {
        baseDatos.eliminarUsuario(nombreUsuario);
        System.out.println("🗑️ Usuario eliminado: " + nombreUsuario);
    }

    /**
     * Actualiza la información de un usuario existente.
     */
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

    /**
     * Retorna todos los usuarios en un ObservableList para JavaFX.
     */
    public ObservableList<Usuario> obtenerUsuarios() {
        return FXCollections.observableArrayList(baseDatos.getListaUsuarios());
    }

    /**
     * Inicializa usuarios por defecto si no existen.
     */
    private void inicializarUsuariosPorDefecto() {
        if (baseDatos.getListaUsuarios().isEmpty()) {
            baseDatos.agregarUsuario(new Usuario("celis", "123", "Administrador"));
            baseDatos.agregarUsuario(new Usuario("cliente1", "pass", "Cliente"));
            baseDatos.agregarUsuario(new Usuario("repartidor1", "abc", "Repartidor"));
            System.out.println("✅ Usuarios por defecto cargados correctamente.");
        }
    }

    // ==========================================================
    // 🔹 ENVÍOS
    // ==========================================================

    /**
     * Devuelve los envíos en formato observable para JavaFX.
     */
    public ObservableList<Envio> obtenerEnvios() {
        return FXCollections.observableArrayList(baseDatos.getListaEnvios());
    }

    /**
     * Devuelve la lista de envíos del sistema.
     */
    public List<Envio> listarEnvios() {
        return baseDatos.getListaEnvios();
    }

    /**
     * Calcula el costo de un envío según parámetros del cliente.
     */
    public double calcularCosto(double peso, double volumen, String prioridad,
                                boolean seguro, boolean fragil, boolean firma, boolean prioridadExtra) {

        double costoBase = (peso * 5000) + (volumen * 80000);

        // Ajuste por prioridad
        switch (prioridad) {
            case "Urgente" -> costoBase *= 1.25;
            case "Exprés" -> costoBase *= 1.5;
        }

        // Servicios adicionales
        if (seguro) costoBase += 10000;
        if (fragil) costoBase += 7000;
        if (firma) costoBase += 5000;
        if (prioridadExtra) costoBase *= 1.1;

        return costoBase;
    }

    /**
     * Crea un nuevo envío con todos los datos requeridos.
     */
    public Envio crearEnvio(String origen, String destino, double peso, double volumen, String prioridad,
                            boolean seguro, boolean fragil, boolean firma, boolean prioridadExtra,
                            String descripcion, String remitente) {

        double costo = calcularCosto(peso, volumen, prioridad, seguro, fragil, firma, prioridadExtra);

        // Usuario temporal según remitente
        Usuario usuarioTemporal = new Usuario(remitente, "N/A", "Cliente");

        // Crear direcciones reales
        Direccion dirOrigen = new Direccion(origen);
        Direccion dirDestino = new Direccion(destino);

        // Crear paquete real
        Paquete paquete = new Paquete(peso, 30, 25, 20, fragil);

        // Crear tarifa real
        Tarifa tarifa = new Tarifa(costo, 0, 0, 0, 0);

        // CREAR ENVÍO con el nuevo constructor corregido
        Envio envio = new Envio(
                null,          // ID generado automático
                remitente,     // 🔹 NUEVO: remitente del envío
                dirOrigen,
                dirDestino,
                usuarioTemporal,
                paquete,
                tarifa,
                false
        );

        envio.setDescripcion(descripcion);

        baseDatos.agregarEnvio(envio);

        System.out.println("📦 Envío creado correctamente con ID: " + envio.getIdEnvio());
        return envio;

    }

    /**
     * Actualiza el estado de un envío existente.
     */
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

    /**
     * Marca un envío como cancelado.
     */
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

    /**
     * Reporta una incidencia sobre un envío existente.
     */
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

    // ==========================================================
    // 🔹 ESTADÍSTICAS
    // ==========================================================

    /**
     * Retorna un conteo de los envíos agrupados por estado.
     */
    public Map<String, Integer> contarEnviosPorEstado() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Envio envio : baseDatos.getListaEnvios()) {
            String estado = envio.getEstado();
            conteo.put(estado, conteo.getOrDefault(estado, 0) + 1);
        }
        return conteo;
    }

    /**
     * Busca y devuelve un envío por su ID único.
     * Si no se encuentra, retorna null.
     */
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
// LISTAR
    public ObservableList<Repartidor> obtenerRepartidores() {
        return baseDatos.getListaRepartidores();
    }

    // AGREGAR
    public void agregarRepartidor(String usuario, String contrasena,
                                  String nombreReal, String documento,
                                  String telefono, String zonaCobertura,
                                  String estado) {

        Repartidor nuevo = new Repartidor(usuario, contrasena, nombreReal, documento, telefono, zonaCobertura, estado);
        baseDatos.getListaRepartidores().add(nuevo);
    }

    // ELIMINAR (por documento)
    public void eliminarRepartidor(String documento) {
        baseDatos.getListaRepartidores()
                .removeIf(r -> r.getDocumento().equalsIgnoreCase(documento));
    }

    // ACTUALIZAR (buscando por documento original)
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

                // Datos heredados de Usuario
                r.setNombreUsuario(nuevoUsuario);

                r.setContrasena(nuevaContrasena);

                // Campos propios
                r.setDocumento(nuevoDocumento);
                r.setNombreReal(nuevoNombreReal);
                r.setTelefono(nuevoTelefono);
                r.setZonaCobertura(nuevaZona);
                r.setEstadoDisponibilidad(nuevoEstado);

                return;
            }
        }
    }

}
