package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

public class Usuario {

    private String nombreUsuario;
    private String contrasena;
    private String tipo;

    public Usuario(String nombreUsuario, String contrasena, String tipo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
