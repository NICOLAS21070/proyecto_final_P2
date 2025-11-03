package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

public class EnvioFactory {

    /**
     * Crea un envío básico usando los datos proporcionados.
     */
    public static Envio crearEnvio(Usuario usuario, Direccion origen, Direccion destino,
                                   Paquete paquete, Tarifa tarifa, boolean activo) {

        // Generar un ID único para el envío
        String idEnvio = "ENV-" + System.currentTimeMillis();

        // Crear el nuevo envío con el orden correcto de parámetros
        return new Envio(idEnvio, origen, destino, usuario, paquete, tarifa, activo);
    }

    /**
     * Crea un envío rápido con prioridad y recargo de urgencia.
     */
    public static Envio crearEnvioPrioritario(Usuario usuario, Direccion origen, Direccion destino,
                                              Paquete paquete, Tarifa tarifa) {

        String idEnvio = "ENV-PRIOR-" + System.currentTimeMillis();

        Envio envio = new Envio(idEnvio, origen, destino, usuario, paquete, tarifa, true);
        envio.setDescripcion("Envío prioritario con atención urgente");
        return envio;
    }
}
