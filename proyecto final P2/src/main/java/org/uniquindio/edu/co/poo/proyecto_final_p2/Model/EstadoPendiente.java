package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

public class EstadoPendiente implements EstadoEnvio {
    @Override
    public String getNombre() { return "Pendiente"; }

    @Override
    public void avanzar(Envio envio) {
        envio.setEstado(new EstadoEnRuta());
    }
}
