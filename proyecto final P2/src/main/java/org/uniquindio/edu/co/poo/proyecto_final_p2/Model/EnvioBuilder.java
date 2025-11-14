package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import java.util.UUID;

public class EnvioBuilder {

    private Usuario usuario;
    private Direccion origen;
    private Direccion destino;
    private Paquete paquete;
    private Tarifa tarifa;
    private boolean prioridad;
    private String remitente;

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

    public EnvioBuilder setRemitente(String remitente) {
        this.remitente = remitente;
        return this;
    }

    public Envio build() {

        if (usuario == null || origen == null || destino == null || paquete == null || tarifa == null || remitente == null) {
            throw new IllegalStateException("Faltan datos para crear el envío");
        }

        String idEnvio = UUID.randomUUID().toString();

        return new Envio(
                idEnvio,
                remitente,
                origen,
                destino,
                usuario,
                paquete,
                tarifa,
                prioridad
        );
    }
}
