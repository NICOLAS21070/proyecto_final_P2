package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;


public class SesionUsuario {

    private static Usuario usuarioActual;

    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
