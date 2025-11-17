package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

public class Usuario {

    private String nombreUsuario;
    private String contrasena;
    private String tipo;
    private String correo;

    // 👉 Constructor viejo (sin correo)
    public Usuario(String nombreUsuario, String contrasena, String tipo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.correo = "Sin correo"; // valor por defecto
    }

    // 👉 Constructor nuevo (con correo)
    public Usuario(String nombreUsuario, String contrasena, String tipo, String correo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.correo = correo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
