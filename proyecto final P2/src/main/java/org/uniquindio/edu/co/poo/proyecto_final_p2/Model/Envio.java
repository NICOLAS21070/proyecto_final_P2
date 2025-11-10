package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que representa un Envío dentro del sistema logístico.
 * Compatible con JavaFX TableView.
 */
public class Envio {

    private static final AtomicInteger contador = new AtomicInteger(1);

    private final StringProperty idEnvio;
    private final StringProperty origen;
    private final StringProperty destino;
    private final StringProperty estado;
    private final StringProperty repartidor;

    private String incidencia;
    private Usuario usuario;
    private Paquete paquete;
    private Tarifa costo;
    private String descripcion;

    // CONSTRUCTOR
    public Envio(String idEnvio, Direccion origen, Direccion destino,
                 Usuario usuario, Paquete paquete, Tarifa costo, boolean estado) {

        String idGenerado = (idEnvio == null || idEnvio.isBlank())
                ? String.format("ENV-%04d", contador.getAndIncrement())
                : idEnvio;

        this.idEnvio = new SimpleStringProperty(idGenerado);
        this.origen = new SimpleStringProperty(origen != null ? origen.toString() : "Sin origen");
        this.destino = new SimpleStringProperty(destino != null ? destino.toString() : "Sin destino");
        this.estado = new SimpleStringProperty(estado ? "Activo" : "Inactivo");
        this.repartidor = new SimpleStringProperty("Sin asignar");
        this.usuario = usuario;
        this.paquete = paquete;
        this.costo = costo;
        this.descripcion = "Envío estándar";
    }

    // --- Getters ---
    public String getIdEnvio() { return idEnvio.get(); }
    public String getOrigen() { return origen.get(); }
    public String getDestino() { return destino.get(); }
    public String getEstado() { return estado.get(); }
    public String getRepartidor() { return repartidor.get(); }
    public Usuario getUsuario() { return usuario; }
    public Paquete getPaquete() { return paquete; }
    public Tarifa getCosto() { return costo; }
    public String getDescripcion() { return descripcion; }
    public String getIncidencia() { return incidencia; }

    public double getCostoTotal() {
        return (costo != null) ? costo.getBase() : 0.0;
    }

    // --- Setters ---
    public void setOrigen(String origen) { this.origen.set(origen); }
    public void setDestino(String destino) { this.destino.set(destino); }
    public void setEstado(String estado) { this.estado.set(estado); }
    public void setRepartidor(String repartidor) { this.repartidor.set(repartidor); }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setPaquete(Paquete paquete) { this.paquete = paquete; }
    public void setCosto(Tarifa costo) { this.costo = costo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setIncidencia(String incidencia) { this.incidencia = incidencia; }

    // --- Propiedades para TableView ---
    public StringProperty idEnvioProperty() { return idEnvio; }
    public StringProperty origenProperty() { return origen; }
    public StringProperty destinoProperty() { return destino; }
    public StringProperty estadoProperty() { return estado; }
    public StringProperty repartidorProperty() { return repartidor; }

    @Override
    public String toString() {
        return "Envío{" +
                "id='" + getIdEnvio() + '\'' +
                ", origen='" + getOrigen() + '\'' +
                ", destino='" + getDestino() + '\'' +
                ", estado='" + getEstado() + '\'' +
                ", repartidor='" + getRepartidor() + '\'' +
                ", costo=" + getCostoTotal() +
                ", usuario='" + (usuario != null ? usuario.getNombreUsuario() : "N/A") + '\'' +
                '}';
    }
}
