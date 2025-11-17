package org.uniquindio.edu.co.poo.proyecto_final_p2.Model;

public interface EnvioObservable {
    void agregarObserver(EnvioObserver observer);
    void notificarObservers();
}
