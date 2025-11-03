package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;


import java.time.LocalDate;
import java.util.UUID;

public class Pago {

    private String idPago;
    private double monto;
    private LocalDate fechaPago;
    private String metodoPago; // Simulado (Ej: Tarjeta, PSE, Efectivo)
    private String resultado;  // Aprobado / Rechazado

    public Pago(double monto, String metodoPago, boolean aprobado) {
        this.idPago = UUID.randomUUID().toString();
        this.monto = monto;
        this.fechaPago = LocalDate.now();
        this.metodoPago = metodoPago;
        this.resultado = aprobado ? "Aprobado" : "Rechazado";
    }

    public String getIdPago() { return idPago; }
    public double getMonto() { return monto; }
    public LocalDate getFechaPago() { return fechaPago; }
    public String getMetodoPago() { return metodoPago; }
    public String getResultado() { return resultado; }

    @Override
    public String toString() {
        return String.format("Pago %s - $%.2f [%s]", resultado, monto, metodoPago);
    }
}
