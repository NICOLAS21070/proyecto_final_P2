package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

public class SistemaEnvios {

    private static SistemaEnvios instancia;
    private BaseDatosLogistica baseDatos;

    // 🔒 Constructor privado (patrón Singleton)
    private SistemaEnvios() {
        this.baseDatos = BaseDatosLogistica.getInstancia();
    }

    // ✅ Método estático para obtener la única instancia
    public static SistemaEnvios getInstancia() {
        if (instancia == null) {
            instancia = new SistemaEnvios();
        }
        return instancia;
    }

    // ✅ Métodos de acceso a las listas del sistema
    public BaseDatosLogistica getBaseDatos() {
        return baseDatos;
    }
}
