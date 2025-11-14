package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import java.util.UUID;

public class Repartidor extends Usuario {

    private String idRepartidor;
    private String nombreReal;
    private String documento;
    private String telefono;
    private String estadoDisponibilidad; // Activo / Inactivo / En ruta
    private String zonaCobertura;

    // Constructor existente (mantiene compatibilidad con otras partes del sistema)
    public Repartidor(String nombreUsuario,
                      String contrasena,
                      String nombreReal,
                      String documento,
                      String telefono,
                      String zonaCobertura,
                      String estadoDisponibilidad) {

        super(nombreUsuario, contrasena, "Repartidor");
        this.idRepartidor = UUID.randomUUID().toString();
        this.nombreReal = nombreReal;
        this.documento = documento;
        this.telefono = telefono;
        this.zonaCobertura = zonaCobertura;
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    // --- NUEVO: constructor sencillo compatible con tu controller (nombre, documento, telefono)
    // Lo añadimos para que el controller pueda hacer: new Repartidor(nombre, cedula, telefono)
    public Repartidor(String nombre, String documento, String telefono) {
        super(nombre, "", "Repartidor"); // no se toca lógica existente; contraseña vacía por defecto
        this.idRepartidor = UUID.randomUUID().toString();
        this.nombreReal = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.zonaCobertura = "";
        this.estadoDisponibilidad = "Activo";
    }

    // Getters / setters existentes
    public String getIdRepartidor() { return idRepartidor; }

    public String getNombreReal() { return nombreReal; }
    public void setNombreReal(String nombreReal) { this.nombreReal = nombreReal; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEstadoDisponibilidad() { return estadoDisponibilidad; }
    public void setEstadoDisponibilidad(String estadoDisponibilidad) { this.estadoDisponibilidad = estadoDisponibilidad; }

    public String getZonaCobertura() { return zonaCobertura; }
    public void setZonaCobertura(String zonaCobertura) { this.zonaCobertura = zonaCobertura; }

    @Override
    public String toString() {
        return getNombreUsuario() + " - " + zonaCobertura + " (" + estadoDisponibilidad + ")";
    }

    // --- NUEVOS: métodos de compatibilidad esperados por el controller ---
    // controller usa getNombre() y getCedula(); los implementamos delegando a los campos reales

    public String getNombre() {
        return nombreReal;
    }

    public void setNombre(String nombre) {
        this.nombreReal = nombre;
    }

    /**
     * getCedula / setCedula son sinónimos para getDocumento / setDocumento
     * para que no tengas que cambiar el controller.
     */
    public String getCedula() {
        return documento;
    }

    public void setCedula(String cedula) {
        this.documento = cedula;
    }
}
