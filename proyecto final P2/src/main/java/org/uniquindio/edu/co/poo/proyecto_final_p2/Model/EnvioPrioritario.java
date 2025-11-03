package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

/**
 * Decorador concreto que representa un envío prioritario.
 * Añade un recargo al campo recargoPrioridad de la Tarifa base,
 * devolviendo una nueva Tarifa que refleja el costo con prioridad.
 */
public class EnvioPrioritario extends EnvioDecorator {

    private final double recargoPrioridad;

    public EnvioPrioritario(Envio envio) {
        super(envio);
        this.recargoPrioridad = 15000.0; // ajusta el valor según necesites
    }

    @Override
    public Tarifa getCosto() {
        Tarifa tarifaBase = envioDecorado.getCosto();
        if (tarifaBase == null) {
            // si no hay tarifa original, devolvemos null o una tarifa por defecto
            return null;
        }

        // Construimos una nueva Tarifa basada en la original,
        // pero aumentando el recargoPrioridad (sin modificar la original).
        Tarifa tarifaPrioritaria = new Tarifa(
                tarifaBase.getBase(),
                tarifaBase.getCostoPorKm(),
                tarifaBase.getCostoPorKg(),
                tarifaBase.getRecargoPrioridad() + this.recargoPrioridad,
                tarifaBase.getRecargoFragil()
        );

        return tarifaPrioritaria;
    }

    @Override
    public String getDescripcion() {
        return envioDecorado.getDescripcion() + " + Prioritario";
    }
}
