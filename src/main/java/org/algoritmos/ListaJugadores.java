package org.algoritmos;

public class ListaJugadores {

    private Nodo cabeza, cola;
    private int cantidadNodos;

    public ListaJugadores() {
    }

    private boolean estaVacia() {
        return cabeza == null;
    }

    public void insertarInicio(Jugador jugador) {
        Nodo nuevo = new Nodo(jugador);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
        }
    }

    public void insertarFinal(Jugador jugador) {
        Nodo nuevo = new Nodo(jugador);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            nuevo.setAnterior(cola);
            cola.setSiguiente(nuevo);
            cola = nuevo;
        }
    }
}
