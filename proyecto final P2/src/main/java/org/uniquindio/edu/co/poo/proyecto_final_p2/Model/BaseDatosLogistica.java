package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import java.util.ArrayList;
import java.util.List;

public class BaseDatosLogistica {

    private static BaseDatosLogistica instancia;

    private final List<Usuario> listaUsuarios;
    private final List<Envio> listaEnvios;
    private final List<Incidencia> listaIncidencias;

    private BaseDatosLogistica() {
        this.listaUsuarios = new ArrayList<>();
        this.listaEnvios = new ArrayList<>();
        this.listaIncidencias = new ArrayList<>();
    }

    public static BaseDatosLogistica getInstancia() {
        if (instancia == null) {
            instancia = new BaseDatosLogistica();
        }
        return instancia;
    }

    // ================================
    // 🔹 Métodos de Usuario
    // ================================
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void agregarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
    }

    public void eliminarUsuario(String nombreUsuario) {
        listaUsuarios.removeIf(u -> u.getNombreUsuario().equalsIgnoreCase(nombreUsuario));
    }

    // ================================
    // 🔹 Métodos de Envío
    // ================================
    public List<Envio> getListaEnvios() {
        return listaEnvios;
    }

    public void agregarEnvio(Envio envio) {
        listaEnvios.add(envio);
    }

    // ================================
    // 🔹 Métodos de Incidencias
    // ================================
    public void agregarIncidencia(Incidencia incidencia) {
        listaIncidencias.add(incidencia);
    }

    public List<Incidencia> getListaIncidencias() {
        return listaIncidencias;
    }
}
