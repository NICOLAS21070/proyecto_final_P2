package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;


public abstract class EnvioDecorator extends Envio {

    protected Envio envioDecorado;

    public EnvioDecorator(Envio envioDecorado) {
        super(
                envioDecorado.getIdEnvio(),
                envioDecorado.getRemitente(),   // NUEVO
                new Direccion("Origen", envioDecorado.getOrigen(), "Desconocida", ""),
                new Direccion("Destino", envioDecorado.getDestino(), "Desconocida", ""),
                envioDecorado.getUsuario(),
                envioDecorado.getPaquete(),
                envioDecorado.getCosto(),
                envioDecorado.getEstado().equalsIgnoreCase("Activo")
        );
        this.envioDecorado = envioDecorado;
    }

    // --- Delegaciones comunes ---
    @Override
    public String getIdEnvio() {
        return envioDecorado.getIdEnvio();
    }

    @Override
    public String getOrigen() {
        return envioDecorado.getOrigen();
    }

    @Override
    public String getDestino() {
        return envioDecorado.getDestino();
    }

    @Override
    public Usuario getUsuario() {
        return envioDecorado.getUsuario();
    }

    @Override
    public Paquete getPaquete() {
        return envioDecorado.getPaquete();
    }

    @Override
    public Tarifa getCosto() {
        return envioDecorado.getCosto();
    }

    @Override
    public String getEstado() {
        return envioDecorado.getEstado();
    }

    @Override
    public String getDescripcion() {
        return envioDecorado.getDescripcion();
    }

    // --- Método para obtener el costo numérico directamente (opcional) ---
    public double getValorCosto() {
        return envioDecorado.getCosto() != null ? envioDecorado.getCosto().getBase() : 0.0;
    }
}
