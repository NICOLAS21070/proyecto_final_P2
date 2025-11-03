package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

/**
 * Decorador concreto que agrega un costo adicional por seguro
 * al envío base.
 */
public class EnvioConSeguro extends EnvioDecorator {

    private double valorSeguro;

    public EnvioConSeguro(Envio envio, double valorSeguro) {
        super(envio);
        this.valorSeguro = valorSeguro;
    }

    @Override
    public Tarifa getCosto() {
        Tarifa costoOriginal = envioDecorado.getCosto();

        // Sumar el valor del seguro al costo base
        double nuevoValor = costoOriginal.getBase() + valorSeguro;

        // Crear y retornar una nueva tarifa con los mismos valores adicionales
        return new Tarifa(
                nuevoValor,
                costoOriginal.getCostoPorKm(),
                costoOriginal.getCostoPorKg(),
                costoOriginal.getRecargoPrioridad(),
                costoOriginal.getRecargoFragil()
        );
    }

    @Override
    public String getDescripcion() {
        return envioDecorado.getDescripcion() + " + Seguro adicional";
    }
}
