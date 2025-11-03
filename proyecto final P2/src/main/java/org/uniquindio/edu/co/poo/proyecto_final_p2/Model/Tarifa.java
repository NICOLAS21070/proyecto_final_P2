package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;


public class Tarifa {

    private double base;          // valor base del envío
    private double costoPorKm;    // costo por kilómetro
    private double costoPorKg;    // costo por kilogramo
    private double recargoPrioridad; // recargo si es prioridad
    private double recargoFragil;    // recargo si el paquete es frágil

    public Tarifa(double base, double costoPorKm, double costoPorKg, double recargoPrioridad, double recargoFragil) {
        this.base = base;
        this.costoPorKm = costoPorKm;
        this.costoPorKg = costoPorKg;
        this.recargoPrioridad = recargoPrioridad;
        this.recargoFragil = recargoFragil;
    }

    public double calcularCosto(double distanciaKm, Paquete paquete, boolean prioridad) {
        double costo = base + (costoPorKm * distanciaKm) + (costoPorKg * paquete.getPeso());
        if (prioridad) costo += recargoPrioridad;
        if (paquete.isFragil()) costo += recargoFragil;
        return costo;
    }

    public double getCostoTotal(Paquete paquete, boolean prioridad) {
        // Puedes definir una distancia estándar (por ejemplo, 10 km)
        double distancia = 10;
        return calcularCosto(distancia, paquete, prioridad);
    }

    public double getBase() { return base; }
    public void setBase(double base) { this.base = base; }

    public double getCostoPorKm() { return costoPorKm; }
    public void setCostoPorKm(double costoPorKm) { this.costoPorKm = costoPorKm; }

    public double getCostoPorKg() { return costoPorKg; }
    public void setCostoPorKg(double costoPorKg) { this.costoPorKg = costoPorKg; }

    public double getRecargoPrioridad() { return recargoPrioridad; }
    public void setRecargoPrioridad(double recargoPrioridad) { this.recargoPrioridad = recargoPrioridad; }

    public double getRecargoFragil() { return recargoFragil; }
    public void setRecargoFragil(double recargoFragil) { this.recargoFragil = recargoFragil; }

    @Override
    public String toString() {
        return String.format("Tarifa base: %.2f, Km: %.2f, Kg: %.2f", base, costoPorKm, costoPorKg);
    }

}
