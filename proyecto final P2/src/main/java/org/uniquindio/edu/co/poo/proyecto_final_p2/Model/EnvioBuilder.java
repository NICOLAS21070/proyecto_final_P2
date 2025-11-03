package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import java.util.UUID;

/**
 * Patrón Builder aplicado a la clase Envio.
 * Permite construir un envío paso a paso antes de crearlo definitivamente.
 */
public class EnvioBuilder {

    private Usuario usuario;
    private Direccion origen;
    private Direccion destino;
    private Paquete paquete;
    private Tarifa tarifa;
    private boolean prioridad;

    public EnvioBuilder setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public EnvioBuilder setOrigen(Direccion origen) {
        this.origen = origen;
        return this;
    }

    public EnvioBuilder setDestino(Direccion destino) {
        this.destino = destino;
        return this;
    }

    public EnvioBuilder setPaquete(Paquete paquete) {
        this.paquete = paquete;
        return this;
    }

    public EnvioBuilder setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
        return this;
    }

    public EnvioBuilder setPrioridad(boolean prioridad) {
        this.prioridad = prioridad;
        return this;
    }

    /**
     * Construye finalmente el objeto Envio.
     */
    public Envio build() {
        if (usuario == null || origen == null || destino == null || paquete == null || tarifa == null) {
            throw new IllegalStateException("Faltan datos para crear el envío");
        }

        // Generar un ID único para el envío
        String idEnvio = UUID.randomUUID().toString();

        // Crear el envío con todos los parámetros
        return new Envio(idEnvio, origen, destino, usuario, paquete, tarifa, prioridad);
    }
}