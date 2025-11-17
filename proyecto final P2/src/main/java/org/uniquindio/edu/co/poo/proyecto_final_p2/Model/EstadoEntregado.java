package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

public class EstadoEntregado implements EstadoEnvio {
    @Override
    public String getNombre() { return "Entregado"; }

    @Override
    public void avanzar(Envio envio) {
        System.out.println("El envío ya fue entregado.");
    }
}
