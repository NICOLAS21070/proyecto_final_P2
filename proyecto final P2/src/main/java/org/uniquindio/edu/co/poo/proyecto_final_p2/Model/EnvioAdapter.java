package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

/**
 * Patrón Adapter:
 * Adapta un objeto Envio a un formato legible o compatible con otro sistema,
 * como por ejemplo un módulo externo o exportación de datos.
 */
public class EnvioAdapter {

    private Envio envio;

    public EnvioAdapter(Envio envio) {
        this.envio = envio;
    }

    /**
     * Devuelve un formato de texto tipo JSON simplificado,
     * simulando compatibilidad con un sistema externo.
     */
    public String getFormatoExterno() {
        return String.format(
                "{ \"codigo\": \"%s\", \"cliente\": \"%s\", \"destino\": \"%s\", \"estado\": \"%s\" }",
                envio.getIdEnvio(),
                envio.getUsuario().getNombreUsuario(), // ✅ corregido
                envio.getDestino(),
                envio.getEstado()
        );
    }

    /**
     * Exporta a formato CSV simulado.
     */
    public String getFormatoCSV() {
        return envio.getIdEnvio() + ";" +
                envio.getUsuario().getNombreUsuario() + ";" + // ✅ corregido
                envio.getDestino() + ";" +
                envio.getEstado();
    }
}
