package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

import java.util.UUID;


public class Direccion {

    private String idDireccion;
    private String alias;
    private String calle;
    private String ciudad;
    private String coordenadas; // Simuladas o generadas manualmente


    public Direccion(String alias, String calle, String ciudad, String coordenadas) {
        this.idDireccion = UUID.randomUUID().toString();
        this.alias = alias;
        this.calle = calle;
        this.ciudad = ciudad;
        this.coordenadas = coordenadas;
    }

    public Direccion(String detalle) {
        this.idDireccion = UUID.randomUUID().toString();
        this.alias = detalle;
        this.calle = "No especificada";
        this.ciudad = "No definida";
        this.coordenadas = "0,0";
    }

    // --- Getters y Setters ---
    public String getIdDireccion() {
        return idDireccion;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    // --- Representación legible ---
    @Override
    public String toString() {
        return alias + ": " + calle + ", " + ciudad + " (" + coordenadas + ")";
    }

    public String getNombreLugar() {
        // Retorna un nombre legible y corto para mostrar en tablas o etiquetas
        return alias + " - " + ciudad;
    }
}
