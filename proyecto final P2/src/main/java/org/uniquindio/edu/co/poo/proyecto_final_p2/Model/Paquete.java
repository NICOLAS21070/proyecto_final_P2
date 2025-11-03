package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

/**
 * Representa un paquete dentro del sistema logístico.
 * Contiene datos físicos y su condición de fragilidad.
 */
public class Paquete {

    private String nombre;
    private double peso;   // en kg
    private double largo;  // en cm
    private double ancho;  // en cm
    private double alto;   // en cm
    private boolean fragil;

    // --- Constructor principal (dimensiones completas) ---
    public Paquete(double peso, double largo, double ancho, double alto, boolean fragil) {
        this.nombre = "Paquete sin nombre";
        this.peso = peso;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
        this.fragil = fragil;
    }

    // --- Constructor alternativo (usado por LogisticaFacade) ---
    public Paquete(String nombre, double peso, boolean fragil) {
        this.nombre = nombre;
        this.peso = peso;
        this.largo = 0;
        this.ancho = 0;
        this.alto = 0;
        this.fragil = fragil;
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

    // --- Métodos útiles ---
    /**
     * Calcula el volumen del paquete en metros cúbicos.
     */
    public double calcularVolumen() {
        return (largo * ancho * alto) / 1_000_000.0; // cm³ → m³
    }

    @Override
    public String toString() {
        return String.format("%s [%.2f kg, %.2f×%.2f×%.2f cm, %s]",
                nombre, peso, largo, ancho, alto, fragil ? "Frágil" : "Normal");
    }
}
