package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

public class EstadoEnRuta implements EstadoEnvio {
    @Override
    public String getNombre() { return "En ruta"; }

    @Override
    public void avanzar(Envio envio) {
        envio.setEstado(new EstadoEntregado());
    }
}

