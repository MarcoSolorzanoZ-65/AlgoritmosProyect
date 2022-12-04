package org.algoritmos;

public class Nodo {
    private Jugador dato;
    private Nodo anterior, siguiente;

    public Nodo(Jugador dato) {
        this.dato = dato;
    }

    public Jugador getDato() {
        return dato;
    }

    public void setDato(Jugador dato) {
        this.dato = dato;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "dato=" + dato +
                ", anterior=" + anterior +
                ", siguiente=" + siguiente +
                '}';
    }
}
