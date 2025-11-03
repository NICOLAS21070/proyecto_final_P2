package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import java.util.UUID;

public class Repartidor extends Usuario {

    private String idRepartidor;
    private String documento;
    private String telefono;
    private String estadoDisponibilidad; // Activo / Inactivo / En ruta
    private String zonaCobertura;

    public Repartidor(String nombreUsuario, String contrasena, String nombre, String documento, String telefono, String zonaCobertura) {
        super(nombreUsuario, contrasena, "Repartidor"); // 👈 Llamada al constructor de Usuario
        this.idRepartidor = UUID.randomUUID().toString();
        this.documento = documento;
        this.telefono = telefono;
        this.zonaCobertura = zonaCobertura;
        this.estadoDisponibilidad = "Activo";
    }

    public String getIdRepartidor() { return idRepartidor; }
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
}
