package org.PlayerRelated;

public class NodoH {
    private Historial dato;
    private NodoH anterior, siguiente;

    public NodoH(Historial dato){
        this.dato = dato;
    }

    public Historial getDato() {
        return dato;
    }

    public void setDato(Historial dato) {
        this.dato = dato;
    }

    public NodoH getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoH anterior) {
        this.anterior = anterior;
    }

    public NodoH getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoH siguiente) {
        this.siguiente = siguiente;
    }
}
