package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Clase Singleton que simula la base de datos en memoria.
 * Contiene todas las colecciones principales del sistema.
 */
public class BaseDatosLogistica {

    // 🔹 Instancia única (patrón Singleton)
    private static BaseDatosLogistica instancia;

    // 🔹 Listas simulando tablas
    private List<Usuario> listaUsuarios;
    private List<Repartidor> listaRepartidores;
    private List<Envio> listaEnvios;
    private List<Pago> listaPagos;
    private List<Incidencia> listaIncidencias;

    // ==============================
    // 🔒 CONSTRUCTOR PRIVADO
    // ==============================
    private BaseDatosLogistica() {
        listaUsuarios = new ArrayList<>();
        listaRepartidores = new ArrayList<>();
        listaEnvios = new ArrayList<>();
        listaPagos = new ArrayList<>();
        listaIncidencias = new ArrayList<>();

        inicializarDatosPrueba();
    }

    // ==============================
    // ⚙️ SINGLETON
    // ==============================
    public static BaseDatosLogistica getInstancia() {
        if (instancia == null) {
            instancia = new BaseDatosLogistica();
        }
        return instancia;
    }

    // ==============================
    // ✅ MÉTODOS GETTERS
    // ==============================
    public List<Usuario> getListaUsuarios() { return listaUsuarios; }
    public List<Repartidor> getListaRepartidores() { return listaRepartidores; }
    public List<Envio> getListaEnvios() { return listaEnvios; }
    public List<Pago> getListaPagos() { return listaPagos; }
    public List<Incidencia> getListaIncidencias() { return listaIncidencias; }

    // ==============================
    // ✅ MÉTODOS DE GESTIÓN
    // ==============================
    public void agregarUsuario(Usuario usuario) { listaUsuarios.add(usuario); }
    public void agregarRepartidor(Repartidor repartidor) { listaRepartidores.add(repartidor); }
    public void agregarEnvio(Envio envio) { listaEnvios.add(envio); }
    public void agregarPago(Pago pago) { listaPagos.add(pago); }
    public void agregarIncidencia(Incidencia incidencia) { listaIncidencias.add(incidencia); }

    // ==============================
    // 🔁 MÉTODO COMPATIBLE CON getRepositorioEnvios()
    // ==============================
    /**
     * Este método mantiene compatibilidad con controladores que
     * esperan un “repositorio de envíos”.
     * Devuelve una referencia a la lista actual de envíos.
     */
    public BaseDatosLogistica getRepositorioEnvios() {
        return this; // 🔄 Retorna la misma instancia para permitir .listarEnvios()
    }

    /**
     * Devuelve la lista de envíos actual.
     */
    public List<Envio> listarEnvios() {
        return listaEnvios;
    }

    // ==============================
    // 🧪 DATOS DE PRUEBA
    // ==============================
    private void inicializarDatosPrueba() {
        Direccion d1 = new Direccion("Casa", "Calle 10 #12-34", "Armenia", "4.5333,-75.6811");
        Direccion d2 = new Direccion("Oficina", "Carrera 14 #8-20", "Armenia", "4.5370,-75.6745");

        // 👑 ADMIN
        Usuario admin = new Usuario("admin", "admin123", "Administrador");
        admin.agregarDireccion(d1);
        listaUsuarios.add(admin);

        // 🚚 REPARTIDOR
        Usuario repartidorUser = new Usuario("repartidor1", "1234", "Repartidor");
        repartidorUser.agregarDireccion(d1);
        listaUsuarios.add(repartidorUser);

        Repartidor repartidor = new Repartidor("repartidor1", "1234", "Carlos Gómez", "1098765432", "3120001111", "Centro");
        listaRepartidores.add(repartidor);
        listaUsuarios.add(repartidor);

        // 👤 CLIENTE
        Usuario cliente = new Usuario("Samuel", "123", "Cliente");
        cliente.agregarDireccion(d2);
        listaUsuarios.add(cliente);

        // 📦 TARIFA, PAQUETE Y ENVÍO
        Tarifa tarifa = new Tarifa(3000, 500, 100, 2000, 1500);
        Paquete paquete = new Paquete(2.5, 30, 25, 20, false);

        Envio envio = new Envio(UUID.randomUUID().toString(), d1, d2, cliente, paquete, tarifa, false);
        envio.setRepartidor(repartidor.getNombreCompleto());
        listaEnvios.add(envio);

        // 💳 PAGO
        Pago pago = new Pago(tarifa.getCostoTotal(paquete, false), "Tarjeta", true);
        listaPagos.add(pago);
    }
}
