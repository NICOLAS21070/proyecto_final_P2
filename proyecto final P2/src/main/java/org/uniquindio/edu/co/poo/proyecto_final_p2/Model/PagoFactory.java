package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;


import java.time.LocalDateTime;
import java.util.Random;

public class PagoFactory {

    /**
     * Crea un pago simulado y decide aleatoriamente si fue aprobado o no.
     */
    public static Pago crearPago(double monto) {
        Random random = new Random();
        boolean aprobado = random.nextBoolean();
        String metodo = aprobado ? "Tarjeta" : "Efectivo";
        return new Pago(monto, metodo, aprobado);
    }

    /**
     * Crea un pago aprobado manualmente (por ejemplo, validado por un operador).
     */
    public static Pago crearPagoAprobado(double monto, String metodo) {
        return new Pago(monto, metodo, true);
    }

    /**
     * Crea un pago rechazado (para pruebas o simulaciones).
     */
    public static Pago crearPagoRechazado(double monto, String metodo) {
        return new Pago(monto, metodo, false);
    }
}
