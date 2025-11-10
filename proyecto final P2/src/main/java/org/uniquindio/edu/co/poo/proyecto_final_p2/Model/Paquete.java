package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

public class Paquete {

    private String nombre;
    private double peso;
    private double largo;
    private double ancho;
    private double alto;
    private boolean fragil;

    // 🟩 Nuevo campo
    private String tipo;

    // --- Constructor principal ---
    public Paquete(double peso, double largo, double ancho, double alto, boolean fragil) {
        this.nombre = "Paquete sin nombre";
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.fragil = fragil;
        this.tipo = "Paquete"; // valor por defecto
    }

    // --- Constructor alternativo ---
    public Paquete(String nombre, double peso, boolean fragil) {
        this.nombre = nombre;
        this.peso = peso;
        this.largo = 0;
        this.ancho = 0;
        this.alto = 0;
        this.fragil = fragil;
        this.tipo = "Paquete";
    }

    // --- Getters y Setters ---
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getLargo() { return largo; }
    public void setLargo(double largo) { this.largo = largo; }

    public double getAncho() { return ancho; }
    public void setAncho(double ancho) { this.ancho = ancho; }

    public double getAlto() { return alto; }
    public void setAlto(double alto) { this.alto = alto; }

    public boolean isFragil() { return fragil; }
    public void setFragil(boolean fragil) { this.fragil = fragil; }

    // 🟩 Nuevo getter y setter
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double calcularVolumen() {
        return (largo * ancho * alto) / 1_000_000.0;
    }

    @Override
    public String toString() {
        return String.format("%s [%.2f kg, %.2f×%.2f×%.2f cm, %s, Tipo: %s]",
                nombre, peso, largo, ancho, alto, fragil ? "Frágil" : "Normal", tipo);
    }
}
