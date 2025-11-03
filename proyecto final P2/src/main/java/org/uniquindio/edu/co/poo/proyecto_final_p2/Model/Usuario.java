package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Usuario {

    private final String idUsuario; // ID único
    private String nombreCompleto;
    private String correo;
    private String telefono;
    private final List<Direccion> direcciones; // Lista de direcciones asociadas

    // ✅ Campos adicionales para el login
    private String nombreUsuario;
    private String contrasena;
    private String tipo; // Ejemplo: "Cliente", "Administrador", "Repartidor"

    public Usuario(String nombreUsuario, String contrasena, String tipo) {
        this.idUsuario = UUID.randomUUID().toString();
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.direcciones = new ArrayList<>();
    }

    // -------------------- GETTERS Y SETTERS -------------------- //
    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    // -------------------- MÉTODOS DE GESTIÓN -------------------- //
    public void agregarDireccion(Direccion direccion) {
        if (direccion != null && !direcciones.contains(direccion)) {
            direcciones.add(direccion);
        }
    }

    public void eliminarDireccion(Direccion direccion) {
        direcciones.remove(direccion);
    }

    // ✅ Getters y Setters para login
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nombreCompleto + " (" + correo + ")";
    }
}