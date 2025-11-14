package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

public class EnvioFactory {

    /**
     * Crea un envío básico usando los datos proporcionados.
     */
    public static Envio crearEnvio(String remitente, Usuario usuario,
                                   Direccion origen, Direccion destino,
                                   Paquete paquete, Tarifa tarifa, boolean activo) {

        String idEnvio = "ENV-" + System.currentTimeMillis();

        return new Envio(
                idEnvio,
                remitente,
                origen,
                destino,
                usuario,
                paquete,
                tarifa,
                activo
        );
    }

    /**
     * Crea un envío prioritario con recargo.
     */
    public static Envio crearEnvioPrioritario(String remitente, Usuario usuario,
                                              Direccion origen, Direccion destino,
                                              Paquete paquete, Tarifa tarifa) {

        String idEnvio = "ENV-PRIOR-" + System.currentTimeMillis();

        Envio envio = new Envio(
                idEnvio,
                remitente,
                origen,
                destino,
                usuario,
                paquete,
                tarifa,
                true
        );

        envio.setDescripcion("Envío prioritario con atención urgente");
        return envio;
    }
}
