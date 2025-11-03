package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;


import java.time.LocalDate;
import java.util.UUID;

public class Incidencia {

    private String idIncidencia;
    private Envio envio;
    private String descripcion;
    private LocalDate fechaRegistro;
    private String tipo; // Ejemplo: Retraso, Dañado, Dirección Incorrecta
    private String estado; // Abierta / Cerrada

    public Incidencia(Envio envio, String tipo, String descripcion) {
        this.idIncidencia = UUID.randomUUID().toString();
        this.envio = envio;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaRegistro = LocalDate.now();
        this.estado = "Abierta";
    }

    public String getIdIncidencia() { return idIncidencia; }
    public Envio getEnvio() { return envio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getEstado() { return estado; }
    public void cerrarIncidencia() { this.estado = "Cerrada"; }

    @Override
    public String toString() {
        return String.format("Incidencia [%s - %s] %s", tipo, estado, descripcion);
    }
}
