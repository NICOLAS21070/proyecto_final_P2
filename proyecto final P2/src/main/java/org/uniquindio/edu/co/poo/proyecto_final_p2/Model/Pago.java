package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

/**
 * Representa el pago asociado a un envío dentro del sistema logístico.
 * Contiene la información del cliente, destino, monto y estado del pago.
 */
public class Pago {

    // =====================
    // 🔹 Atributos
    // =====================
    private String idEnvio;
    private String nombreCliente;
    private String destino;
    private double monto;
    private String estadoPago; // "Pagado" o "Pendiente"

    // =====================
    // 🔹 Constructor completo
    // =====================
    public Pago(String idEnvio, String nombreCliente, String destino, double monto, String estadoPago) {
        this.idEnvio = idEnvio;
        this.nombreCliente = nombreCliente;
        this.destino = destino;
        this.monto = monto;
        this.estadoPago = estadoPago;
    }

    // =====================
    // 🔹 Constructor simplificado (para BaseDatosLogistica)
    // =====================
    public Pago(double monto, String metodoPago, boolean pagado) {
        this.idEnvio = "Sin ID";
        this.nombreCliente = "Desconocido";
        this.destino = "N/A";
        this.monto = monto;
        this.estadoPago = pagado ? "Pagado" : "Pendiente";
    }

    // =====================
    // 🔹 Getters y Setters
    // =====================
    public String getIdEnvio() { return idEnvio; }
    public void setIdEnvio(String idEnvio) { this.idEnvio = idEnvio; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getEstadoPago() { return estadoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }

    // =====================
    // 🔹 Métodos útiles
    // =====================
    @Override
    public String toString() {
        return String.format(
                "Pago [idEnvio=%s, cliente=%s, destino=%s, monto=%.2f, estado=%s]",
                idEnvio, nombreCliente, destino, monto, estadoPago
        );
    }
}
