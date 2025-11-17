package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Envio implements EnvioObservable {

    private static final AtomicInteger contador = new AtomicInteger(1);

    // ⭐ CONSTANTES DE ESTADO
    public static final String ESTADO_PENDIENTE = "Pendiente";
    public static final String ESTADO_EN_RUTA = "En ruta";
    public static final String ESTADO_ENTREGADO = "Entregado";
    public static final String ESTADO_CANCELADO = "Cancelado";
    public static final String ESTADO_INCIDENCIA = "Incidencia";


    private final StringProperty idEnvio;
    private final StringProperty remitente;
    private final StringProperty origen;
    private final StringProperty destino;
    private final StringProperty estado;
    private final StringProperty repartidor;

    private String incidencia;
    private Usuario usuario;
    private Paquete paquete;
    private Tarifa costo;
    private String descripcion;



    public Envio(String idEnvio, String remitente, Direccion origen, Direccion destino,
                 Usuario usuario, Paquete paquete, Tarifa costo, boolean estadoIgnorado) {

        String idGenerado = (idEnvio == null || idEnvio.isBlank())
                ? String.format("ENV-%04d", contador.getAndIncrement())
                : idEnvio;

        this.idEnvio = new SimpleStringProperty(idGenerado);
        this.remitente = new SimpleStringProperty(remitente != null ? remitente : "No registrado");
        this.origen = new SimpleStringProperty(origen != null ? origen.toString() : "Sin origen");
        this.destino = new SimpleStringProperty(destino != null ? destino.toString() : "Sin destino");

        // Estado inicial siempre debe ser Pendiente
        this.estado = new SimpleStringProperty("Pendiente");

        this.repartidor = new SimpleStringProperty("Sin asignar");

        this.usuario = usuario;
        this.paquete = paquete;
        this.costo = costo;
        this.descripcion = "Envío estándar";
    }

    // Getters, Setters y Properties (sin cambios)
    public String getIdEnvio() { return idEnvio.get(); }
    public String getRemitente() { return remitente.get(); }
    public String getOrigen() { return origen.get(); }
    public String getDestino() { return destino.get(); }
    public String getEstado() { return estado.get(); }
    public String getRepartidor() { return repartidor.get(); }
    public Usuario getUsuario() { return usuario; }
    public Paquete getPaquete() { return paquete; }
    public Tarifa getCosto() { return costo; }
    public String getDescripcion() { return descripcion; }
    public String getIncidencia() { return incidencia; }
    public double getCostoTotal() {return (costo != null) ? costo.getBase() : 0.0;}

    public void setEstado(String estado) { this.estado.set(estado); }
    public void setRepartidor(String repartidor) { this.repartidor.set(repartidor); }
    public void setIncidencia(String incidencia) { this.incidencia = incidencia; }
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public void setDestino(String destino) {this.destino.set(destino);}
    public void setDestino(Direccion destino) {
        this.destino.set(destino != null ? destino.toString() : "Sin destino");
    }


    public StringProperty idEnvioProperty() { return idEnvio; }
    public StringProperty remitenteProperty() { return remitente; }
    public StringProperty origenProperty() { return origen; }
    public StringProperty destinoProperty() { return destino; }
    public StringProperty estadoProperty() { return estado; }
    public StringProperty repartidorProperty() { return repartidor; }

    private EstadoEnvio estadoActual;

    public void setEstado(EstadoEnvio estado) {
        this.estadoActual = estadoActual;
    }

    public String getEstadoNombre() {
        return estadoActual.getNombre();
    }

    public void avanzarEstado() {
        estadoActual.avanzar(this);
    }

    private List<EnvioObserver> observers = new ArrayList<>();

    @Override
    public void agregarObserver(EnvioObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notificarObservers() {
        for (EnvioObserver obs : observers) {
            obs.onEnvioActualizado(this);
        }
    }
}
